package com.sagrishin.traini.data.repositories

import com.sagrishin.traini.data.database.dao.TrainingExerciseLinkDao
import com.sagrishin.traini.data.database.entities.TrainingExerciseLink
import com.sagrishin.traini.domain.models.DomainTrainingExerciseLink
import com.sagrishin.traini.domain.repositories.TrainingExerciseLinksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TrainingExerciseLinksRepositoryImpl @Inject constructor(
    private val trainingExerciseLinkDao: TrainingExerciseLinkDao
): TrainingExerciseLinksRepository {

    override suspend fun addExerciseTo(trainingId: Long, exerciseId: Long) {
        trainingExerciseLinkDao.create(TrainingExerciseLink(trainingId = trainingId, exerciseId = exerciseId))
    }

    override fun getTrainingExerciseLinksBy(trainingId: Long): Flow<List<DomainTrainingExerciseLink>> {
        return trainingExerciseLinkDao.getLinksWithExercisesByFlow(trainingId).map {
            it.map { DomainTrainingExerciseLink(it.id, it.trainingId, it.exerciseId) }
        }
    }

    override fun getTrainingExerciseLinksBy(trainingIds: List<Long>): Flow<List<DomainTrainingExerciseLink>> {
        return trainingExerciseLinkDao.getLinksWithExercisesByFlow(trainingIds).map {
            it.map { DomainTrainingExerciseLink(it.id, it.trainingId, it.exerciseId) }
        }
    }

}
