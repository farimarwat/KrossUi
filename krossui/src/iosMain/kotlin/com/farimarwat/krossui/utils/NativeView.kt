package com.farimarwat.krossui.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.viewinterop.UIKitView
import kotlin.math.abs
import kotlinx.cinterop.useContents
import platform.UIKit.UISemanticContentAttributeForceLeftToRight
import platform.UIKit.UISemanticContentAttributeForceRightToLeft
import platform.UIKit.UIView
import platform.UIKit.accessibilityLabel

/** iOS minimum touch target (Human Interface Guidelines). */
internal val MinTouchTarget: Dp = 44.dp

/** The view's natural size from UIKit; dimensions without an intrinsic size are [Dp.Unspecified]. */
internal fun UIView.intrinsicDpSize(): DpSize = intrinsicContentSize.useContents {
    DpSize(
        width = if (width > 0) width.dp else Dp.Unspecified,
        height = if (height > 0) height.dp else Dp.Unspecified
    )
}

/**
 * Hosts a UIKit view and sizes it like a native Compose component.
 *
 * - The view is created once by [factory]; [update] applies the latest parameters before every measure,
 *   so the natural size always matches the current content.
 * - Size per axis: a fixed constraint (e.g. `fillMaxWidth()`, `size(...)`) wins; otherwise the view's
 *   [preferredSize] (UIKit's intrinsic size by default); a dimension without a natural size fills the
 *   available space. [minSize] (e.g. the 44pt touch target) is always respected.
 * - 1 dp equals 1 pt on iOS, so UIKit sizes map directly to dp.
 * - Accessibility: VoiceOver reads the native view; [accessibilityLabel] sets its label. The view follows
 *   Compose's layout direction (RTL).
 * - Clipping: the view is clipped wherever Compose clips it (e.g. scrolled out of a list), but left
 *   unclipped while fully visible so Liquid Glass halos and shadows are not cut off.
 */
@Composable
internal fun <T : UIView> NativeView(
    factory: () -> T,
    update: (T) -> Unit,
    modifier: Modifier = Modifier,
    minSize: DpSize = DpSize(0.dp, 0.dp),
    accessibilityLabel: String? = null,
    preferredSize: (T) -> DpSize = { it.intrinsicDpSize() }
) {
    val view = remember { factory() }
    val rtl = LocalLayoutDirection.current == LayoutDirection.Rtl
    // Read so a text-size (Dynamic Type) change recomposes and re-applies scaled fonts
    LocalDensity.current.fontScale
    // Runs after composition and before layout of the same frame
    SideEffect {
        // Follow Compose's layout direction (e.g. a forced RTL screen), not only the app language
        view.semanticContentAttribute =
            if (rtl) UISemanticContentAttributeForceRightToLeft else UISemanticContentAttributeForceLeftToRight
        update(view)
        // VoiceOver label; null keeps UIKit's own label (e.g. a button's title)
        accessibilityLabel?.let { view.accessibilityLabel = it }
    }

    UIKitView(
        factory = { view },
        properties = NativeInteropProperties,
        modifier = modifier
            .onGloballyPositioned { coordinates -> view.clipWhenCompose(coordinates) }
            .layout { measurable, constraints ->
                val preferred = preferredSize(view)
                val width = resolveSize(constraints.minWidth, constraints.maxWidth, preferred.width, minSize.width)
                val height = resolveSize(constraints.minHeight, constraints.maxHeight, preferred.height, minSize.height)
                val placeable = measurable.measure(Constraints.fixed(width, height))
                layout(width, height) { placeable.place(0, 0) }
            }
    )
}

internal fun Density.resolveSize(min: Int, max: Int, preferred: Dp, minimum: Dp): Int {
    val bounded = max != Constraints.Infinity
    val natural = when {
        preferred.isSpecified -> preferred.roundToPx()

        // No natural size: fill the available space
        bounded -> max

        else -> 0
    }
    val atLeast = maxOf(natural, minimum.roundToPx(), min)
    return if (bounded) atLeast.coerceAtMost(max) else atLeast
}

/**
 * Overlay interop views are not clipped by Compose (so glass effects can extend past their bounds).
 * When Compose clips this view (e.g. scrolled partly out of a container), turn clipping on in the
 * interop wrapper, which Compose sizes to the visible area.
 *
 * Hierarchy created by Compose: wrapper (visible area) → host view (full bounds) → this view.
 */
private fun UIView.clipWhenCompose(coordinates: LayoutCoordinates) {
    val visible = coordinates.boundsInWindow()
    val full = Rect(coordinates.positionInWindow(), coordinates.size.toSize())
    val clippedByCompose = abs(visible.width - full.width) > 0.5f || abs(visible.height - full.height) > 0.5f
    val wrapper = superview?.superview ?: return
    // Called on every scroll frame: only touch UIKit when the value changes
    if (wrapper.clipsToBounds != clippedByCompose) wrapper.clipsToBounds = clippedByCompose
}
