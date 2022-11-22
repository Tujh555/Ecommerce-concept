package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.modules

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class KeyViewModel(val key: KClass<out ViewModel>)
