package com.sagrishin.traini.domain.usecases

import com.sagrishin.common.utils.toMap
import com.sagrishin.traini.domain.converters.toUiModel
import com.sagrishin.traini.domain.repositories.ExerciseRepetitionsRepository
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import com.sagrishin.traini.domain.repositories.MusclesRepository
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import com.sagrishin.traini.presentation.uimodels.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SingleTrainingUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository,
    private val exercisesRepository: ExercisesRepository,
    private val musclesRepository: MusclesRepository,
    private val repetitionsRepository: ExerciseRepetitionsRepository
) {

    suspend fun getTrainingWithExercisesAndRepetitionsBy(trainingId: Long): Flow<UiTrainingWithExercisesAndRepetitions> {
        val training = trainingsRepository.getTrainingBy(trainingId)
        val uiTraining = UiTrainingData(training.id, training.startDateTime)

        return trainingsRepository.getTrainingExerciseLinksBy(trainingId).flatMapConcat { trainingExerciseLinks ->
            val trainingLinksMap = trainingExerciseLinks.toMap { it.id to it }
            val exercises = exercisesRepository.getExercisesBy(trainingExerciseLinks.map { it.exerciseId }).toMap { it.id to it }
            val muscles = musclesRepository.getMusclesBy(exercises.values.map { it.muscleId }).toMap { it.id to it }

            repetitionsRepository.getExerciseRepetitionsBy(trainingExerciseLinks.map { it.id }).map { repetitions ->
                repetitions.map {
                    val exercise = exercises.getValue(trainingLinksMap.getValue(it.trainingExerciseId).exerciseId)
                    val uiMuscle = muscles.getValue(exercise.muscleId).toUiModel()
                    val uiExerciseData = UiExerciseData(exercise.id, exercise.name, uiMuscle)
                    val uiRepetitions = repetitions.map { UiExerciseRepetition(it.id, it.weight, it.repetitionsCount) }

                    UiExerciseWithRepetitions(uiExerciseData, uiRepetitions)
                }
            }.map {
                UiTrainingWithExercisesAndRepetitions(uiTraining, it)
            }
        }
    }

}
