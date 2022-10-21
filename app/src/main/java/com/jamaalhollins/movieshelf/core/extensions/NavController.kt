package com.jamaalhollins.movieshelf.core.extensions

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.jamaalhollins.movieshelf.core.navigation.NavigationRouter

fun NavController.navigate(router: NavigationRouter) {
    navigate(router.deepLink.toUri())
}