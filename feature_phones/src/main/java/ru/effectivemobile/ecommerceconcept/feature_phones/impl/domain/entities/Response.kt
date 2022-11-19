package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities

internal sealed class Response<T> {
    class Loading<T> : Response<T>()
    class Success<T>(val answer: T) : Response<T>()
    class Failure<T>(val exception: Exception): Response<T>()
}
