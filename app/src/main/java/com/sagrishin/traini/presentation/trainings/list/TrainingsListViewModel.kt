package com.sagrishin.traini.presentation.trainings.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sagrishin.common.livedatas.ActionLiveData
import com.sagrishin.common.utils.currentDay
import com.sagrishin.common.utils.currentDayDateTime
import com.sagrishin.common.viewmodels.BaseViewModel
import com.sagrishin.traini.domain.usecases.TrainingActionsUseCase
import com.sagrishin.traini.domain.usecases.TrainingsListUseCase
import com.sagrishin.traini.presentation.uimodels.UiTrainingsList
import com.sagrishin.traini.presentation.uimodels.UiWeekSelectableDay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class TrainingsListViewModel @Inject constructor(
    private val useCase: TrainingsListUseCase,
    private val trainingActionsUseCase: TrainingActionsUseCase,
    private val weekDaysDataSource: WeekDaysDataSource
) : BaseViewModel() {

    var selectedDate: LocalDate = currentDay

    val trainingsListLiveData: LiveData<UiTrainingsList>
        get() = _trainingsListLiveData
    private val _trainingsListLiveData = MutableLiveData<UiTrainingsList>()

    val newTrainingCreatedLiveData: LiveData<Long>
        get() = _newTrainingLiveData
    private val _newTrainingLiveData = ActionLiveData<Long>()

    val weekDaysLiveData: LiveData<PagingData<UiWeekSelectableDay>> by lazy { _weekDaysFlow.asLiveData() }
    private lateinit var _weekDaysFlow: Flow<PagingData<UiWeekSelectableDay>>

    val selectedDateFlow = MutableStateFlow(selectedDate)

    fun initLoadingWeekDays() {
        _weekDaysFlow = Pager(
            config = PagingConfig(pageSize = 7),
            initialKey = currentDayDateTime
        ) { weekDaysDataSource }
            .flow
            .cachedIn(viewModelScope)
            .map { pagingData -> pagingData.map { UiWeekSelectableDay(it, it.day == selectedDate) } }
            .catch { _errorsLiveData.value = it }
    }

    fun initLoadingTrainings() {
        viewModelScope.launch {
            selectedDateFlow.collect { date ->
                selectedDate = date

                useCase.loadTrainingsBy(date)
                    .distinctUntilChanged()
                    .catch { _errorsLiveData.setValue(it) }
                    .collect { _trainingsListLiveData.value = it }
            }
        }
    }

    fun createTrainingAt(createdOn: LocalDateTime) {
        viewModelScope.launch(commonErrorsHandler) {
            _newTrainingLiveData.value = trainingActionsUseCase.createTrainingAt(createdOn)
        }
    }

}
