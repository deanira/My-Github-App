package com.dea.mygithubapp.di

import com.dea.mygithubapp.data.GithubUserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Singleton
    @Provides
    fun provideGithubUserService(retrofit: Retrofit) =
        retrofit.create(GithubUserApiService::class.java)
}