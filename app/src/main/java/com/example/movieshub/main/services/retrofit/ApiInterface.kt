package com.example.movieshub.main.services.retrofit

import com.example.movieshub.main.models.PopularMoviesModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("movie/popular")
    fun getInfo(@Query("api_key") apiKey: String): Call<PopularMoviesModel>
}