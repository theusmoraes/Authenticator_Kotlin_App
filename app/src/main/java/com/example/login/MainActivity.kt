package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val loginButton = findViewById<Button>(R.id.login);
        val etEmail = findViewById<EditText>(R.id.etEmail);
        val etPassword = findViewById<EditText>(R.id.etPassword);
        val tvRegister = findViewById<TextView>(R.id.tvRegister);
        tvRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        loginButton.setOnClickListener{
            login(etEmail.text.toString(), etPassword.text.toString())
        }
    }
    fun login(email: String, password: String){
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.15.9:3000/")
        val endPoint = retrofitClient.create(EndPoint::class.java)
        val body = UserLoginModel(user = email, password = password)

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