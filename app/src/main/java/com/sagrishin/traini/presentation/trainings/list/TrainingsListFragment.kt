package com.sagrishin.traini.presentation.trainings.list

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.sagrishin.common.fragments.BaseFragment
import com.sagrishin.common.utils.currentDay
import com.sagrishin.common.utils.currentTime
import com.sagrishin.traini.R
import com.sagrishin.traini.databinding.TrainingsListFragmentBinding
import com.sagrishin.traini.presentation.trainings.list.TrainingsListFragmentDirections.Companion.showSingleTraining
import com.sagrishin.traini.presentation.uimodels.UiTrainingItem
import com.sagrishin.uikit.dialogs.showTimePicker
import com.sagrishin.uikit.recyclerview.BaseRecyclerViewAdapter
import com.sagrishin.uikit.recyclerview.SpaceItemDecoration
import com.sagrishin.uikit.utils.adapter
import com.sagrishin.uikit.utils.getDateFormattedAsDayMonth
import com.sagrishin.uikit.utils.setSafeOnClickListener
import java.time.LocalDateTime

class TrainingsListFragment : BaseFragment(R.layout.trainings_list_fragment, false) {

    private val viewModel by viewModels<TrainingsListViewModel> { viewModelFactory }
    private val binding by viewBinding(TrainingsListFragmentBinding::bind)
    private lateinit var weekDaysAdapter: WeekDaysAdapter
    private lateinit var trainingsAdapter: BaseRecyclerViewAdapter<UiTrainingItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.selectedDate = currentDay
        viewModel.initLoadingWeekDays()
        viewModel.loadTrainingsBy(currentDay)

        weekDaysAdapter = WeekDaysAdapter { pos ->
            val selectableWeekDay = weekDaysAdapter[pos]
            viewModel.selectedDate = selectableWeekDay.weekDay.day
            viewModel.loadTrainingsBy(selectableWeekDay.weekDay.day)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = getDateFormattedAsDayMonth(currentDay)
        binding.toolbar.setSafeOnClickListener {
            /// show current day and display on correct place at week
        }

        val metrics = DisplayMetrics().apply { requireActivity().windowManager.defaultDisplay.getMetrics(this) }
        val weekDayWidthPx = 40.toPx()
        val weekDaysPerScreenCount = 7
        val weekMarginsSizePx = 32.toPx()
        val separatorWidth =
            (metrics.widthPixels - weekDayWidthPx * weekDaysPerScreenCount - weekMarginsSizePx) /
            (weekDaysPerScreenCount - 1)

        binding.weekDays.adapter = weekDaysAdapter
        binding.weekDays.setHasFixedSize(true)
        binding.weekDays.addItemDecoration(SpaceItemDecoration(
            context = requireContext(),
            orientation = LinearLayout.HORIZONTAL,
            space = separatorWidth
        ))

        val weekDaysLayoutManager = binding.weekDays.layoutManager as LinearLayoutManager
        binding.weekDays.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val firstVisibleItemPos = weekDaysLayoutManager.findFirstCompletelyVisibleItemPosition()

                if (firstVisibleItemPos != RecyclerView.NO_POSITION) {
                    val name = weekDaysAdapter[firstVisibleItemPos].weekDay.monthName
                    if (binding.monthName.text != name) binding.monthName.text = name
                }
            }
        })

        trainingsAdapter = adapter(diffUtilCallback = TrainingsDiffUtilCallbackImpl()) {
            TrainingItemHolder(it) { trainingId -> findNavController().navigate(showSingleTraining(trainingId)) }
        }
        binding.trainings.setHasFixedSize(true)
        binding.trainings.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayout.HORIZONTAL).apply {
            setDrawable(getDrawable(R.drawable.divider_trainings))
        })
        binding.trainings.adapter = trainingsAdapter

        binding.fab.setSafeOnClickListener {
            requireContext().showTimePicker(currentTime) { selectedTime ->
                viewModel.createTrainingAt(LocalDateTime.of(viewModel.selectedDate, selectedTime))
            }
        }

        viewModel.trainingsListLiveData.observe(viewLifecycleOwner) { trainingsAdapter.setItems(it.trainings) }

        /**
         * An alternative way to subscribe on updates from paging:
         *
         * lifecycleScope.launch { viewModel.weekDaysFlow.collect { weekDaysAdapter.submitData(it) } }
         *
         * The main difference is in 'observer' type. In code below it is LiveData and prev code was
         * subscribed to kotlin coroutine Flow.
         */

        viewModel.weekDaysLiveData.observe(viewLifecycleOwner) {
            weekDaysAdapter.submitData(lifecycle, it)
            /// need to check, which date is "current", then calculate last first week day position
            /// from this "current" date. This first week day should be first visible item on screen
        }

        viewModel.newTrainingCreatedLiveData.observe(viewLifecycleOwner) { newTrainingId ->
            findNavController().navigate(showSingleTraining(newTrainingId))
        }
    }

}
