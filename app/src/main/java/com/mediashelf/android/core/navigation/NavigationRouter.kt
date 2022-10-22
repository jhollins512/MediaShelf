package com.mediashelf.android.core.navigation

import com.mediashelf.android.core.domain.model.Media

sealed class NavigationRouter(val path: String) {

    class MediaRouter(media: Media) :
        NavigationRouter("${media.mediaType}/${media.id}")

    object SearchRouter : NavigationRouter("search")

}
