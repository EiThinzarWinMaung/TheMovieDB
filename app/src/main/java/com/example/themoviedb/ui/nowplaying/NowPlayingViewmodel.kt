package com.example.themoviedb.ui.nowplaying

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.nowplaying.NowPlaying
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NowPlayingViewmodel: ViewModel() {

    private var nowplayingList: MutableLiveData<NowPlaying> = MutableLiveData()

    fun getNowPlaying(): LiveData<NowPlaying> = nowplayingList

    fun loadNowPlaying() {
        var apiClient = ApiClient()
        val apiCall = apiClient.getNowPlaying(
            ApiClient.API_KEY, "en-US", "1"
        )
        apiCall.enqueue(object: Callback<NowPlaying> {
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("Error >>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                nowplayingList.value = response.body()
                Log.d("Success >>>>>>>>", response.body().toString())
            }
        })
    }
}