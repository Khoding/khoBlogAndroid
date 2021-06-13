package xyz.khodok.khoblog

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PostApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: Application
            private set
    }

}
