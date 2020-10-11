package com.example.themoviedb.ui.recommendation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.recommendation.Recommendation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationViewmodel: ViewModel() {

    private var recommendResult: MutableLiveData<Recommendation> = MutableLiveData()

    fun getRecommendation(): LiveData<Recommendation> = recommendResult

    fun loadRecommendation(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getRecommendation(
            id, ApiClient.API_KEY, "en-US", "1"
        )

        apiCall.enqueue(object: Callback<Recommendation> {
            override fun onFailure(call: Call<Recommendation>, t: Throwable) {
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(
                call: Call<Recommendation>,
                response: Response<Recommendation>
            ) {
                recommendResult.value = response.body()
                Log.d("Success >>>>>>>>", response.body().toString())
            }

        })
    }
}