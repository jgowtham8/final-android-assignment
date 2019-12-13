package com.example.movieshub.main.services.api_interface

import com.example.movieshub.main.models.ActorsModel
import com.example.movieshub.main.models.MoviesAndTvModel
import com.example.movieshub.main.models.PopularMoviesTvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/popular")
    fun getInfoPopMovie(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<PopularMoviesTvModel>

    @GET("tv/popular")
    fun getInfoPopTV(@Query("api_key") apiKey: String, @Query("page") page: Int): Call<PopularMoviesTvModel>

    @GET("movie/{movie_id}")
    fun getInfoMovie(@Path("movie_id") movieID: Int, @Query("api_key") apiKey: String): Call<MoviesAndTvModel>

    @GET("tv/{tv_id}")
    fun getInfoTV(@Path("tv_id") id: Int, @Query("api_key") apiKey: String): Call<MoviesAndTvModel>

    @GET("movie/{movie_id}/credits")
    fun getInfoActorsMovie(@Path("movie_id") movieID: Int, @Query("api_key") apiKey: String): Call<ActorsModel>

    @GET("tv/{tv_id}/credits")
    fun getInfoActorsTV(@Path("tv_id") tvID: Int, @Query("api_key") apiKey: String): Call<ActorsModel>
}