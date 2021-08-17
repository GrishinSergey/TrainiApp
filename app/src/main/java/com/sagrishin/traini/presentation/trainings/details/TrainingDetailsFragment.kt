package com.sagrishin.traini.presentation.trainings.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.common.fragments.BaseFragment
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.ExerciseWithRepetitionsItemBinding
import com.sagrishin.traini.databinding.TrainingDetailsFragmentBinding
import com.sagrishin.traini.presentation.trainings.details.TrainingDetailsFragmentDirections.Companion.showExercisesSelector
import com.sagrishin.uikit.utils.adapter
import com.sagrishin.uikit.utils.inflate
import com.sagrishin.uikit.utils.setSafeOnClickListener
import com.sagrishin.uikit.utils.setupFabOnScrollVisibilityHandler

class TrainingDetailsFragment : BaseFragment(R.layout.training_details_fragment) {

    private val args: TrainingDetailsFragmentArgs by navArgs()
    private val viewModel: TrainingDetailsViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding(TrainingDetailsFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadTrainingBy(args.trainingId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPool = RecyclerView.RecycledViewPool()
        binding.exercises.setHasFixedSize(true)
        val adapter = adapter(diffUtilCallback = ExercisesWithRepetitionsDiffUtilCallback()) {
            val itemView = it.inflate(R.layout.exercise_with_repetitions_item)
            val binding = ExerciseWithRepetitionsItemBinding.bind(itemView)
            ExerciseWithRepetitionsHolder(sharedPool, binding, ExerciseWithRepetitionsHolderInteractorImpl())
        }
        binding.exercises.adapter = adapter
        binding.exercises.setupFabOnScrollVisibilityHandler(binding.fab)

        binding.fab.setSafeOnClickListener { findNavController().navigate(showExercisesSelector()) }

        viewModel.exercisesListLiveData.observe(viewLifecycleOwner) {
            adapter.setItems(it)
        }

        viewModel.trainingLiveData.observe(viewLifecycleOwner) {

        }
    }


    private inner class ExerciseWithRepetitionsHolderInteractorImpl : ExerciseWithRepetitionsHolder.Interactor {
        override fun onAddRepetitionFor(exerciseId: Long, position: Int) {

        }

        override fun onEditRepetitionBy(exerciseId: Long, exercisePosition: Int, id: Long, position: Int) {

        }

        override fun onDeleteRepetitionBy(exerciseId: Long, exercisePosition: Int, id: Long, position: Int) {

        }
    }

}
