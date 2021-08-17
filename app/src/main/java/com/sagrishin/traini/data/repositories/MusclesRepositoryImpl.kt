package com.sagrishin.traini.data.repositories

import com.sagrishin.traini.data.database.dao.MusclesDao
import com.sagrishin.traini.data.database.entities.MuscleEntity
import com.sagrishin.traini.domain.models.DomainMuscle
import com.sagrishin.traini.domain.repositories.MusclesRepository
import javax.inject.Inject

class MusclesRepositoryImpl @Inject constructor(
    private val musclesDao: MusclesDao
) : MusclesRepository {

    override suspend fun getAllMuscles(): List<DomainMuscle> {
        return musclesDao.all().map { getDomainMuscleFrom(it) }
    }

    override suspend fun getMusclesBy(ids: List<Long>): List<DomainMuscle> {
        return musclesDao.allBy(ids).map {
            getDomainMuscleFrom(it)
        }
    }

    override suspend fun getMuscleBy(id: Long): DomainMuscle {
        return getDomainMuscleFrom(musclesDao.singleBy(id))
    }

    override suspend fun createMuscles(musclesToSave: List<MuscleEntity>) {
        musclesDao.create(*musclesToSave.toTypedArray())
    }

    private fun getDomainMuscleFrom(it: MuscleEntity) = DomainMuscle(
        id = it.id,
        group = when (it.muscleGroup) {
            MuscleEntity.MuscleGroup.NECK -> DomainMuscle.Group.NECK
            MuscleEntity.MuscleGroup.TRAPEZE -> DomainMuscle.Group.TRAPEZE
            MuscleEntity.MuscleGroup.SHOULDERS -> DomainMuscle.Group.SHOULDERS
            MuscleEntity.MuscleGroup.CHEST -> DomainMuscle.Group.CHEST
            MuscleEntity.MuscleGroup.LATISSIMUS -> DomainMuscle.Group.LATISSIMUS
            MuscleEntity.MuscleGroup.BICEPS -> DomainMuscle.Group.BICEPS
            MuscleEntity.MuscleGroup.TRICEPS -> DomainMuscle.Group.TRICEPS
            MuscleEntity.MuscleGroup.FOREARM -> DomainMuscle.Group.FOREARM
            MuscleEntity.MuscleGroup.PRESS -> DomainMuscle.Group.PRESS
            MuscleEntity.MuscleGroup.BACK -> DomainMuscle.Group.BACK
            MuscleEntity.MuscleGroup.QUADRICEPS -> DomainMuscle.Group.QUADRICEPS
            MuscleEntity.MuscleGroup.LEG_BICEPS -> DomainMuscle.Group.LEG_BICEPS
            MuscleEntity.MuscleGroup.CALVES -> DomainMuscle.Group.CALVES
        }
    )

}
