package com.bignerdrach.android.junior.api

import retrofit2.Call
import retrofit2.http.GET


interface GeekJokesApi {

    @GET("https://geek-jokes.sameerkumar.website/api")
    fun fetchContents(): Call<String>
}