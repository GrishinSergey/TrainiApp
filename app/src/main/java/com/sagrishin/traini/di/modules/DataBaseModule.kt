package com.sagrishin.traini.di.modules

import android.content.Context
import androidx.room.Room
import com.sagrishin.traini.BuildConfig
import com.sagrishin.traini.data.database.AppDataBase
import com.sagrishin.traini.data.database.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun providesDataBaseInstance(context: Context): AppDataBase {
        return Room.databaseBuilder(context, AppDataBase::class.java, BuildConfig.APP_DATABASE_NAME).build()
    }

    @Provides
    fun provideTrainingsDao(dataBase: AppDataBase): TrainingsDao {
        return dataBase.trainingsDao
    }

    @Provides
    fun provideMusclesDao(dataBase: AppDataBase): MusclesDao {
        return dataBase.musclesDao
    }

    @Provides
    fun provideExercisesDao(dataBase: AppDataBase): ExercisesDao {
        return dataBase.exercisesDao
    }

    @Provides
    fun provideExerciseRepetitionDao(dataBase: AppDataBase): ExerciseRepetitionDao {
        return dataBase.exerciseRepetitionDao
    }

    @Provides
    fun provideTrainingExerciseLinkDao(dataBase: AppDataBase): TrainingExerciseLinkDao {
        return dataBase.trainingExerciseLinkDao
    }

}
