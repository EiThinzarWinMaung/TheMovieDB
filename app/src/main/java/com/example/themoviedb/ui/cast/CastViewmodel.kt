package com.example.themoviedb.ui.cast

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.themoviedb.api.ApiClient
import com.example.themoviedb.model.cast.Cast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CastViewmodel: ViewModel() {

    private var castList: MutableLiveData<Cast> = MutableLiveData()

    fun getCast(): LiveData<Cast> = castList

    fun loadCast(id: Int) {
        var apiClient = ApiClient()
        var apiCall = apiClient.getCast(
            id, ApiClient.API_KEY
        )

        apiCall.enqueue(object: Callback<Cast> {
            override fun onFailure(call: Call<Cast>, t: Throwable) {
                Log.d("Error >>>>>>>>>>", t.toString())
            }

            override fun onResponse(call: Call<Cast>, response: Response<Cast>) {
                castList.value = response.body()
            }
        })
    }
}