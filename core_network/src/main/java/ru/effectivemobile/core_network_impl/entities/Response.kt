package ru.effectivemobile.core_network_impl.entities

/**
 * The class represents 4 response states from the server:
 *
 * @property Idle - loading hasn't started yet
 * @property Loading - loading has begun
 * @property Failure - the download is broken.
 * @property Success - the download was successful
 */
sealed class Response<T> {
    class Loading<T> : Response<T>()
    class Success<T>(val data: T) : Response<T>()
    class Failure<T>(val exception: Exception) : Response<T>()
    class Idle<T> : Response<T>()
}
