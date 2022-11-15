package ru.effectivemobile.ecommerceconcept

import android.content.Context
import ru.effectivemobile.ecommerceconcept.di.AppComponent

val Context.appComponent: AppComponent
    get() = if (this is App) {
        applicationComponent
    } else {
        applicationContext.appComponent
    }
