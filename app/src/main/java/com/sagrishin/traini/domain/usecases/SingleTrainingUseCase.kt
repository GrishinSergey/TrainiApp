package com.sagrishin.traini.domain.usecases

import com.sagrishin.common.utils.toMap
import com.sagrishin.common.utils.zipFlows
import com.sagrishin.traini.domain.converters.toUiModel
import com.sagrishin.traini.domain.repositories.*
import com.sagrishin.traini.presentation.uimodels.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SingleTrainingUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository,
    private val trainingExerciseLinksRepository: TrainingExerciseLinksRepository,
    private val exercisesRepository: ExercisesRepository,
    private val musclesRepository: MusclesRepository,
    private val repetitionsRepository: ExerciseRepetitionsRepository
) {

    suspend fun addExerciseTo(trainingId: Long, exerciseId: Long) {
        trainingExerciseLinksRepository.addExerciseTo(trainingId, exerciseId)
    }

    suspend fun getTrainingWithExercisesAndRepetitionsBy(trainingId: Long): Flow<UiTrainingWithExercisesAndRepetitions> {
        val uiTraining = withContext(Dispatchers.IO) {
            val training = trainingsRepository.getTrainingBy(trainingId)
            UiTrainingData(training.id, training.startDateTime)
        }

        return trainingExerciseLinksRepository.getTrainingExerciseLinksBy(trainingId).flatMapConcat { trainingExerciseLinks ->
            val exercisesMap = exercisesRepository.getExercisesBy(trainingExerciseLinks.map { it.exerciseId }).toMap { it.id to it }
            val musclesMap = musclesRepository.getMusclesBy(exercisesMap.values.map { it.muscleId }).toMap { it.id to it }

            trainingExerciseLinks.map { trainingExerciseLink ->
                val exercise = exercisesMap.getValue(trainingExerciseLink.exerciseId)
                val uiMuscle = musclesMap.getValue(exercise.muscleId).toUiModel()
                val uiExerciseData = UiExerciseData(exercise.id, exercise.name, uiMuscle)

                repetitionsRepository.getExerciseRepetitionsBy(trainingExerciseLink.id).map { repetitions ->
                    val uiRepetitions = repetitions.map { UiExerciseRepetition(it.id, it.weight, it.repetitionsCount) }
                    UiExerciseWithRepetitions(uiExerciseData, uiRepetitions)
                }.flowOn(Dispatchers.IO)
            }.zipFlows()
        }.map { exercises ->
            UiTrainingWithExercisesAndRepetitions(uiTraining, exercises)
        }.flowOn(Dispatchers.IO)
    }

}
