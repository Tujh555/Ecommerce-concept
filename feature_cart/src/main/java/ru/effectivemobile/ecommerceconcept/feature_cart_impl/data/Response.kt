package ru.effectivemobile.ecommerceconcept.feature_cart_impl.data

internal sealed class Response<T> {
    class Loading<T> : Response<T>()
    class Success<T>(val data: T) : Response<T>()
    class Failure<T>(val exception: Exception): Response<T>()
    class Idle<T> : Response<T>()
}
