package com.sagrishin.traini.presentation.uimodels

import java.time.LocalDateTime

data class UiTrainingsList(
    val trainings: List<UiTrainingItem>
)


data class UiTrainingItem(
    val id: Long,
    val dateTime: LocalDateTime,
    val muscleGroups: List<UiMuscleGroup>,
    val exerciseNames: List<String>
) {

//    val muscleNames = muscleNames.joinToString()
//    val exerciseNames = exerciseNames.joinToString()

}
