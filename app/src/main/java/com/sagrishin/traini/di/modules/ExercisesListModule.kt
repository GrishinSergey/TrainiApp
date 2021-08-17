package com.sagrishin.traini.di.modules

import androidx.lifecycle.ViewModel
import com.sagrishin.common.di.FragmentScoped
import com.sagrishin.common.di.ViewModelKey
import com.sagrishin.traini.presentation.exercises.ExercisesSelectorBottomSheet
import com.sagrishin.traini.presentation.exercises.ExercisesSelectorViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ExercisesListModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun exercisesSelectorBottomSheetDialogFragment(): ExercisesSelectorBottomSheet


    @Binds
    @IntoMap
    @ViewModelKey(ExercisesSelectorViewModel::class)
    abstract fun bindExercisesSelectorViewModel(viewModel: ExercisesSelectorViewModel): ViewModel

}
