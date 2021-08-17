package com.sagrishin.traini.presentation.exercises

import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.traini.presentation.uimodels.UiGroupedExercises

class GroupedExercisesDiffUtilCallbackImpl : DiffUtil.ItemCallback<UiGroupedExercises>() {

    override fun areItemsTheSame(oldItem: UiGroupedExercises, newItem: UiGroupedExercises): Boolean {
        return oldItem.muscleGroup == newItem.muscleGroup
    }

    override fun areContentsTheSame(oldItem: UiGroupedExercises, newItem: UiGroupedExercises): Boolean {
        return oldItem == newItem
    }

}
