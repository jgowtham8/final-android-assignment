package com.example.movieshub.main.services.api_interface

import com.example.movieshub.main.models.MoviesAndTvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaceMovie {
    @GET("movie/{movie_id}")
    fun getInfo(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<MoviesAndTvModel>
}