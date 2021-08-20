package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.domain.models.DomainTraining
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TrainingsRepository {

    suspend fun createNewTraining(scheduledDateTime: LocalDateTime): DomainTraining

    fun getTrainingsBetween(start: LocalDateTime, end: LocalDateTime): Flow<List<DomainTraining>>

    suspend fun loadTrainingsBetween(start: LocalDateTime, end: LocalDateTime): List<DomainTraining>

    suspend fun getTrainingsAt(dateTime: LocalDateTime): List<DomainTraining>

    suspend fun getTrainingBy(trainingId: Long): DomainTraining

}
