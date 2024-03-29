package uz.gita.a2048.app

import android.app.Application
import timber.log.Timber
import uz.gita.a2048.BuildConfig

class App :Application() {
    companion object{
        lateinit var instance :App
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }




}