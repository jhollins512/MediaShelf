package com.jamaalhollins.movieshelf.core.domain.model

import android.os.Parcel
import android.os.Parcelable

data class MovieDetails(
    val id: Int,
    val title: String,
    val overview: String,
    val backdropPath: String?,
    val posterPath: String?,
    val genres: List<Genre>,
    val homepage: String,
    val releaseDate: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty(),
        parcel.createTypedArrayList(Genre).orEmpty(),
        parcel.readString().orEmpty(),
        parcel.readString().orEmpty()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(backdropPath)
        parcel.writeString(posterPath)
        parcel.writeTypedList(genres)
        parcel.writeString(homepage)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDetails> {
        override fun createFromParcel(parcel: Parcel): MovieDetails {
            return MovieDetails(parcel)
        }

        override fun newArray(size: Int): Array<MovieDetails?> {
            return arrayOfNulls(size)
        }
    }
}
