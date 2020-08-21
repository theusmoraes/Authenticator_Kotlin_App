package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login()
    }
    fun login(){
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.15.9:3000/")
        val endPoint = retrofitClient.create(EndPoint::class.java)
        val body = UserLoginModel(user = "theusmoraes", password = "123456789")

        val callback = endPoint.login(body)
        callback.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val resposta : String = response?.body().toString()
                Log.i("RESPONSE", resposta)
            }
        })
    }
}