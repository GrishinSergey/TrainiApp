package com.sagrishin.traini.di.modules

import androidx.lifecycle.ViewModel
import com.sagrishin.common.di.ActivityScoped
import com.sagrishin.common.di.ViewModelKey
import com.sagrishin.traini.presentation.trainings.details.SingleTrainingNavActivity
import com.sagrishin.traini.presentation.trainings.details.TrainingDetailsFragment
import com.sagrishin.traini.presentation.trainings.details.TrainingDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Module
abstract class TrainingDetailsModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TrainingDetailsFragmentModule::class])
    abstract fun singleTrainingNavActivity(): SingleTrainingNavActivity


    @Module
    abstract class TrainingDetailsFragmentModule {

        @MustBeDocumented
        @Scope
        @Retention(AnnotationRetention.RUNTIME)
        annotation class TrainingDetailsScoped

        @TrainingDetailsScoped
        @ContributesAndroidInjector
        abstract fun trainingDetailsFragment(): TrainingDetailsFragment


        @Binds
        @IntoMap
        @ViewModelKey(TrainingDetailsViewModel::class)
        abstract fun bindTrainingDetailsViewModel(viewModel: TrainingDetailsViewModel): ViewModel
    }

}
