package com.example.s8130002Application2.di

import com.example.s8130002Application2.data.api.ApiService
import com.example.s8130002Application2.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(): ApiService {
        return RetrofitClient.apiService
    }
}
