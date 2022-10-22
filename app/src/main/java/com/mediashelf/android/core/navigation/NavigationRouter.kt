package com.mediashelf.android.core.navigation

import com.mediashelf.android.core.domain.model.Media

sealed class NavigationRouter(internal val deepLink: String) {

    companion object {
        private const val BASE_APP_NAMESPACE = "mediashelf://"
    }

    class MediaRouter(media: Media) :
        NavigationRouter("$BASE_APP_NAMESPACE${media.mediaType}/${media.id}")

    object SearchRouter : NavigationRouter("$BASE_APP_NAMESPACE/search")
}
