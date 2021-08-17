package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.domain.models.DomainTraining
import com.sagrishin.traini.domain.models.DomainTrainingExerciseLink
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface TrainingsRepository {

    suspend fun createNewTraining(scheduledDateTime: LocalDateTime): DomainTraining

    fun getTrainingsBetween(start: LocalDateTime, end: LocalDateTime): Flow<List<DomainTraining>>

    suspend fun loadTrainingsBetween(start: LocalDateTime, end: LocalDateTime): List<DomainTraining>

    suspend fun getTrainingsAt(dateTime: LocalDateTime): List<DomainTraining>

    fun getTrainingExerciseLinksBy(trainingId: Long): Flow<List<DomainTrainingExerciseLink>>

    fun getTrainingExerciseLinksBy(trainingIds: List<Long>): Flow<List<DomainTrainingExerciseLink>>

    suspend fun getTrainingBy(trainingId: Long): DomainTraining

}
