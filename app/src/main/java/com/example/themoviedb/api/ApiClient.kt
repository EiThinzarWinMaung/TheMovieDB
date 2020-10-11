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
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val BASE_URL = "https://api.themoviedb.org/3/"

    companion object {
        val API_KEY = "f5b4b4a244942dc69732aa01528c2054"
    }

    private val apiInterface: ApiInterface

    init {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getTopRated(
        apiKey: String,
        language: String,
        page: String
    ): Call<TopRated> {
        return apiInterface.getTopRated(apiKey, language, page)
    }

    fun getNowPlaying(
        apiKey: String,
        language: String,
        page: String
    ): Call<NowPlaying> {
        return apiInterface.getNowPlaying(apiKey, language, page)
    }

    fun getPopular(
        apiKey: String,
        language: String,
        page: String
    ): Call<Popular> {
        return apiInterface.getPopular(apiKey, language, page)
    }

    fun getUpcoming(
        apiKey: String,
        language: String,
        page: String
    ): Call<Upcoming> {
        return apiInterface.getUpcoming(apiKey, language, page)
    }

    fun getDetails(
        movieId: Int,
        apiKey: String,
        language: String
    ): Call<Details> {
        return apiInterface.getDetails(movieId, apiKey, language)
    }

    fun getCast(
        movieId: Int,
        apiKey: String
    ): Call<Cast> {
        return apiInterface.getCast(movieId, apiKey)
    }

    fun getRecommendation(
        movieId: Int,
        apiKey: String,
        language: String,
        page: String
    ): Call<Recommendation> {
        return apiInterface.getRecommendation(movieId,apiKey, language, page)
    }

    fun getSimilar(
        movieId: Int,
        apiKey: String,
        language: String,
        page: String
    ): Call<Similar> {
        return apiInterface.getSimilar(movieId, apiKey, language, page)
    }

    fun getReviews(
        movieId: Int,
        apiKey: String,
        language: String,
        page: String
    ): Call<Reviews> {
        return apiInterface.getReviews(movieId, apiKey, language, page)
    }

    fun getBiography(
        personId: Int,
        apiKey: String,
        language: String
    ): Call<Biography> {
        return apiInterface.getBiography(personId, apiKey, language)
    }
}