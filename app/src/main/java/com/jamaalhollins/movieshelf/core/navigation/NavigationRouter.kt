package com.jamaalhollins.movieshelf.core.navigation

import com.jamaalhollins.movieshelf.core.domain.model.Media

sealed class NavigationRouter(internal val deepLink: String) {

    companion object {
        private const val BASE_APP_NAMESPACE = "movieshelf://"
    }

    class MediaRouter(media: Media) :
        NavigationRouter("$BASE_APP_NAMESPACE${media.mediaType}/${media.id}")

    object SearchRouter : NavigationRouter("$BASE_APP_NAMESPACE/search")
}
