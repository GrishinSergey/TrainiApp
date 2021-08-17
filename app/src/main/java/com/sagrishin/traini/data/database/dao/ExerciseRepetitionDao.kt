package com.sagrishin.traini.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sagrishin.traini.data.database.entities.ExerciseRepetitionEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ExerciseRepetitionDao {

    @Query("select * from ExerciseRepetitionEntity where trainingExerciseId = :trainingExerciseId")
    abstract fun getExerciseRepetitionsByFlow(trainingExerciseId: Long): Flow<List<ExerciseRepetitionEntity>>

    @Query("select * from ExerciseRepetitionEntity where trainingExerciseId in (:ids)")
    abstract fun getExerciseRepetitionsByFlow(ids: List<Long>): Flow<List<ExerciseRepetitionEntity>>

}
