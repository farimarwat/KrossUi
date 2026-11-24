package com.farimarwat.krossui

import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.utils.resolveSize
import kotlin.test.Test
import kotlin.test.assertEquals

/** Size rules of NativeView (1 dp = 1 px here, density 1). */
class NativeSizingTest {
    private val density = Density(1f)

    private fun resolve(min: Int, max: Int, preferred: Dp, minimum: Dp = 0.dp) =
        with(density) { resolveSize(min, max, preferred, minimum) }

    @Test
    fun wrapUsesNaturalSize() = assertEquals(80, resolve(0, 300, 80.dp))

    @Test
    fun fixedConstraintWins() = assertEquals(300, resolve(300, 300, 80.dp))

    @Test
    fun naturalSizeIsCappedByMax() = assertEquals(100, resolve(0, 100, 250.dp))

    @Test
    fun noNaturalSizeFillsAvailableSpace() = assertEquals(300, resolve(0, 300, Dp.Unspecified))

    @Test
    fun noNaturalSizeAndUnboundedIsZero() = assertEquals(0, resolve(0, Constraints.Infinity, Dp.Unspecified))

    @Test
    fun touchTargetMinimumApplies() = assertEquals(44, resolve(0, 300, 30.dp, minimum = 44.dp))
}
