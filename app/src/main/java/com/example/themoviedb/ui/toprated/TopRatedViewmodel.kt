package com.example.themoviedb.ui.toprated

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.TopRated
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedViewmodel: ViewModel() {

    private var topratedList: MutableLiveData<TopRated> = MutableLiveData()

    fun getTopRated(): LiveData<TopRated> = topratedList

    fun loadTopRated() {
        var apiClientt = ApiClient()
        var apiCall = apiClientt.getTopRated(
            ApiClient.API_KEY, "en-US", "1"
        )
        apiCall.enqueue(object: Callback<TopRated> {
            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("Error >>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                topratedList.value = response.body()
                Log.d("Success >>>>>>>>", response.body().toString())
            }

        })
    }
}