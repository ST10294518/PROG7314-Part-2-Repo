package com.winx.app.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    /*
     * 10.0.2.2 allows the Android Emulator
     * to access the host computer's localhost.
     *
     * Winx.API is running on port 5001.
     */
    private const val BASE_URL = "http://10.0.0.149:5001/"

    val api: WinxApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WinxApiService::class.java)
    }
}
