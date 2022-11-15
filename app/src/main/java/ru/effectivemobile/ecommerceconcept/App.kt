package ru.effectivemobile.ecommerceconcept

import android.app.Application
import ru.effectivemobile.ecommerceconcept.di.AppComponent
import ru.effectivemobile.ecommerceconcept.di.DaggerAppComponent

class App : Application() {
    private var _appComponent: AppComponent? = null
    val applicationComponent: AppComponent
        get() = requireNotNull(_appComponent) {
            "App component mus not be null"
        }

    override fun onCreate() {
        super.onCreate()

        _appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }
}