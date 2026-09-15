package com.example.s8130002Application2.di

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.repository.AuthRepository
import com.example.s8130002Application2.data.repository.DashboardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAuthRepository(apiService: ApiService): AuthRepository {
        return AuthRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideDashboardRepository(apiService: ApiService): DashboardRepository {
        return DashboardRepository(apiService)
    }
}
