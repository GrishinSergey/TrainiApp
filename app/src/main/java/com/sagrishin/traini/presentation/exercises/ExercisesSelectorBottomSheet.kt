package com.sagrishin.traini.presentation.exercises

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagrishin.common.navutils.setResultToPrevBackStackEntry
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.ExericesSelectorDialogBinding
import com.sagrishin.uikit.utils.adapter
import com.sagrishin.uikit.utils.inflate
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ExercisesSelectorBottomSheet : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
        internal set
    private val binding by viewBinding(ExericesSelectorDialogBinding::bind)
    private val viewModel by viewModels<ExercisesSelectorViewModel> { viewModelFactory }
    private lateinit var behavior: BottomSheetBehavior<View>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadExercises()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.exerices_selector_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        behavior = BottomSheetBehavior.from(binding.root.parent as View)
        behavior.addBottomSheetCallback(BottomSheetCallbackImpl())

        val commonPool = RecyclerView.RecycledViewPool()
        val adapter = adapter(diffUtilCallback = GroupedExercisesDiffUtilCallbackImpl()) {
            GroupedExercisesHolder(it.inflate(R.layout.grouped_exercises_item), commonPool) { exerciseId ->
                setResultToPrevBackStackEntry(result = OnExerciseSelected(exerciseId))
            }
        }
        binding.groupedExercises.adapter = adapter

        viewModel.exercisesLiveData.observe(viewLifecycleOwner) { adapter.setItems(it) }
    }


    private inner class BottomSheetCallbackImpl : BottomSheetBehavior.BottomSheetCallback() {
        private val defaultElevation = 16F

        override fun onStateChanged(bottomSheet: View, newState: Int) {
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            binding.appBar.elevation = defaultElevation * slideOffset
        }
    }

}
