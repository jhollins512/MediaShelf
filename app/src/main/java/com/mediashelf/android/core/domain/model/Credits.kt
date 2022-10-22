package com.mediashelf.android.core.domain.model

data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
) {
    fun getDirectorName(): String {
        return crew.find { it.job == "Director" }?.name.orEmpty()
    }
}
