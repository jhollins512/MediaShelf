package com.jamaalhollins.movieshelf.core.data.api.model

import com.jamaalhollins.movieshelf.core.domain.model.Network
import com.jamaalhollins.movieshelf.core.domain.model.TVShowDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiTVShowDetails(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "created_by")
    val createdBy: List<ApiCreatedBy>,
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "genres")
    val genres: List<ApiGenre>,
    @Json(name = "homepage")
    val homepage: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "in_production")
    val inProduction: Boolean,
    @Json(name = "languages")
    val languages: List<String>,
    @Json(name = "last_air_date")
    val lastAirDate: String?,
    @Json(name = "last_episode_to_air")
    val lastEpisodeToAir: ApiLastEpisodeToAir,
    @Json(name = "name")
    val name: String?,
    @Json(name = "networks")
    val networks: List<ApiNetwork>,
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: ApiNextEpisodeToAir?,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int,
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "production_companies")
    val productionCompanies: List<ApiProductionCompany>,
    @Json(name = "production_countries")
    val productionCountries: List<ApiProductionCountry>,
    @Json(name = "seasons")
    val seasons: List<ApiSeason>,
    @Json(name = "spoken_languages")
    val spokenLanguages: List<ApiSpokenLanguage>,
    @Json(name = "status")
    val status: String?,
    @Json(name = "tagline")
    val tagline: String?,
    @Json(name = "type")
    val type: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
) {
    fun mapToDomain(): TVShowDetails {
        return TVShowDetails(
            id,
            overview ?: "",
            posterPath ?: "",
            backdropPath,
            firstAirDate ?: "",
            genres.map { it.mapToDomain() },
            homepage ?: "",
            name ?: "",
            networks.map { it.mapToDomain() })
    }
}

@JsonClass(generateAdapter = true)
data class ApiCreatedBy(
    @Json(name = "credit_id")
    val creditId: String?,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String?,
    @Json(name = "profile_path")
    val profilePath: String?
)

@JsonClass(generateAdapter = true)
data class ApiLastEpisodeToAir(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "production_code")
    val productionCode: String?,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "show_id")
    val showId: Int,
    @Json(name = "still_path")
    val stillPath: String?,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class ApiNetwork(
    @Json(name = "id")
    val id: Int,
    @Json(name = "logo_path")
    val logoPath: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "origin_country")
    val originCountry: String?
) {
    fun mapToDomain(): Network {
        return Network(id, logoPath ?: "", name ?: "")
    }
}

@JsonClass(generateAdapter = true)
data class ApiNextEpisodeToAir(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_number")
    val episodeNumber: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "production_code")
    val productionCode: String?,
    @Json(name = "runtime")
    val runtime: Any,
    @Json(name = "season_number")
    val seasonNumber: Int,
    @Json(name = "show_id")
    val showId: Int,
    @Json(name = "still_path")
    val stillPath: Any,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "vote_count")
    val voteCount: Int
)

@JsonClass(generateAdapter = true)
data class ApiProductionCountry(
    @Json(name = "iso_3166_1")
    val iso31661: String?,
    @Json(name = "name")
    val name: String?
)

@JsonClass(generateAdapter = true)
data class ApiSeason(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_count")
    val episodeCount: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int
)