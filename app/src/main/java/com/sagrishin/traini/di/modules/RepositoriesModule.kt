package com.sagrishin.traini.di.modules

import com.sagrishin.traini.data.repositories.ExerciseRepetitionsRepositoryImpl
import com.sagrishin.traini.data.repositories.ExercisesRepositoryImpl
import com.sagrishin.traini.data.repositories.MusclesRepositoryImpl
import com.sagrishin.traini.data.repositories.TrainingsRepositoryImpl
import com.sagrishin.traini.domain.repositories.ExerciseRepetitionsRepository
import com.sagrishin.traini.domain.repositories.ExercisesRepository
import com.sagrishin.traini.domain.repositories.MusclesRepository
import com.sagrishin.traini.domain.repositories.TrainingsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun bindTrainingsRepository(repository: TrainingsRepositoryImpl): TrainingsRepository

    @Binds
    abstract fun bindMusclesRepository(repository: MusclesRepositoryImpl): MusclesRepository

    @Binds
    abstract fun bindExercisesRepository(repository: ExercisesRepositoryImpl): ExercisesRepository

    @Binds
    abstract fun bindExerciseRepetitionsRepository(repository: ExerciseRepetitionsRepositoryImpl): ExerciseRepetitionsRepository

}
