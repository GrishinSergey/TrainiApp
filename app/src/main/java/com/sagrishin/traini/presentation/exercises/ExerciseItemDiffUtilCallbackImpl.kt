package com.sagrishin.traini.presentation.exercises

import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.traini.presentation.uimodels.UiExerciseItem

class ExerciseItemDiffUtilCallbackImpl : DiffUtil.ItemCallback<UiExerciseItem>() {

    override fun areItemsTheSame(oldItem: UiExerciseItem, newItem: UiExerciseItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiExerciseItem, newItem: UiExerciseItem): Boolean {
        return oldItem == newItem
    }

}
