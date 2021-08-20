package com.sagrishin.traini.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.sagrishin.traini.data.database.base.BaseDao
import com.sagrishin.traini.data.database.entities.TrainingExerciseLink
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TrainingExerciseLinkDao : BaseDao<TrainingExerciseLink>() {

    @Query("select * from TrainingExerciseLink where trainingId = :trainingId")
    abstract fun getLinksWithExercisesByFlow(trainingId: Long): Flow<List<TrainingExerciseLink>>

    @Query("select * from TrainingExerciseLink where trainingId in (:trainingIds)")
    abstract fun getLinksWithExercisesByFlow(trainingIds: List<Long>): Flow<List<TrainingExerciseLink>>

}
