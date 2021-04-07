package com.dea.mygithubapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.MainRepository
import com.dea.mygithubapp.Resource
import com.dea.mygithubapp.data.response.UsersResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchActivityViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val listUserSearchResult = MutableLiveData<ArrayList<UsersResponseItem>>()

    fun onSearchuser(url: String) {
        viewModelScope.launch {
            var searchUserResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response =
                repository.searchUserByUsername(url)) {
                is Resource.Success -> {
                    response.data?.items?.forEach {
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
                        searchUserResponse.add(user)
                    }
                    listUserSearchResult.postValue(searchUserResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}