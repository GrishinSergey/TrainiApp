package com.sagrishin.traini.presentation.uimodels

import androidx.annotation.StringRes
import com.sagrishin.traini.R

enum class UiMuscleGroup(
    @StringRes
    val nameResId: Int
) {
    NECK(R.string.neck),
    TRAPEZE(R.string.trapeze),
    SHOULDERS(R.string.shoulders),
    CHEST(R.string.chest),
    LATISSIMUS(R.string.latissimus),
    BICEPS(R.string.biceps),
    TRICEPS(R.string.triceps),
    FOREARM(R.string.forearm),
    PRESS(R.string.press),
    BACK(R.string.back),
    QUADRICEPS(R.string.quadriceps),
    LEG_BICEPS(R.string.leg_biceps),
    CALVES(R.string.calves)
}