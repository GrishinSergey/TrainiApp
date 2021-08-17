package com.sagrishin.traini.di.modules

import androidx.lifecycle.ViewModel
import com.sagrishin.common.di.FragmentScoped
import com.sagrishin.common.di.ViewModelKey
import com.sagrishin.traini.presentation.trainings.list.TrainingsListFragment
import com.sagrishin.traini.presentation.trainings.list.TrainingsListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class TrainingsListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun trainingsListFragment(): TrainingsListFragment


    @Binds
    @IntoMap
    @ViewModelKey(TrainingsListViewModel::class)
    abstract fun bindTrainingsListViewModel(viewModel: TrainingsListViewModel): ViewModel

}
