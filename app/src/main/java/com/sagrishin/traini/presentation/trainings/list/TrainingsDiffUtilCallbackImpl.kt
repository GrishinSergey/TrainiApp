package com.sagrishin.traini.presentation.trainings.list

import androidx.recyclerview.widget.DiffUtil
import com.sagrishin.traini.presentation.uimodels.UiTrainingItem

class TrainingsDiffUtilCallbackImpl : DiffUtil.ItemCallback<UiTrainingItem>() {

    override fun areItemsTheSame(oldItem: UiTrainingItem, newItem: UiTrainingItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiTrainingItem, newItem: UiTrainingItem): Boolean {
        return oldItem == newItem
    }

}
