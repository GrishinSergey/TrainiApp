package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.domain.models.DomainExerciseRepetition
import kotlinx.coroutines.flow.Flow

interface ExerciseRepetitionsRepository {

    fun getExerciseRepetitionsBy(trainingExerciseId: Long): Flow<List<DomainExerciseRepetition>>

    fun getExerciseRepetitionsBy(ids: List<Long>): Flow<List<DomainExerciseRepetition>>

}
