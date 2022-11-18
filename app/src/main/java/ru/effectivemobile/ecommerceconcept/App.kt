package ru.effectivemobile.ecommerceconcept

import android.app.Application
import android.util.Log
import ru.effectivemobile.ecommerceconcept.di.AppComponent
import ru.effectivemobile.ecommerceconcept.di.DaggerAppComponent
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import javax.inject.Inject
import javax.inject.Provider

class App : Application() {
    private var _appComponent: AppComponent? = null
    val applicationComponent: AppComponent
        get() = requireNotNull(_appComponent) {
            "App component mus not be null"
        }

    override fun onCreate() {
        super.onCreate()

        Log.d("MyLogs", "App component created")
        _appComponent = DaggerAppComponent.builder().build()
    }
}