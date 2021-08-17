package com.sagrishin.traini.data.repositories

import com.sagrishin.traini.data.database.dao.ExerciseRepetitionDao
import com.sagrishin.traini.domain.models.DomainExerciseRepetition
import com.sagrishin.traini.domain.repositories.ExerciseRepetitionsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExerciseRepetitionsRepositoryImpl @Inject constructor(
    private val trainingRepetitionDao: ExerciseRepetitionDao
): ExerciseRepetitionsRepository {

    override fun getExerciseRepetitionsBy(trainingExerciseId: Long): Flow<List<DomainExerciseRepetition>> {
        return trainingRepetitionDao.getExerciseRepetitionsByFlow(trainingExerciseId).map {
            it.map { DomainExerciseRepetition(it.id, it.trainingExerciseId, it.weight, it.repetitionsCount) }
        }
    }

}
