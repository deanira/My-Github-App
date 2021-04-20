package com.dea.mygithubapp.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dea.mygithubapp.data.response.UsersResponseItem

@Dao
interface FavoriteUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorite(user: UsersResponseItem): Long

    @Query("SELECT * FROM favorite_users")
    fun getFavoriteUsers(): LiveData<List<UsersResponseItem>>

    @Query("SELECT * FROM favorite_users WHERE favorite_users.id = :id")
    suspend fun checkUser(id: Int): UsersResponseItem

    @Delete
    suspend fun removeFromFavorite(user: UsersResponseItem)

    @Query("SELECT * FROM favorite_users")
    fun findAll(): Cursor
}