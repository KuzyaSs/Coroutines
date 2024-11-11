package ru.otus.coroutines.second

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.otus.coroutines.second.di.appModule

class CoroutinesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CoroutinesApplication)
            modules(appModule)
        }
    }
}
