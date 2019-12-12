package com.example.movieshub.main.services.api_interface

import com.example.movieshub.main.models.MoviesAndTvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaceTV {
    @GET("movie/{tv_id}")
    fun getInfo(@Path("tv_id") id: Int, @Query("api_key") apiKey: String): Call<MoviesAndTvModel>
}