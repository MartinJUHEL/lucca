package com.martin.network.di

import com.martin.lucca.core.network.BuildConfig
import com.martin.network.SafeHttpCaller
import com.martin.network.SafeHttpCallerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val READ_TIMEOUT_S = 30L
private const val CONNECT_TIMEOUT_S = 30L

/**
 * Declares the building blocks to build any Retrofit API.
 * Ultimately, modules will ask for a [Retrofit.Builder], call its build method and
 * then create it.
 * The Http Client is also present from its Builder, to allow to add custom interceptors
 * on the desired APIs.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideHttpLogging() = HttpLoggingInterceptor().apply {
        // No logger in Release!
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else
            HttpLoggingInterceptor.Level.NONE
    }

    @Provides
    fun provideHttpClientBuilder(logger: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT_S, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT_S, TimeUnit.SECONDS)
            .addInterceptor(logger)

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
}

@Module
@InstallIn(SingletonComponent::class)
fun interface NetworkUtilModule {

    @Singleton
    @Binds
    fun provideSafeHttpCaller(impl: SafeHttpCallerImpl): SafeHttpCaller
}
