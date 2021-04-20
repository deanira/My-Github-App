package com.dea.mygithubapp.di

import android.content.Context
import androidx.room.Room
import com.dea.mygithubapp.data.local.FavoriteUserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ApplicationModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteUserDatabase::class.java,
        "favorite_users.db"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: FavoriteUserDatabase) = db.favoriteUserDao()
}