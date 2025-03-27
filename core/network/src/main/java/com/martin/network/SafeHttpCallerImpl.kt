package com.martin.network

import com.martin.lucca.core.common.network.ErrorInfo
import com.martin.lucca.core.common.network.FetchedResponse
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

internal const val HTTP_CODE_GENERIC_ERROR = -1
internal const val DEFAULT_ERROR_MESSAGE = "Error message not available."

class SafeHttpCallerImpl @Inject constructor() : SafeHttpCaller {

    override suspend fun <DTO, OUTPUT> call(
        action: suspend () -> Response<DTO>,
        transform: (DTO) -> OUTPUT,
    ): FetchedResponse<OUTPUT> {
        val response = try {
            // Performs the HTTP call.
            val result = action()
            val body = result.body() ?: throw HttpException(result)
            val output = transform(body)
            FetchedResponse.Success(output)
        } catch (e: HttpException) {
            return FetchedResponse.Error(
                ErrorInfo(e.code(), e.message())
            )
        } catch (e: Throwable) {
            e.printStackTrace()
            return FetchedResponse.Error(
                ErrorInfo(HTTP_CODE_GENERIC_ERROR, e.message ?: DEFAULT_ERROR_MESSAGE)
            )
        }

        return response
    }
}
