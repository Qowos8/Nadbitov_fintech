package com.example.nadbitov_fintech.data.api

import com.example.nadbitov_fintech.data.api.Detail
import com.example.nadbitov_fintech.data.api.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkInterface {
    @GET("api/v2.2/films/top")
    suspend fun getPopularFilms(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int = 1
    ): PopularResponse

    @GET("api/v2.2/films/{movie_id}")
    suspend fun getFilmDetails(
        @Path("movie_id") movieId: Int,
        //@Query("type") type: String = "TOP_100_POPULAR_FILMS",
    ): Detail
}