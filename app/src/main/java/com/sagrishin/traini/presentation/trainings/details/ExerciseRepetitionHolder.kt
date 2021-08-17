package com.sagrishin.traini.presentation.trainings.details

import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.ExerciseRepetitionItemBinding
import com.sagrishin.traini.presentation.uimodels.UiExerciseRepetition
import com.sagrishin.uikit.recyclerview.BaseHolder

class ExerciseRepetitionHolder(
    parent: ViewGroup,
    private val interactor: Interactor,
) : BaseHolder<UiExerciseRepetition>(R.layout.exercise_repetition_item, parent) {

    private val binding by viewBinding(ExerciseRepetitionItemBinding::bind)

    override fun onBind(item: UiExerciseRepetition) {
        val repetition = bindingAdapterPosition + 1
        binding.repetition.text = getString(R.string.repetition_format, repetition, item.weight, item.repetitionsCount)
    }

    interface Interactor {

        fun onEditRepetitionBy(id: Long, position: Int)

        fun onDeleteRepetitionBy(id: Long, position: Int)

    }

}
