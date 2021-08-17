package com.sagrishin.traini.data.repositories

import com.sagrishin.traini.data.database.dao.ExercisesDao
import com.sagrishin.traini.domain.models.DomainExercise
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExercisesRepositoryImpl @Inject constructor(
    private val exercisesDao: ExercisesDao
) : ExercisesRepository {

    override suspend fun getExercisesBy(ids: List<Long>): List<DomainExercise> {
        return exercisesDao.allBy(ids).map { DomainExercise(it.id, it.name, it.muscleId) }
    }

    override fun getAllExerciseBy(): Flow<List<DomainExercise>> {
        return exercisesDao.allFlow().map { it.map { DomainExercise(it.id, it.name, it.muscleId) } }
    }

    override suspend fun getExerciseBy(id: Long): DomainExercise {
        return exercisesDao.singleBy(id).let { DomainExercise(it.id, it.name, it.muscleId) }
    }

}
