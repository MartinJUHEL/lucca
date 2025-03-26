package com.martin.lucca.feature.user.di

import com.martin.lucca.feature.user.data.service.UserService
import com.martin.lucca.feature.user.data.service.UserServiceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UserModule {

    @Singleton
    @Binds
    fun provideUserService(impl: UserServiceRemote): UserService
}