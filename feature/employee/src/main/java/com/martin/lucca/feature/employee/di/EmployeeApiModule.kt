package com.martin.lucca.feature.employee.di

import com.martin.lucca.core.network.BuildConfig
import com.martin.lucca.feature.employee.data.api.EmployeeApi
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
class EmployeeApiModule {

    @Singleton
    @Provides
    internal fun provideEmployeeApi(
        retrofitBuilder: Retrofit.Builder,
        httpClientBuilder: OkHttpClient.Builder,
        authorizationInterceptor: AuthorizationInterceptor
    ): EmployeeApi {
        val httpClient = httpClientBuilder.addInterceptor(authorizationInterceptor).build()

        return retrofitBuilder
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(EmployeeApi::class.java)
    }
}