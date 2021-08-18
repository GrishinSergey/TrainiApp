package com.sagrishin.traini.presentation.exercises

import com.sagrishin.common.navutils.BackStackEvent
import kotlinx.parcelize.Parcelize

@Parcelize
class OnExerciseSelected(
    val exerciseId: Long
) : BackStackEvent()