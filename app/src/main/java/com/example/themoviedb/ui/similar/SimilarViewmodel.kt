package com.example.themoviedb.ui.similar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.similar.Similar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SimilarViewmodel: ViewModel() {

    private var similarResult: MutableLiveData<Similar> = MutableLiveData()

    fun getSimilar(): LiveData<Similar> = similarResult

    fun loadSimilar(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getSimilar(
            id, ApiClient.API_KEY, "en-US", "1"
        )

        apiCall.enqueue(object: Callback<Similar> {
            override fun onFailure(call: Call<Similar>, t: Throwable) {
                Log.d("Error >>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Similar>, response: Response<Similar>) {
                similarResult.value = response.body()
            }
        })
    }
}