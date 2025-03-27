package com.martin.lucca.feature.employee.di

import com.martin.lucca.feature.employee.data.service.EmployeeService
import com.martin.lucca.feature.employee.data.service.EmployeeServiceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface EmployeeModule {

    @Singleton
    @Binds
    fun provideEmployeeService(impl: EmployeeServiceRemote): EmployeeService
}