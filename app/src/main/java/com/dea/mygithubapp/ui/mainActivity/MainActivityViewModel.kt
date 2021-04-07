package com.dea.mygithubapp.ui.mainActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.MainRepository
import com.dea.mygithubapp.Resource
import com.dea.mygithubapp.data.response.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val listUser = MutableLiveData<ArrayList<UsersResponseItem>>()

    fun onFetchUsers() {
        viewModelScope.launch {
            var usersResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response = repository.getUsers()) {
                is Resource.Success -> {
                    response.data?.forEach {
                        val user = UsersResponseItem(
                            it.avatarUrl,
                            it.eventsUrl,
                            it.followersUrl,
                            it.followingUrl,
                            it.gistsUrl,
                            it.gravatarId,
                            it.htmlUrl,
                            it.id,
                            it.login,
                            it.nodeId,
                            it.organizationsUrl,
                            it.receivedEventsUrl,
                            it.reposUrl,
                            it.siteAdmin,
                            it.starredUrl,
                            it.subscriptionsUrl,
                            it.type,
                            it.url
                        )
                        usersResponse.add(user)
                    }
                    listUser.postValue(usersResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}