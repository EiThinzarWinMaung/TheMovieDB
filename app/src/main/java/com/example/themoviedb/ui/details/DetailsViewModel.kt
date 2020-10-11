package com.example.themoviedb.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.cast.Cast
import com.example.themoviedb.model.details.Details
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel: ViewModel() {

    private var topratedDetails: MutableLiveData<Details> = MutableLiveData()

    fun getDetails(): LiveData<Details> = topratedDetails

    fun loadDetails(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetails(
            id, ApiClient.API_KEY, "en-US"
        )

        apiCall.enqueue(object: Callback<Details> {
            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                topratedDetails.value = response.body()
                Log.d("Success >>>>>>>>>", response.toString())
            }

        })
    }
}