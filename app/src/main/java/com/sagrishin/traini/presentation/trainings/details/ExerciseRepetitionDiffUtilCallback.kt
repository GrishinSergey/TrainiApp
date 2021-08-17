package com.sagrishin.traini.presentation.trainings.details

import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.traini.presentation.uimodels.UiExerciseRepetition

class ExerciseRepetitionDiffUtilCallback : DiffUtil.ItemCallback<UiExerciseRepetition>() {

    override fun areItemsTheSame(oldItem: UiExerciseRepetition, newItem: UiExerciseRepetition): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiExerciseRepetition, newItem: UiExerciseRepetition): Boolean {
        return oldItem == newItem
    }

}
