package com.sagrishin.traini.domain.usecases

import com.sagrishin.common.utils.zipFlows
import com.sagrishin.traini.domain.converters.toUiModel
import com.sagrishin.traini.domain.repositories.ExerciseRepetitionsRepository
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import com.sagrishin.traini.domain.repositories.MusclesRepository
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import com.sagrishin.traini.presentation.uimodels.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SingleTrainingUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository,
    private val exercisesRepository: ExercisesRepository,
    private val musclesRepository: MusclesRepository,
    private val repetitionsRepository: ExerciseRepetitionsRepository
) {

    fun getTrainingWithExercisesAndRepetitionsBy(trainingId: Long): Flow<UiTrainingWithExercisesAndRepetitions> {
        return trainingsRepository.getTrainingExerciseLinksBy(trainingId).flatMapConcat { trainingExerciseLinks ->
//            val exercises = exercisesRepository.getExercisesBy(trainingExerciseLinks.map { it.exerciseId })
//            val muscles = musclesRepository.getMusclesBy(exercises.map { it.muscleId }).toMap { it.id to it }
//
//            val uiExercises = exercises.map { exercise ->
//                val uiMuscle = muscles.getValue(exercise.muscleId).toUiModel()
//                val uiExerciseData = UiExerciseData(exercise.id, exercise.name, uiMuscle)
//            }


            trainingExerciseLinks.map { trainingExerciseLink ->
                val exercise = exercisesRepository.getExerciseBy(trainingExerciseLink.exerciseId)
                val uiMuscle = musclesRepository.getMuscleBy(exercise.muscleId).toUiModel()
                val uiExerciseData = UiExerciseData(exercise.id, exercise.name, uiMuscle)

                repetitionsRepository.getExerciseRepetitionsBy(trainingExerciseLink.id).map { repetitions ->
                    val uiRepetitions = repetitions.map { UiExerciseRepetition(it.id, it.weight, it.repetitionsCount) }
                    UiExerciseWithRepetitions(uiExerciseData, uiRepetitions)
                }.flowOn(Dispatchers.IO)
            }.zipFlows()
        }.map { exercises ->
            val training = trainingsRepository.getTrainingBy(trainingId)
            val uiTraining = UiTrainingData(training.id, training.startDateTime)
            UiTrainingWithExercisesAndRepetitions(uiTraining, exercises)
        }
    }

}
