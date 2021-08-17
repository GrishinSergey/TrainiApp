package com.sagrishin.traini.app

import android.app.Application
import com.sagrishin.traini.di.AppComponent
import com.sagrishin.traini.di.DaggerAppComponent
import com.sagrishin.uikit.utils.DateTimeFormatUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TrainiApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Any>
        internal set
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        DateTimeFormatUtils.init(this)

        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return injector
    }

}
