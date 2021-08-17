package com.sagrishin.traini.domain.usecases

import com.sagrishin.common.utils.atEndOfDay
import com.sagrishin.common.utils.zipFlows
import com.sagrishin.traini.domain.converters.toUiModel
import com.sagrishin.traini.domain.models.DomainMuscle
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import com.sagrishin.traini.domain.repositories.MusclesRepository
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import com.sagrishin.traini.presentation.uimodels.UiTrainingItem
import com.sagrishin.traini.presentation.uimodels.UiTrainingsList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TrainingsListUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository,
    private val exercisesRepository: ExercisesRepository,
    private val musclesRepository: MusclesRepository
) {

    fun loadTrainingsBy(date: LocalDate): Flow<UiTrainingsList> {
        val start = date.atStartOfDay()
        val end = date.atEndOfDay()

        return trainingsRepository.getTrainingsBetween(start, end).flatMapConcat { trainings ->
            trainings.map { training ->
                trainingsRepository.getTrainingExerciseLinksBy(training.id).map { link ->
                    val exercisesBy = exercisesRepository.getExercisesBy(link.map { it.exerciseId })
                    val musclesBy = musclesRepository.getMusclesBy(exercisesBy.map { it.muscleId }.distinct())

                    UiTrainingItem(
                        id = training.id,
                        dateTime = training.startDateTime,
                        muscleGroups = musclesBy.map(DomainMuscle::toUiModel),
                        exerciseNames = exercisesBy.map { it.name },
                    )
                }.flowOn(Dispatchers.IO)
            }.zipFlows().map { UiTrainingsList(it) }
        }
    }

}
