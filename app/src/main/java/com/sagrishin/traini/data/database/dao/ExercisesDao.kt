package com.sagrishin.traini.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sagrishin.traini.data.database.base.BaseDao
import com.sagrishin.traini.data.database.entities.ExerciseEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExercisesDao : BaseDao<ExerciseEntity>() {

    @Query("select * from ExerciseEntity where id in (:ids)")
    abstract suspend fun allBy(ids: List<Long>): List<ExerciseEntity>

    @Query("select * from ExerciseEntity")
    abstract fun allFlow(): Flow<List<ExerciseEntity>>

    @Query("select * from ExerciseEntity where id = :id")
    abstract suspend fun singleBy(id: Long): ExerciseEntity

}
