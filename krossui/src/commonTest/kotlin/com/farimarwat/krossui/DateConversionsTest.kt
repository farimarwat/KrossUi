package com.farimarwat.krossui

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate

class DateConversionsTest {
    @Test
    fun utcMillisRoundTrip() {
        val dates = listOf(LocalDate(2024, 2, 29), LocalDate(1970, 1, 1), LocalDate(2026, 12, 31))
        for (date in dates) {
            assertEquals(date, localDateFromUtcMillis(date.toUtcMillis()))
        }
    }

    @Test
    fun utcMillisIsMidnightUtc() {
        assertEquals(0L, LocalDate(1970, 1, 1).toUtcMillis())
        assertEquals(86_400_000L, LocalDate(1970, 1, 2).toUtcMillis())
    }
}
