package com.sagrishin.traini.domain.usecases

import com.sagrishin.traini.domain.repositories.TrainingsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import javax.inject.Inject

class TrainingActionsUseCase @Inject constructor(
    private val trainingsRepository: TrainingsRepository
) {

    suspend fun createTrainingAt(scheduledDateTime: LocalDateTime): Long {
        return withContext(Dispatchers.IO) { trainingsRepository.createNewTraining(scheduledDateTime).id }
    }

}
