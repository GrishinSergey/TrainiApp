package com.sagrishin.traini.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sagrishin.traini.BuildConfig
import com.sagrishin.traini.data.database.converters.DateTimeConverters
import com.sagrishin.traini.data.database.converters.MuscleGroupConverters
import com.sagrishin.traini.data.database.dao.*
import com.sagrishin.traini.data.database.entities.*

@Database(
    version = BuildConfig.APP_DATABASE_VERSION,
    entities = [
        TrainingEntity::class,
        MuscleEntity::class,
        ExerciseEntity::class,
        TrainingExerciseLink::class,
        ExerciseRepetitionEntity::class,
    ],
)
@TypeConverters(
    value = [
        DateTimeConverters::class,
        MuscleGroupConverters::class
    ]
)
abstract class AppDataBase : RoomDatabase() {

    abstract val trainingsDao: TrainingsDao
    abstract val exercisesDao: ExercisesDao
    abstract val musclesDao: MusclesDao
    abstract val exerciseRepetitionDao: ExerciseRepetitionDao
    abstract val trainingExerciseLinkDao: TrainingExerciseLinkDao

}
