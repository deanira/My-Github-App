package com.dea.mygithubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dea.mygithubapp.data.response.UsersResponseItem

@Database(
    entities = [UsersResponseItem::class],
    version = 1
)

abstract class FavoriteUserDatabase : RoomDatabase() {
    abstract fun favoriteUserDao(): FavoriteUserDao
}
