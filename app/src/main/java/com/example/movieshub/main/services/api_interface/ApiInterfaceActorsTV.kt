package com.example.movieshub.main.services.api_interface

import com.example.movieshub.main.models.ActorsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterfaceActorsTV {
    @GET("tv/{tv_id}/credits")
    fun getInfo(@Path("tv_id") tvID: Int, @Query("api_key") apiKey: String): Call<ActorsModel>
}