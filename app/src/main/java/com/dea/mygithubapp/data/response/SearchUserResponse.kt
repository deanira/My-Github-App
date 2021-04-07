package com.dea.mygithubapp.data.response


import com.google.gson.annotations.SerializedName

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: MutableList<UsersResponseItem>,
    @SerializedName("total_count")
    val totalCount: Int
)