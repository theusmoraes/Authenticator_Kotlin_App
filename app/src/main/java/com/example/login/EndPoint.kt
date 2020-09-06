package com.example.login
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EndPoint {
    @POST("login")
    fun login(@Body body: UserLoginModel): Call<String>
    @POST("register")
    fun register(@Body body:RegisterUser): Call<String>
}