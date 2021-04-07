package com.dea.mygithubapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dea.mygithubapp.MainRepository
import com.dea.mygithubapp.Resource
import com.dea.mygithubapp.data.response.UserResponse
import com.dea.mygithubapp.data.response.UsersResponseItem
import com.dea.mygithubapp.utill.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailUserViewModel @Inject constructor(
    private val repository: MainRepository
): ViewModel() {
    val listUsersFollowers = MutableLiveData<ArrayList<UsersResponseItem>>()
    val listUsersFollowing = MutableLiveData<ArrayList<UsersResponseItem>>()
    val userDetail = MutableLiveData<UserResponse>()

    fun onFetchFollowers(url: String) {
        viewModelScope.launch {
            val usersResponse: ArrayList<UsersResponseItem> = arrayListOf()
            when (val response = repository.getUsersFollowers("${url}?per_page=100")) {
                is Resource.Success -> {
                    response.data?.forEach {
                        usersResponse.add(it)
//                        val user = UsersResponseItem(
//                            it.avatarUrl,
//                            it.eventsUrl,
//                            it.followersUrl,
//                            it.followingUrl,
//                            it.gistsUrl,
//                            it.gravatarId,
//                            it.htmlUrl,
//                            it.id,
//                            it.login,
//                            it.nodeId,
//                            it.organizationsUrl,
//                            it.receivedEventsUrl,
//                            it.reposUrl,
//                            it.siteAdmin,
//                            it.starredUrl,
//                            it.subscriptionsUrl,
//                            it.type,
//                            it.url
//                        )
//                        usersResponse.add(user)
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
                        userDetail.postValue(
                            UserResponse(
                                it.avatarUrl,
                                it.bio,
                                it.blog,
                                it.company,
                                it.createdAt,
                                it.email,
                                it.eventsUrl,
                                it.followers,
                                it.followersUrl,
                                it.following,
                                it.followingUrl,
                                it.gistsUrl,
                                it.gravatarId,
                                it.hireable,
                                it.htmlUrl,
                                it.id,
                                it.location,
                                it.login,
                                it.name,
                                it.nodeId,
                                it.organizationsUrl,
                                it.publicGists,
                                it.publicRepos,
                                it.receivedEventsUrl,
                                it.reposUrl,
                                it.siteAdmin,
                                it.starredUrl,
                                it.subscriptionsUrl,
                                it.twitterUsername,
                                it.type,
                                it.updatedAt,
                                it.url
                            )
                        )
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
            when (val response = repository.getUsersFollowing("${Constant.BASE_URL}users/$url/following?per_page=100")) {
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
                    listUsersFollowing.postValue(usersResponse)
                }
                is Resource.Error -> {

                }
            }
        }
    }
}