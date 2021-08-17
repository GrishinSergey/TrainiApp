package com.sagrishin.traini.presentation.trainings.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sagrishin.common.viewmodels.BaseViewModel
import com.sagrishin.traini.domain.usecases.SingleTrainingUseCase
import com.sagrishin.traini.presentation.uimodels.UiExerciseWithRepetitions
import com.sagrishin.traini.presentation.uimodels.UiTrainingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class TrainingDetailsViewModel @Inject constructor(
    private val useCase: SingleTrainingUseCase
): BaseViewModel() {

    val exercisesListLiveData: LiveData<List<UiExerciseWithRepetitions>>
        get() = _exercisesListLiveData
    private val _exercisesListLiveData = MutableLiveData<List<UiExerciseWithRepetitions>>()
    val trainingLiveData: LiveData<UiTrainingData>
        get() = _trainingListLiveData
    private val _trainingListLiveData = MutableLiveData<UiTrainingData>()

    fun loadTrainingBy(trainingId: Long) {
        viewModelScope.launch {
            useCase.getTrainingWithExercisesAndRepetitionsBy(trainingId)
                .flowOn(Dispatchers.IO + commonErrorsHandler)
                .distinctUntilChanged()
                .collect {
                    _exercisesListLiveData.value = it.exercises
                    _trainingListLiveData.value = it.trainingData
                }
        }
    }

}
