package com.dea.mygithubapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.Resource
import com.dea.mygithubapp.data.response.UserResponse
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.repo.MainRepository
import com.dea.mygithubapp.utill.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val listUsersFollowers = MutableLiveData<ArrayList<UsersResponseItem>>()
    val listUsersFollowing = MutableLiveData<ArrayList<UsersResponseItem>>()
    val checkUser = MutableLiveData<UsersResponseItem>()
    val userDetail = MutableLiveData<UserResponse>()

    fun onFetchFollowers(url: String) {
        viewModelScope.launch {
            val usersResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response = repository.getUsersFollowers("${url}?per_page=100")) {
                is Resource.Success -> {
                    response.data?.forEach {
                        usersResponse.add(it)
                    }
                    listUsersFollowers.postValue(usersResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun getUserDetail(url: String) {
        viewModelScope.launch {
            when (val response = repository.getUserByUsername(url)) {
                is Resource.Success -> {
                    response.data?.let {
                        userDetail.postValue(it)
                    }
                }
                is Resource.Error -> {

                }
            }
        }
    }

    fun onFetchFollowing(url: String) {
        viewModelScope.launch {
            val usersResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response =
                repository.getUsersFollowing("${Constant.BASE_URL}users/$url/following?per_page=100")) {
                is Resource.Success -> {
                    response.data?.forEach {
                        usersResponse.add(it)
                    }
                    listUsersFollowing.postValue(usersResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}