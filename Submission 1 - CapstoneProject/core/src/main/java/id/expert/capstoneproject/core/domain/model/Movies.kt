package id.expert.capstoneproject.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    val backdropPath: String,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Float,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Float,
    val voteCount: Int,
    val isFavorite: Boolean
) : Parcelable
