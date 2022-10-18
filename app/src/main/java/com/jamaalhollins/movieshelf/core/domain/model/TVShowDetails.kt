package com.jamaalhollins.movieshelf.core.domain.model

import android.os.Parcel
import android.os.Parcelable

data class TVShowDetails(
    val id: Int,
    val overview: String,
    val posterPath: String,
    val backdropPath: String?,
    val firstAirDate: String,
    val genres: List<Genre>,
    val homepage: String,
    val name: String,
    val networks: List<Network>,
    val originalLanguage: String,
    val numberOfEpisodes: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString(),
        parcel.readString().orEmpty(),
        parcel.createTypedArrayList(Genre) as List<Genre>,
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.createTypedArrayList(Network).orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(overview)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
        parcel.writeString(firstAirDate)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeString(name)
        parcel.writeTypedList(networks)
        parcel.writeString(originalLanguage)
        parcel.writeInt(numberOfEpisodes)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TVShowDetails> {
        override fun createFromParcel(parcel: Parcel): TVShowDetails {
            return TVShowDetails(parcel)
        }

        override fun newArray(size: Int): Array<TVShowDetails?> {
            return arrayOfNulls(size)
        }
    }
}