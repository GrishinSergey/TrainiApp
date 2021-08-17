package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.domain.models.DomainExercise
import kotlinx.coroutines.flow.Flow

interface ExercisesRepository {

    suspend fun getExercisesBy(ids: List<Long>): List<DomainExercise>

    fun getAllExerciseBy(): Flow<List<DomainExercise>>

    suspend fun getExerciseBy(id: Long): DomainExercise

}
