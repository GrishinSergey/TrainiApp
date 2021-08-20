package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.domain.models.DomainTrainingExerciseLink
import kotlinx.coroutines.flow.Flow

interface TrainingExerciseLinksRepository {

    suspend fun addExerciseTo(trainingId: Long, exerciseId: Long)

    fun getTrainingExerciseLinksBy(trainingId: Long): Flow<List<DomainTrainingExerciseLink>>

    fun getTrainingExerciseLinksBy(trainingIds: List<Long>): Flow<List<DomainTrainingExerciseLink>>

}
