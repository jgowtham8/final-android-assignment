package com.example.movieshub.main.services.retrofit

import com.example.movieshub.main.models.PopularMoviesTvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfacePopMovie {
    @GET("movie/popular")
    fun getInfo(@Query("api_key") apiKey: String): Call<PopularMoviesTvModel>
}