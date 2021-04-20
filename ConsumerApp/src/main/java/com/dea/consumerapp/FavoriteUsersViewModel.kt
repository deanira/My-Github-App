package com.dea.consumerapp

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteUsersViewModel : ViewModel() {

    private var listUser = MutableLiveData<ArrayList<UsersResponseItem>>()

    fun setFavorite(context: Context) {
        val cursor = context.contentResolver.query(
            DatabaseContract.FavoriteUserColumns.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        val listConverted = MappingHelper.mapCursorToArrayList(cursor)
        listUser.postValue(listConverted)
    }

    fun getFavoriteUsers(): LiveData<ArrayList<UsersResponseItem>> = listUser
}