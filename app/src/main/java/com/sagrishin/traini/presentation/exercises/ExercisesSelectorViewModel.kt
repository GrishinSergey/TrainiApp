package com.sagrishin.traini.presentation.exercises

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sagrishin.common.viewmodels.BaseViewModel
import com.sagrishin.traini.domain.usecases.ExercisesListUseCase
import com.sagrishin.traini.presentation.uimodels.UiGroupedExercises
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExercisesSelectorViewModel @Inject constructor(
    private val useCase: ExercisesListUseCase
) : BaseViewModel() {

    val exercisesLiveData: LiveData<List<UiGroupedExercises>>
        get() = _exercisesLiveData
    private val _exercisesLiveData = MutableLiveData<List<UiGroupedExercises>>()

    fun loadExercises() {
        viewModelScope.launch {
            useCase.getAllExercises()
                .flowOn(Dispatchers.IO + commonErrorsHandler)
                .distinctUntilChanged()
                .collect { _exercisesLiveData.value = it.exercises }
        }
    }

}
