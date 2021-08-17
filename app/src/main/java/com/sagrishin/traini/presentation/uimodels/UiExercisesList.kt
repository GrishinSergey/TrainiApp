package com.sagrishin.traini.presentation.uimodels

data class UiExercisesList(
    val exercises: List<UiGroupedExercises>
)


data class UiGroupedExercises(
    val muscleGroup: UiMuscleGroup,
    val exercises: List<UiExerciseItem>
)


data class UiExerciseItem(
    val id: Long,
    val name: String,
)