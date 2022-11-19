package ru.effectivemobile.ecommerceconcept

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesDependencyProvider
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    @Inject
    lateinit var cartDependencies: CartFeatureDependencies
    @Inject
    lateinit var phonesDependencies: FeaturePhonesDependencies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)


        CartDependencyProvider.dependencies = cartDependencies
        PhonesDependencyProvider.dependencies = phonesDependencies
    }
}
