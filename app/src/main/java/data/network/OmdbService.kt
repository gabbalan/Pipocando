package com.example.pipocando_oficial.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbService {
    @GET("/")
    suspend fun search(
        @Query("apikey") apiKey: String,
        @Query("s") query: String
    ): SearchResult

    @GET("/")
    suspend fun detail(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String,
        @Query("plot") plot: String = "short"
    ): OmdbDetail
}
