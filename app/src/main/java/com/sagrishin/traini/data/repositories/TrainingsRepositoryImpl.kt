package com.sagrishin.traini.data.repositories

import com.sagrishin.common.utils.atEndOfDay
import com.sagrishin.common.utils.atStartOfDay
import com.sagrishin.traini.data.database.dao.TrainingExerciseLinkDao
import com.sagrishin.traini.data.database.dao.TrainingsDao
import com.sagrishin.traini.data.database.entities.TrainingEntity
import com.sagrishin.traini.domain.models.DomainTraining
import com.sagrishin.traini.domain.models.DomainTrainingExerciseLink
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class TrainingsRepositoryImpl @Inject constructor(
    private val trainingsDao: TrainingsDao,
    private val trainingExerciseLinkDao: TrainingExerciseLinkDao
) : TrainingsRepository {

    override suspend fun createNewTraining(scheduledDateTime: LocalDateTime): DomainTraining {
        return getTrainingBy(trainingsDao.create(TrainingEntity(startDate = scheduledDateTime)))
    }

    override suspend fun getTrainingBy(trainingId: Long): DomainTraining {
        return trainingsDao.getTrainingBy(trainingId).let { DomainTraining(it.id, it.startDate) }
    }

    override fun getTrainingsBetween(start: LocalDateTime, end: LocalDateTime): Flow<List<DomainTraining>> {
        return trainingsDao.getTrainingsBetweenFlow(start, end).map { it.map { DomainTraining(it.id, it.startDate) } }
    }

    override suspend fun loadTrainingsBetween(start: LocalDateTime, end: LocalDateTime): List<DomainTraining> {
        return trainingsDao.getTrainingsBetween(start, end).map { DomainTraining(it.id, it.startDate) }
    }

    override suspend fun getTrainingsAt(dateTime: LocalDateTime): List<DomainTraining> {
        return trainingsDao.getTrainingsBetween(dateTime.atStartOfDay(), dateTime.atEndOfDay()).map {
            DomainTraining(it.id, it.startDate)
        }
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
