package com.sagrishin.traini.presentation.exercises

import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.ExerciseItemBinding
import com.sagrishin.traini.presentation.uimodels.UiExerciseItem
import com.sagrishin.uikit.recyclerview.BaseHolder
import com.sagrishin.uikit.utils.setSafeOnClickListener

class ExerciseItemHolder constructor(
    parent: ViewGroup,
    private val interactor: ExerciseItemInteractor
): BaseHolder<UiExerciseItem>(R.layout.exercise_item, parent) {

    private val binding by viewBinding(ExerciseItemBinding::bind)

    override fun onBind(item: UiExerciseItem) {
        binding.exerciseName.text = item.name
        binding.exerciseName.setSafeOnClickListener { interactor.onSelectExercise(item.id) }
    }


    fun interface ExerciseItemInteractor {

        fun onSelectExercise(exerciseId: Long)

    }

}
