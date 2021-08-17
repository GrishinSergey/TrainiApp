package com.sagrishin.traini.domain.usecases

import com.sagrishin.traini.domain.repositories.TrainingsRepository
import java.time.LocalDateTime
import javax.inject.Inject

class TrainingActionsUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository
) {

    suspend fun createTrainingAt(scheduledDateTime: LocalDateTime): Long {
        return trainingsRepository.createNewTraining(scheduledDateTime).id
    }

}
