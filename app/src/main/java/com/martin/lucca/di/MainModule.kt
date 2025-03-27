package com.martin.lucca.di

import android.content.Context
import android.content.res.Resources
import com.martin.lucca.core.common.locale.getSupportedLocale
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.time.ZoneId
import java.util.Locale
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
/** This module is shared by all configuration (live, stub). */
class MainModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context

    @Singleton
    @Provides
    fun provideResources(context: Context): Resources = context.resources

    /** Useful especially for testing the VMs. */
    @Singleton
    @Provides
    fun provideLocale(): Locale = getSupportedLocale()

    /** Useful especially for testing the VMs. */
    @Singleton
    @Provides
    fun provideZoneId(): ZoneId = ZoneId.systemDefault()
}