package com.example.themoviedb.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.popular.Popular
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopularViewmodel: ViewModel() {

    private var popularList: MutableLiveData<Popular> = MutableLiveData()

    fun getPopular(): LiveData<Popular> = popularList

    fun loadPopular() {
        var apiClient = ApiClient()
        val apiCall = apiClient.getPopular(
            ApiClient.API_KEY, "en-US", "1"
        )
        apiCall.enqueue(object: Callback<Popular> {
            override fun onFailure(call: Call<Popular>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                popularList.value = response.body()
            }
        })
    }
}