package com.sagrishin.traini.presentation.trainings.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sagrishin.common.utils.indexOfFirstOrNull
import com.sagrishin.common.viewmodels.BaseViewModel
import com.sagrishin.traini.domain.usecases.SingleTrainingUseCase
import com.sagrishin.traini.presentation.uimodels.UiExerciseWithRepetitions
import com.sagrishin.traini.presentation.uimodels.UiTrainingData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
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
                .distinctUntilChanged()
                .collect {
                    _exercisesListLiveData.value = it.exercises
                    _trainingListLiveData.value = it.trainingData
                }
        }
    }

    fun addExerciseTo(trainingId: Long, exerciseId: Long) {
        launch {
            val exists = _exercisesListLiveData.indexOfFirstOrNull { it.exerciseData.id == exerciseId } != null
            if (!exists) useCase.addExerciseTo(trainingId, exerciseId)
        }
    }

}
