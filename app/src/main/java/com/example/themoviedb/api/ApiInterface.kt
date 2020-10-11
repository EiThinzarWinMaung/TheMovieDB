package com.example.themoviedb.api

import com.example.themoviedb.model.TopRated
import com.example.themoviedb.model.biography.Biography
import com.example.themoviedb.model.cast.Cast
import com.example.themoviedb.model.details.Details
import com.example.themoviedb.model.nowplaying.NowPlaying
import com.example.themoviedb.model.popular.Popular
import com.example.themoviedb.model.recommendation.Recommendation
import com.example.themoviedb.model.reviews.Reviews
import com.example.themoviedb.model.similar.Similar
import com.example.themoviedb.model.upcoming.Upcoming
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {


    @GET("movie/top_rated")
    fun getTopRated(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<TopRated>

    @GET("movie/now_playing")
    fun getNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<NowPlaying>

    @GET("movie/popular")
    fun getPopular(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<Popular>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<Upcoming>

    @GET("movie/{movie_id}")
    fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Details>

    @GET("movie/{movie_id}/credits")
    fun getCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Cast>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendation(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<Recommendation>

    @GET("movie/{movie_id}/similar")
    fun getSimilar(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<Similar>

    @GET("movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Call<Reviews>

    @GET("person/{person_id}")
    fun getBiography(
        @Path("person_id") personId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<Biography>
}