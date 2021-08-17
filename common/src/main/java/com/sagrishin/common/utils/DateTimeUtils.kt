package com.sagrishin.common.utils

import java.time.*
import java.time.temporal.WeekFields
import java.util.*

val currentDayDateTime: LocalDateTime
    get() = LocalDateTime.now()

val currentDay: LocalDate
    get() = LocalDate.now()

val currentTime: LocalTime
    get() = LocalTime.now()

fun LocalDateTime.plusOneMonth(): LocalDateTime {
    return plusMonths(1).toLocalDate().atStartOfDay()
}

fun LocalDateTime.minusOneMonth(): LocalDateTime {
    return minusMonths(1).toLocalDate().atStartOfDay()
}

fun LocalDate.atEndOfDay(): LocalDateTime {
    return LocalDateTime.of(this, LocalTime.MAX)
}

fun LocalDateTime.atEndOfDay(): LocalDateTime {
    return LocalDateTime.of(toLocalDate(), LocalTime.MAX)
}

fun LocalDateTime.atStartOfDay(): LocalDateTime {
    return LocalDateTime.of(toLocalDate(), LocalTime.MIN)
}

val LocalDateTime.isInCurrentMonth: Boolean
    get() = month.value == currentDayDateTime.month.value

val LocalDateTime.firstDayOfMonth: LocalDate
    get() = toLocalDate().withDayOfMonth(1)

val LocalDateTime.lastDayOfMonth: LocalDate
    get() = YearMonth.from(this.toLocalDate()).atEndOfMonth()

val LocalDateTime.firstDayOfWeek: LocalDate
    get() = with(WeekFields.of(Locale.getDefault()).firstDayOfWeek).toLocalDate()

val LocalDateTime.lastDayOfWeek: LocalDate
    get() = with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 7L).toLocalDate()

val LocalDateTime.isToday: Boolean
    get() = toLocalDate() == currentDayDateTime.toLocalDate()

val LocalDateTime.isOnThisWeek: Boolean
    get() {
        val today = currentDayDateTime
        return toLocalDate().run { this >= today.firstDayOfWeek && this <= today.lastDayOfWeek }
    }

val LocalDateTime.millis: Long
    get() = atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

val LocalDate.millis: Long
    get() = atStartOfDay().millis

val LocalDateTime.instant: Instant
    get() = atZone(ZoneId.systemDefault()).toInstant()

val LocalDate.instant: Instant
    get() = atStartOfDay(ZoneId.systemDefault()).toInstant()

val LocalTime.instant: Instant
    get() = atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant()


fun Date.toLocalDateTime(): LocalDateTime {
    return LocalDateTime.ofInstant(toInstant(), ZoneId.systemDefault())
}

fun LocalDateTime.toDate(): Date {
    return Date.from(instant)
}

fun LocalDate.toDate(): Date {
    return Date.from(instant)
}


inline fun <R> ClosedRange<LocalDateTime>.mapLocalDateTimes(
    step: LocalDateTime.() -> LocalDateTime = { plusDays(1) },
    action: (LocalDateTime) -> R
): List<R> {
    val res = mutableListOf<R>()
    var day = start
    while (day <= endInclusive) {
        res += action(day)
        day = day.step()
    }

    return res
}

inline fun <R> ClosedRange<LocalDate>.mapLocalDates(
    step: LocalDate.() -> LocalDate = { plusDays(1) },
    action: (LocalDate) -> R
): List<R> {
    val res = mutableListOf<R>()
    var day = start
    while (day <= endInclusive) {
        res += action(day)
        day = day.step()
    }

    return res
}


fun ClosedRange<LocalDateTime>.toList(): List<LocalDateTime> {
    return mapLocalDateTimes { it }
}
