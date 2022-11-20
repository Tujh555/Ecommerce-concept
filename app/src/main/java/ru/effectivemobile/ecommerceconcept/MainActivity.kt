package ru.effectivemobile.ecommerceconcept

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di.HomePageComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesDependencyProvider
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var deps: HomePageFeatureDependencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        HomePageComponentHolder.init(deps)

        Log.d("MyLogs", "${ru.effectivemobile.ecommerceconcept.navigation.R.id.host_main}, ${ru.effectivemobile.ecommerceconcept.navigation.R.id.host_global}")
        Log.d("MyLogs", "${ru.effectivemobile.ecommerceconcept.feature_cart.R.id.action_global_navigate_to_cartFragment}, ${ru.effectivemobile.ecommerceconcept.feature_phones.R.id.action_global_to_phonesFragment}")
    }
}
