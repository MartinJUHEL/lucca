package com.martin.network

import com.martin.lucca.core.common.network.FetchedResponse
import retrofit2.Response

/** Class to make HTTP calls, helps transform DTOs, and manage errors. Generic error is handled here too. */
interface SafeHttpCaller {

    /**
     * Performs an action (such as an HTTP call), but returns the result in a wrapped object
     * instead of throwing an exception.
     * @param DTO the object to be read object from HTTP (typically, a DTO).
     * @param OUTPUT the object to return (typically, a model).
     * @param action the action to perform to get a DTO (a HTTP call, for example).
     * @param transform the action to perform to convert the DTO to OUTPUT
     * (a toModel method for example).
     */
    suspend fun <DTO, OUTPUT> call(
        action: suspend () -> Response<DTO>,
        transform: (DTO) -> OUTPUT,
    ): FetchedResponse<OUTPUT>
}