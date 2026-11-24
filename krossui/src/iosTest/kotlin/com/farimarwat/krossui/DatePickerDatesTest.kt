package com.farimarwat.krossui

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.datetime.LocalDate

/** UIDatePicker works with NSDate in the device time zone; calendar dates must survive the round trip. */
class DatePickerDatesTest {
    @Test
    fun localDateRoundTripsThroughNSDate() {
        val dates =
            listOf(LocalDate(2024, 2, 29), LocalDate(2026, 3, 29), LocalDate(2026, 10, 25), LocalDate(1999, 12, 31))
        for (date in dates) {
            assertEquals(date, date.toNSDate().toLocalDate())
        }
    }
}
