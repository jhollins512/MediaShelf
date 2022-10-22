package com.mediashelf.android.core.utils

enum class TMDBImageHelper(private val imageSizeParam: String) {
    W500("w500"),
    W780("w780");

    fun getTMDBImageUrl(path: String?): String {
        return if (path.isNullOrEmpty()) {
            ""
        } else {
            TMDB_IMAGE_BASE_URL + this.imageSizeParam + path
        }
    }

    companion object {
        private const val TMDB_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    }
}