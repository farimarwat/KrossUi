package com.farimarwat.krossui

import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn

/** Today's date in the device time zone. */
internal fun today(): LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

/** Midnight UTC of this date, in epoch milliseconds (the format Material's DatePicker uses). */
internal fun LocalDate.toUtcMillis(): Long = atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()

/** The UTC calendar date of an epoch-milliseconds value. */
internal fun localDateFromUtcMillis(millis: Long): LocalDate =
    Instant.fromEpochMilliseconds(millis).toLocalDateTime(TimeZone.UTC).date
