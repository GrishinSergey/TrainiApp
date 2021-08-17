package com.sagrishin.traini.di

import android.app.Application
import com.sagrishin.traini.app.TrainiApplication
import com.sagrishin.traini.data.database.AppDataBase
import com.sagrishin.traini.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DataBaseModule::class,
        MainActivityModule::class,
        TrainingDetailsModule::class,
        RepositoriesModule::class,
        ViewModelsModule::class,
        ExercisesListModule::class
    ]
)
interface AppComponent : AndroidInjector<TrainiApplication> {

    val dataBase: AppDataBase

    override fun inject(instance: TrainiApplication)


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}
