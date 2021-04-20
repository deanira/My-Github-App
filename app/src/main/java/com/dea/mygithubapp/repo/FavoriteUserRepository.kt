package com.dea.mygithubapp.repo

import com.dea.mygithubapp.data.local.FavoriteUserDao
import com.dea.mygithubapp.data.response.UsersResponseItem
import javax.inject.Inject

class FavoriteUserRepository @Inject constructor(
    private val userDao: FavoriteUserDao
) {
    suspend fun addToFavorite(user: UsersResponseItem) = userDao.addToFavorite(user)

    fun getFavoriteUsers() = userDao.getFavoriteUsers()

    suspend fun checkUser(id: Int) = userDao.checkUser(id)

    suspend fun removeFromFavorite(user: UsersResponseItem) = userDao.removeFromFavorite(user)
}