package com.misterjerry.mycontactstore

import android.app.Application
import com.misterjerry.mycontactstore.core.di.appModule
import com.misterjerry.mycontactstore.core.di.repositoryModule
import com.misterjerry.mycontactstore.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(
                appModule,
                viewModelModule,
                repositoryModule
            )
        }
    }

}