package com.sagrishin.traini.di.modules

import androidx.lifecycle.ViewModelProvider
import com.sagrishin.common.viewmodels.ViewModelFactoryImpl
import com.sagrishin.traini.di.modules.TrainingDetailsModule.TrainingDetailsFragmentModule
import dagger.Binds
import dagger.Module

@Module(includes = [
    MainActivityModule::class,
    TrainingsListModule::class,
    ExercisesListModule::class,
    TrainingDetailsFragmentModule::class,
])
abstract class ViewModelsModule {

    @Binds
    abstract fun viewModelFactory(factory: ViewModelFactoryImpl): ViewModelProvider.Factory

}
