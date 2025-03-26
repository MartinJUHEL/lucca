package com.martin.lucca.core.common.network

/** A generic HTTP Error information. */
data class ErrorInfo(
    val httpCode: Int,
    val message: String?,
)

/**
 * A generic response which can either be successful and have the returned object, or
 * fail and have an error.
 */
sealed interface FetchedResponse<out T> {
    data class Success<T>(val value: T) : FetchedResponse<T>

    /** Any error that can't be recovered. */
    data class Error(val error: ErrorInfo) : FetchedResponse<Nothing>
}