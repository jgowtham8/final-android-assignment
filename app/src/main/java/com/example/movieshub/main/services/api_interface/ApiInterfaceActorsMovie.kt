package com.example.movieshub.main.services.api_interface

import com.example.movieshub.main.models.ActorsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaceActorsMovie {
    @GET("movie/{movie_id}/credits")
    fun getInfo(@Path("movie_id") movieID: Int, @Query("api_key") apiKey: String): Call<ActorsModel>
}