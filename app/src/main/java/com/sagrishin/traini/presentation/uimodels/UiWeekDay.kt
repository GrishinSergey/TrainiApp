package com.sagrishin.traini.presentation.uimodels

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

data class UiWeekDay constructor(
    val day: LocalDate,
    var hasTraining: Boolean
) {

    val monthName: String
        get() = day.month.getDisplayName(TextStyle.FULL, Locale.getDefault())

}


data class UiWeekSelectableDay constructor(
    val weekDay: UiWeekDay,
    var isSelected: Boolean = false
)