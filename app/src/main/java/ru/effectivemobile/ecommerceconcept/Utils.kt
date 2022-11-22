package ru.effectivemobile.ecommerceconcept

import android.content.Context
import ru.effectivemobile.ecommerceconcept.di.AppComponent

/**
 * Property for getting application dagger component
 */
val Context.appComponent: AppComponent
    get() = if (this is App) {
        applicationComponent
    } else {
        applicationContext.appComponent
    }
