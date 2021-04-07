package com.dea.mygithubapp.data

import com.dea.mygithubapp.data.response.*
import retrofit2.Response
import retrofit2.http.*

interface GithubUserApiService {
    @GET("users")
    suspend fun getUsers (
        @Header("Accept") accept: String,
        @Header("Authorization") auth: String
    ): Response<UsersResponse>

    @GET
    suspend fun getUserByUsername(
        @Url url: String?,
        @Header("Accept") accept: String,
        @Header("Authorization") auth: String
    ): Response<UserResponse>

    @GET
    suspend fun searchUserByUsername(
        @Url url: String?,
        @Header("Accept") accept: String,
        @Header("Authorization") auth: String
    ): Response<SearchUserResponse>

    @GET
    suspend fun getUsersFollowers(
        @Url url: String?,
        @Header("Accept") accept: String,
        @Header("Authorization") auth: String
    ): Response<UsersResponse>

    @GET
    suspend fun getUsersFollowing(
        @Url url: String?,
        @Header("Accept") accept: String,
        @Header("Authorization") auth: String
    ): Response<UsersResponse>
}