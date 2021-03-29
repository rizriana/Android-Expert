package com.dicoding.tourismapp.core.data.source.remote.network

import com.dicoding.tourismapp.core.data.source.remote.response.ListTourismResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
interface ApiService {
    @GET("list")
    fun getList(): Call<ListTourismResponse>
}