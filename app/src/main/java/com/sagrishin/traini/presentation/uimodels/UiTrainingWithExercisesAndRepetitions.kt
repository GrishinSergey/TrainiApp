package com.sagrishin.traini.presentation.uimodels

data class UiTrainingWithExercisesAndRepetitions constructor(
    val trainingData: UiTrainingData,
    val exercises: List<UiExerciseWithRepetitions>
)
