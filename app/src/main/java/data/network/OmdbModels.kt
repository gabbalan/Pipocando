package com.example.pipocando_oficial.data.network

import com.google.gson.annotations.SerializedName

data class SearchResult(
    @SerializedName("Search") val items: List<OmdbShort>?,
    @SerializedName("totalResults") val total: String?,
    @SerializedName("Response") val response: String?,
    @SerializedName("Error") val error: String?
)

data class OmdbShort(
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String?,
    @SerializedName("Poster") val poster: String?
)

data class OmdbDetail(
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String?,
    @SerializedName("Poster") val poster: String?,
    @SerializedName("Plot") val plot: String?,
    @SerializedName("imdbRating") val imdbRating: String?,
    @SerializedName("Response") val response: String?,
    @SerializedName("Error") val error: String?
)
