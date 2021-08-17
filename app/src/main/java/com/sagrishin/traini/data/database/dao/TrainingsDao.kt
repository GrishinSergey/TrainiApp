package com.sagrishin.traini.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sagrishin.traini.data.database.base.BaseDao
import com.sagrishin.traini.data.database.entities.TrainingEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
abstract class TrainingsDao : BaseDao<TrainingEntity>() {

    @Query("select * from TrainingEntity")
    abstract fun allFlow(): Flow<List<TrainingEntity>>

    @Query("select * from TrainingEntity")
    abstract suspend fun all(): List<TrainingEntity>

    @Query("select * from TrainingEntity where startDate between :start and :end")
    abstract suspend fun getTrainingsBetween(start: LocalDateTime, end: LocalDateTime): List<TrainingEntity>

    @Query("select * from TrainingEntity where startDate between :start and :end")
    abstract fun getTrainingsBetweenFlow(start: LocalDateTime, end: LocalDateTime): Flow<List<TrainingEntity>>

    @Query("select * from TrainingEntity where id = :trainingId")
    abstract suspend fun getTrainingBy(trainingId: Long): TrainingEntity

}
