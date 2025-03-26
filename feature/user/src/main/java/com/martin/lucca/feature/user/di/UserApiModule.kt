package com.martin.lucca.feature.user.di

import com.martin.lucca.core.network.BuildConfig
import com.martin.lucca.feature.user.data.api.UserApi
import com.martin.network.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserApiModule {

    @Singleton
    @Provides
    internal fun provideUserApi(
        retrofitBuilder: Retrofit.Builder,
        httpClientBuilder: OkHttpClient.Builder,
        authorizationInterceptor: AuthorizationInterceptor
    ): UserApi {
        val httpClient = httpClientBuilder.addInterceptor(authorizationInterceptor).build()

        return retrofitBuilder
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(UserApi::class.java)
    }
}