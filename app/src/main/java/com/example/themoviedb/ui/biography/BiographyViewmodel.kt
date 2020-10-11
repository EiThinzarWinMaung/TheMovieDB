package com.example.themoviedb.ui.biography

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.biography.Biography
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BiographyViewmodel: ViewModel() {

    private var bioResult: MutableLiveData<Biography> = MutableLiveData()

    fun getBiography(): LiveData<Biography> = bioResult

    fun loadBiography(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getBiography(
            id, ApiClient.API_KEY, "en-US"
        )

        apiCall.enqueue(object: Callback<Biography> {
            override fun onFailure(call: Call<Biography>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Biography>, response: Response<Biography>) {
                bioResult.value = response.body()
            }

        })
    }
}