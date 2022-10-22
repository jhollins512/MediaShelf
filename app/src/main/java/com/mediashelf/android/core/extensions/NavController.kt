package com.mediashelf.android.core.extensions

import androidx.core.net.toUri
import androidx.navigation.NavController
import com.mediashelf.android.core.navigation.NavigationRouter

fun NavController.navigate(router: NavigationRouter) {
    navigate(String.format("mediashelf://%s", router.path).toUri())
}