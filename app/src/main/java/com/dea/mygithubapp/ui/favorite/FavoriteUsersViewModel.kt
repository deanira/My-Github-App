package com.dea.mygithubapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.repo.FavoriteUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteUsersViewModel @Inject constructor(
    private val favoriteUserRepository: FavoriteUserRepository
) : ViewModel() {

    val checkUser = MutableLiveData<UsersResponseItem>()

    fun addToFavorite(user: UsersResponseItem) {
        viewModelScope.launch {
            favoriteUserRepository.addToFavorite(user)
        }
    }

    fun checkUser(id: Int) {
        viewModelScope.launch {
            checkUser.postValue(favoriteUserRepository.checkUser(id))
        }
    }

    fun removeFromFavorite(user: UsersResponseItem) {
        viewModelScope.launch {
            favoriteUserRepository.removeFromFavorite(user)
        }
    }

    fun getFavoriteUsers() = favoriteUserRepository.getFavoriteUsers()

}