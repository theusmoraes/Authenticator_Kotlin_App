package com.example.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var etLogin = findViewById<EditText>(R.id.etEmail)
        var etPassword = findViewById<EditText>(R.id.etPassword)
        var etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        var etName = findViewById<EditText>(R.id.etName)
        var btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener{
            var login = etLogin.text.toString()
            var password = etPassword.text.toString()
            var confirmPassword = etConfirmPassword.text.toString()
            var name = etName.text.toString()
            var validar : Boolean = true
            if (login == ""){
                validar = false
                etLogin.error = "Campo Email não pode ser vazio"
            }
            if (password.length < 8){
                etPassword.error = "Campo Senha deve posuir pelo menos 8 caracteres"
                validar = false
            }
            if (name == ""){
                etName.error = "Campo nome não pode ser vazio"
                validar = false
            }
            if (confirmPassword != password){
                etConfirmPassword.error = "As duas senhas devem ser idênticas"
            }
            if (validar){
                register(login, password, name)
            }
        }
    }
    fun register(email: String, password: String, name: String){
        val retrofitClient = NetworkUtils.getRetrofitInstance("http://192.168.15.9:3000/")
        val endPoint = retrofitClient.create(EndPoint::class.java)
        val body = RegisterUser(email = email, password = password, name = name)

        val callback = endPoint.register(body)
        callback.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val resposta : String = response?.body().toString()
                Log.i("RESPONSE", resposta)
            }
        })
    }
}