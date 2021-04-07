package com.dea.mygithubapp

import com.dea.mygithubapp.data.GithubUserApiService
import com.dea.mygithubapp.data.response.*
import retrofit2.Response
import retrofit2.http.Url
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val githubUserApiService: GithubUserApiService
) {
    suspend fun getUsers(): Resource<UsersResponse> {
        githubUserApiService.getUsers("application/vnd.github.v3+json", BuildConfig.GITHUB_TOKEN)
            .let { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    return Resource.Success(it)
                }
            }
            return Resource.Error(response.message())
        }
    }

    suspend fun getUserByUsername(url: String): Resource<UserResponse> {
        githubUserApiService.getUserByUsername(url, "application/vnd.github.v3+json", BuildConfig.GITHUB_TOKEN)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        return Resource.Success(it)
                    }
                }
                return Resource.Error(response.message())
            }
    }

    suspend fun searchUserByUsername(url: String): Resource<SearchUserResponse> {
        githubUserApiService.searchUserByUsername(url, "application/vnd.github.v3+json", BuildConfig.GITHUB_TOKEN)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        return Resource.Success(it)
                    }
                }
                return Resource.Error(response.message())
            }
    }

    suspend fun getUsersFollowers(url: String): Resource<UsersResponse> {
        githubUserApiService.getUsersFollowers(url, "application/vnd.github.v3+json", BuildConfig.GITHUB_TOKEN)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        return Resource.Success(it)
                    }
                }
                return Resource.Error(response.message())
            }
    }

    suspend fun getUsersFollowing(url: String): Resource<UsersResponse> {
        githubUserApiService.getUsersFollowing(url, "application/vnd.github.v3+json", BuildConfig.GITHUB_TOKEN)
            .let { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        return Resource.Success(it)
                    }
                }
                return Resource.Error(response.message())
            }
    }
}