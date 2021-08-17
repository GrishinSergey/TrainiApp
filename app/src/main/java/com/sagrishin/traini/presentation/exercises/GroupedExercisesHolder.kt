package com.sagrishin.traini.presentation.exercises

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.traini.databinding.GroupedExercisesItemBinding
import com.sagrishin.traini.presentation.uimodels.UiGroupedExercises
import com.sagrishin.uikit.recyclerview.BaseHolder
import com.sagrishin.uikit.utils.adapter

class GroupedExercisesHolder constructor(
    view: View,
    private val commonExercisesPool: RecyclerView.RecycledViewPool,
    private val interactor: ExerciseItemHolder.ExerciseItemInteractor
) : BaseHolder<UiGroupedExercises>(view) {

    private val binding by viewBinding(GroupedExercisesItemBinding::bind)

    override fun onBind(item: UiGroupedExercises) {
        super.onBind(item)

        binding.title.text = getString(item.muscleGroup.nameResId)

        binding.exercises.isNestedScrollingEnabled = false
        binding.exercises.setRecycledViewPool(commonExercisesPool)

        binding.exercises.adapter = adapter(item.exercises, ExerciseItemDiffUtilCallbackImpl()) {
            ExerciseItemHolder(it, interactor)
        }
    }

}
