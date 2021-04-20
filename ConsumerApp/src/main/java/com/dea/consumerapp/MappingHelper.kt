package com.dea.consumerapp

import android.database.Cursor

object MappingHelper {
    fun mapCursorToArrayList(cursor: Cursor?): ArrayList<UsersResponseItem> {
        val list = ArrayList<UsersResponseItem>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.ID))
                val avatarUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.AVATAR_URL))
                val eventsUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.EVENTS_URL))
                val followersUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.FOLLOWERS_URL))
                val followingUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.FOLLOWING_URL))
                val gistsUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.GISTS_URL))
                val gravatarId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.GRAVATAR_ID))
                val htmlUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.HTML_URL))
                val login = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.LOGIN))
                val nodeId = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.NODE_ID))
                val organizationsUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.ORGANIZATIONS_URL))
                val receivedEventsUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.RECEIVED_EVENTS_URL))
                val reposUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.REPOS_URL))
                val siteAdmin = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.SITE_ADMIN))
                val starredUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.STARRED_URL))
                val subscriptionsUrl = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.SUBSCRIPTIONS_URL))
                val type = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.TYPE))
                val url = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.FavoriteUserColumns.URL))

                list.add(
                    UsersResponseItem(
                        null,
                        avatarUrl,
                        eventsUrl,
                        followersUrl,
                        followingUrl,
                        gistsUrl,
                        gravatarId,
                        htmlUrl,
                        id,
                        login,
                        nodeId,
                        organizationsUrl,
                        receivedEventsUrl,
                        reposUrl,
                        siteAdmin,
                        starredUrl,
                        subscriptionsUrl,
                        type,
                        url
                    )
                )
            }
        }
        return list
    }
}