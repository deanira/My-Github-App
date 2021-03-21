package com.dea.mygithubapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val username: String = "",
    val name: String = "",
    val location: String = "",
    val repository: Int = 0,
    val company: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val avatar: Int = 0
):Parcelable
