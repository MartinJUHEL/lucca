package com.martin.network.interceptor

import com.martin.lucca.core.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

private const val AUTH_HEADER = "Authorization"
private const val VALUE_HEADER_AUTHORIZATION_PREFIX = "Lucca application="

class AuthorizationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Add the Authorization header to the request
        val modifiedRequest = originalRequest.newBuilder()
            .header(AUTH_HEADER, "$VALUE_HEADER_AUTHORIZATION_PREFIX${BuildConfig.API_KEY}")
            .build()

        return chain.proceed(modifiedRequest)
    }
}