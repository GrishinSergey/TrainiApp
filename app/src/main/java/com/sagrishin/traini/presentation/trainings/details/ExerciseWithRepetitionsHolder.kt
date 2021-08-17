package com.sagrishin.traini.presentation.trainings.details

import androidx.recyclerview.widget.RecyclerView
import com.sagrishin.traini.databinding.ExerciseWithRepetitionsItemBinding
import com.sagrishin.traini.presentation.uimodels.UiExerciseWithRepetitions
import com.sagrishin.uikit.recyclerview.BaseHolder
import com.sagrishin.uikit.utils.adapter
import com.sagrishin.uikit.utils.setSafeOnClickListener
import kotlin.properties.Delegates

class ExerciseWithRepetitionsHolder constructor(
    private val repetitionsSharedPool: RecyclerView.RecycledViewPool,
    private val binding: ExerciseWithRepetitionsItemBinding,
    private val interactor: Interactor
) : BaseHolder<UiExerciseWithRepetitions>(binding.root) {

    private var exerciseId: Long by Delegates.notNull()

    private val exerciseRepetitionInteractor = object : ExerciseRepetitionHolder.Interactor {
        override fun onEditRepetitionBy(id: Long, position: Int) {
            interactor.onEditRepetitionBy(exerciseId, bindingAdapterPosition, id, position)
        }

        override fun onDeleteRepetitionBy(id: Long, position: Int) {
            interactor.onDeleteRepetitionBy(exerciseId, bindingAdapterPosition, id, position)
        }
    }

    override fun onBind(item: UiExerciseWithRepetitions) {
        exerciseId = item.exerciseData.id

        binding.exerciseName.text = item.exerciseData.name

        binding.repetitions.setRecycledViewPool(repetitionsSharedPool)
        binding.repetitions.adapter = adapter(item.repetitions, ExerciseRepetitionDiffUtilCallback()) {
            ExerciseRepetitionHolder(it, exerciseRepetitionInteractor)
        }

        binding.addNewRepetition.setSafeOnClickListener {
            interactor.onAddRepetitionFor(exerciseId, bindingAdapterPosition)
        }
    }


    interface Interactor {

        fun onAddRepetitionFor(exerciseId: Long, position: Int)

        fun onEditRepetitionBy(exerciseId: Long, exercisePosition: Int, id: Long, position: Int)

        fun onDeleteRepetitionBy(exerciseId: Long, exercisePosition: Int, id: Long, position: Int)

    }

}
