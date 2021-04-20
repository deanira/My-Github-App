package com.dea.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object DatabaseContract {
    const val AUTHORITY = "com.dea.mygithubapp"
    const val SCHEME = "content"

    internal class FavoriteUserColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "favorite_users"
            const val ID = "id"
            const val AVATAR_URL = "avatarUrl"
            const val EVENTS_URL = "eventsUrl"
            const val FOLLOWERS_URL = "followersUrl"
            const val FOLLOWING_URL = "followingUrl"
            const val GISTS_URL = "gistsUrl"
            const val GRAVATAR_ID = "gravatarId"
            const val HTML_URL = "htmlUrl"
            const val LOGIN = "login"
            const val NODE_ID = "nodeId"
            const val ORGANIZATIONS_URL = "organizationsUrl"
            const val RECEIVED_EVENTS_URL = "receivedEventsUrl"
            const val REPOS_URL = "reposUrl"
            const val SITE_ADMIN = "siteAdmin"
            const val STARRED_URL = "starredUrl"
            const val SUBSCRIPTIONS_URL = "subscriptionsUrl"
            const val TYPE = "type"
            const val URL = "url"

            val CONTENT_URI  = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()
        }
    }
}