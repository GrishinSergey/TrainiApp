package com.sagrishin.traini.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

//    @Provides
//    @Singleton
//    fun provideSecurePrefs(context: Context): SharedPreferences {
//        return EncryptedSharedPreferences.create(
//            "FileName",
//            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
//            context.applicationContext,
//            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//        )
//    }

}
