package com.sagrishin.traini.di.modules

import com.sagrishin.traini.data.repositories.*
import com.sagrishin.traini.domain.repositories.*
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

    @Binds
    abstract fun bindTrainingExerciseLinksRepository(repository: TrainingExerciseLinksRepositoryImpl): TrainingExerciseLinksRepository

}
