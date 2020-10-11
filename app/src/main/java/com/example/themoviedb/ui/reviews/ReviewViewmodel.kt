package com.example.themoviedb.ui.reviews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.reviews.Reviews
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewmodel: ViewModel() {

    private var reviewResult: MutableLiveData<Reviews> = MutableLiveData()

    fun getReviews(): LiveData<Reviews> = reviewResult

    fun loadReviews(id: Int) {
        var apiClient = ApiClient()
        val apiCall = apiClient.getReviews(
            id, ApiClient.API_KEY, "en-US", "1"
        )

        apiCall.enqueue(object: Callback<Reviews> {
            override fun onFailure(call: Call<Reviews>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Reviews>, response: Response<Reviews>) {
                reviewResult.value = response.body()
            }

        })
    }
}