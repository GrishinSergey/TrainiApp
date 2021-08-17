package com.sagrishin.traini.domain.usecases

import com.sagrishin.common.utils.toMap
import com.sagrishin.traini.domain.converters.toUiModel
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import com.sagrishin.traini.domain.repositories.MusclesRepository
import com.sagrishin.traini.presentation.uimodels.UiExerciseItem
import com.sagrishin.traini.presentation.uimodels.UiExercisesList
import com.sagrishin.traini.presentation.uimodels.UiGroupedExercises
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExercisesListUseCase @Inject constructor(
    private val repository: ExercisesRepository,
    private val musclesRepository: MusclesRepository
) {

    suspend fun getAllExercises(): Flow<UiExercisesList> {
        val allMuscles = musclesRepository.getAllMuscles().toMap { it.id to it }
        return repository.getAllExerciseBy().map { allExercises ->
            UiExercisesList(
                exercises = allExercises.groupBy { it.muscleId }.map { (muscleId, exercises) ->
                    val muscleGroup = allMuscles.getValue(muscleId).toUiModel()
                    val uiExercises = exercises.map { UiExerciseItem(it.id, it.name) }
                    UiGroupedExercises(muscleGroup = muscleGroup, exercises = uiExercises)
                }
            )
        }
    }

}
