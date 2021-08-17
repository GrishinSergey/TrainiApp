package com.sagrishin.traini.di.modules

import androidx.lifecycle.ViewModel
import com.sagrishin.common.di.ActivityScoped
import com.sagrishin.common.di.ViewModelKey
import com.sagrishin.traini.presentation.MainActivity
import com.sagrishin.traini.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class MainActivityModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [TrainingsListModule::class])
    abstract fun mainActivity(): MainActivity


    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun mainViewModel(viewModel: MainViewModel): ViewModel

}
