package com.farimarwat.krossuidemo

import androidx.compose.ui.window.ComposeUIViewController
import platform.Foundation.NSProcessInfo

@Suppress("ktlint:standard:function-naming") // Name used from Swift (MainViewControllerKt.MainViewController)
fun MainViewController() = ComposeUIViewController { App(benchmark = launchArgument("-benchmark")) }

/** Value after [name] in the launch arguments, e.g. `-benchmark native`. */
private fun launchArgument(name: String): String? {
    val args = NSProcessInfo.processInfo.arguments.map { it.toString() }
    return args.getOrNull(args.indexOf(name) + 1)?.takeIf { name in args }
}
