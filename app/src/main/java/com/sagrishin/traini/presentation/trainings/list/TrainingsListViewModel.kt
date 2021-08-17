package com.sagrishin.traini.presentation.trainings.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.sagrishin.common.livedatas.ActionLiveData
import com.sagrishin.common.utils.currentDayDateTime
import com.sagrishin.common.viewmodels.BaseViewModel
import com.sagrishin.traini.domain.usecases.TrainingActionsUseCase
import com.sagrishin.traini.domain.usecases.TrainingsListUseCase
import com.sagrishin.traini.presentation.uimodels.UiTrainingsList
import com.sagrishin.traini.presentation.uimodels.UiWeekSelectableDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class TrainingsListViewModel @Inject constructor(
    private val useCase: TrainingsListUseCase,
    private val trainingActionsUseCase: TrainingActionsUseCase,
    private val weekDaysDataSource: WeekDaysDataSource
) : BaseViewModel() {

    lateinit var selectedDate: LocalDate

    val trainingsListLiveData: LiveData<UiTrainingsList>
        get() = _trainingsListLiveData
    private val _trainingsListLiveData = MutableLiveData<UiTrainingsList>()

    val newTrainingCreatedLiveData: LiveData<Long>
        get() = _newTrainingLiveData
    private val _newTrainingLiveData = ActionLiveData<Long>()

    val weekDaysLiveData: LiveData<PagingData<UiWeekSelectableDay>> by lazy { _weekDaysFlow.asLiveData() }
    private lateinit var _weekDaysFlow: Flow<PagingData<UiWeekSelectableDay>>

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

    fun loadTrainingsBy(date: LocalDate) {
        viewModelScope.launch {
            useCase.loadTrainingsBy(date)
                .distinctUntilChanged()
                .catch { _errorsLiveData.value = it }
                .collect { _trainingsListLiveData.value = it }
        }
    }

    fun createTrainingAt(createdOn: LocalDateTime) {
        viewModelScope.launch {
            val newTrainingId = withContext(Dispatchers.IO + commonErrorsHandler) {
                trainingActionsUseCase.createTrainingAt(createdOn)
            }
            _newTrainingLiveData.value = newTrainingId
        }
    }

}
