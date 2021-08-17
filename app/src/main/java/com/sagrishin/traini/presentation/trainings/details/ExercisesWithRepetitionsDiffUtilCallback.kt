package com.sagrishin.traini.presentation.trainings.details

import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.traini.presentation.uimodels.UiExerciseWithRepetitions

class ExercisesWithRepetitionsDiffUtilCallback : DiffUtil.ItemCallback<UiExerciseWithRepetitions>() {

    override fun areItemsTheSame(oldItem: UiExerciseWithRepetitions, newItem: UiExerciseWithRepetitions): Boolean {
        return oldItem.exerciseData.id == newItem.exerciseData.id
    }

    override fun areContentsTheSame(oldItem: UiExerciseWithRepetitions, newItem: UiExerciseWithRepetitions): Boolean {
        return oldItem == newItem
    }

}
