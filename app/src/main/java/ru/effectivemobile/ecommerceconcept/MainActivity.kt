package ru.effectivemobile.ecommerceconcept

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var deps: Provider<CartFeatureDependencies>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        Log.d("MyLogs", "Activity on create")

        CartDependencyProvider.dependencies = deps.get()
    }

    override fun onStart() {
        super.onStart()

        Log.d("MyLogs", "Activity on start")
    }

    override fun onResume() {
        super.onResume()

        Log.d("MyLogs", "Activity on resume")
    }
}
