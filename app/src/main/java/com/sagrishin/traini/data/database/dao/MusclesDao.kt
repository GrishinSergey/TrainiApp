package com.sagrishin.traini.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sagrishin.traini.data.database.base.BaseDao
import com.sagrishin.traini.data.database.entities.MuscleEntity

@Dao
abstract class MusclesDao : BaseDao<MuscleEntity>() {

    @Query("select * from MuscleEntity where id in (:ids)")
    abstract suspend fun allBy(ids: List<Long>): List<MuscleEntity>

    @Query("select * from MuscleEntity")
    abstract suspend fun all(): List<MuscleEntity>

    @Query("select * from MuscleEntity where id = :id")
    abstract fun singleBy(id: Long): MuscleEntity

}
