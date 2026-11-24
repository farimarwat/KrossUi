package com.farimarwat.krossui

import kotlin.test.Test
import kotlin.test.assertEquals

class SnapToStepsTest {
    @Test
    fun continuousSliderOnlyClamps() {
        assertEquals(0.37f, snapToSteps(0.37f, 0f..1f, steps = 0))
        assertEquals(1f, snapToSteps(1.5f, 0f..1f, steps = 0))
        assertEquals(0f, snapToSteps(-2f, 0f..1f, steps = 0))
    }

    @Test
    fun snapsToNearestStep() {
        // 3 steps in 0..1 -> values 0, 0.25, 0.5, 0.75, 1
        assertEquals(0.25f, snapToSteps(0.3f, 0f..1f, steps = 3))
        assertEquals(0.5f, snapToSteps(0.4f, 0f..1f, steps = 3))
        assertEquals(1f, snapToSteps(0.9f, 0f..1f, steps = 3))
    }

    @Test
    fun snapsWithinCustomRange() {
        // 1 step in 10..20 -> values 10, 15, 20
        assertEquals(15f, snapToSteps(16f, 10f..20f, steps = 1))
        assertEquals(10f, snapToSteps(11f, 10f..20f, steps = 1))
    }
}
