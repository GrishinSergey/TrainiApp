package com.sagrishin.traini.domain.repositories

import com.sagrishin.traini.data.database.entities.MuscleEntity
import com.sagrishin.traini.domain.models.DomainMuscle

interface MusclesRepository {

    suspend fun getAllMuscles(): List<DomainMuscle>

    suspend fun getMusclesBy(ids: List<Long>): List<DomainMuscle>

    suspend fun getMuscleBy(id: Long): DomainMuscle

    suspend fun createMuscles(musclesToSave: List<MuscleEntity>)

}
