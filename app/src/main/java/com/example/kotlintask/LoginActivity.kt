package com.example.kotlintask

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

  private lateinit var login :EditText
  private lateinit var password : EditText
  private lateinit var btnLogin :Button
  var response = 0
    companion object {
        var loginActivity: Activity? = null
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        loginActivity = this

        binding()


        btnLogin.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            response = getLogin()
            if(response == 200){
               startActivity(intent)

            }
        }

    }

    private fun getLogin(): Int {
        var hashMap : HashMap<String , String>
                = HashMap()
        hashMap.put("username" , login.text.toString())
        hashMap.put("password" , password.text.toString())

        return  HTTP()
            .baseUrl(Constants.baseUrl + "login")
            .header(hashMap)
            .metode("POST")
            .connect()
            .responseCode
    }

    private fun binding(){
        btnLogin = findViewById(R.id.btnlogin)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
    }
}