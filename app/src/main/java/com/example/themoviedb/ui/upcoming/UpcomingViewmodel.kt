package com.example.themoviedb.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.upcoming.Upcoming
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingViewmodel: ViewModel() {

    private var upcomingResult: MutableLiveData<Upcoming> = MutableLiveData()

    fun getUpcoming(): LiveData<Upcoming> = upcomingResult

    fun loadUpcoming() {
        var apiClient = ApiClient()
        var apiCall = apiClient.getUpcoming(
            ApiClient.API_KEY, "en-US", "1"
        )
        apiCall.enqueue(object: Callback<Upcoming> {
            override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                upcomingResult.value = response.body()
                Log.d("Success >>>>>>>>", response.body().toString())
            }

        })
    }
}