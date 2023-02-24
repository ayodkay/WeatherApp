package com.example.weatherapp.data

import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.Response

sealed class Status<out T> {
    class Loading<out T> : Status<T>()
    data class OnSuccess<out T>(val value: T) : Status<T>()
    data class OnError<out T>(val error: Error) : Status<T>()
}

/**
 * converts [Status.OnSuccess] to a lambda function
 */
fun <T : Any> Status<T>.onSuccess(callback: (T) -> Unit): Status<T> {
    if (this is Status.OnSuccess) {
        callback(this.value)
    }
    return this
}

/**
 * converts [Status.OnError] to a lambda function
 */
fun <T : Any> Status<T?>.onError(callback: (response: Error) -> Unit): Status<T?> {
    if (this is Status.OnError) {
        callback(this.error)
    }
    return this
}

/**
 * converts [Status.Loading] to a lambda function
 */
fun <T : Any> Status<T?>.loading(callback: () -> Unit): Status<T?> {
    if (this is Status.Loading) {
        callback()
    }
    return this
}


/**
 * Error class for handling general errors
 */
data class Error(
    val code: Int, val message: String? = null, val cause: Throwable? = null
) {
    constructor(throwable: Throwable) : this(0, throwable.localizedMessage, throwable)
}


/**
 * Error class for handling [Throwable] errors
 */
class ErrorThrowable(
    val code: Int,
    override val message: String? = null,
    private val throwable: Throwable? = null
) : Throwable(message) {
    fun toError() = Error(code, message, throwable)
}

/**
 * Converts any [T] to [Status.OnSuccess]
 */
fun <T> T.toSuccessStatus(): Status<T> = Status.OnSuccess(this)


/**
 * Converts [Throwable] to [Status.OnError]
 */
fun <T> Throwable.toErrorStatus(): Status.OnError<T> {
    return when (this) {
        is ErrorThrowable -> Status.OnError(this.toError())
        else -> Status.OnError(
            ErrorThrowable(
                -1,
                message,
                this
            ).toError()
        )
    }
}

/**
 * Converts retrofit [Response] to [Status.OnError]
 */
fun <T, U> Response<U>.toErrorStatus(): Status.OnError<T> =
    Status.OnError(Error(code(), errorBody()?.string() ?: message()))


/**
 * Handles response gotten from Api call
 */
fun <T, U> Flowable<Response<U>>.subscribeWithRelay(
    relay: BehaviorRelay<Status<T>>,
    errorBlock: (() -> Unit)? = null,
    block: (U) -> T
): Disposable =
    subscribe({ response ->
        if (response.isSuccessful) {
            response.body()?.let {
                relay.accept(block(it).toSuccessStatus())
            } ?: relay.accept(response.toErrorStatus())
            errorBlock?.invoke()
        } else {
            relay.accept(response.toErrorStatus())
            errorBlock?.invoke()
        }
    }, {
        relay.accept(it.toErrorStatus())
        errorBlock?.invoke()
    })
