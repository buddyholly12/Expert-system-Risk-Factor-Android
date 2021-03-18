package com.fileskripsi.skripsi.LoginRegistUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.fileskripsi.skripsi.R
import com.fileskripsi.skripsi.RegistUI.RegistUI
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login_regist.*

class loginRegist : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_regist)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance();
        // Tombol _ Register
        btn_Register.setOnClickListener({
            val regist = Intent(this@loginRegist, RegistUI::class.java)
            startActivity(regist)
        })

        btn_login.setOnClickListener{
            if (email.text.toString().trim().isNotEmpty()|| Passwords.text.toString().trim().isNotEmpty()) {
                loginfunction(email.text.toString(),Passwords.text.toString().trim())
            }else{
                Toast.makeText(applicationContext, "input data ", Toast.LENGTH_SHORT).show()
            }

        }
    }
    private fun loginfunction(email:String,Password:String){
        auth.signInWithEmailAndPassword(email,Password).addOnCompleteListener(this) { task->
            if (task.isSuccessful){
                val Home = Intent(this@loginRegist, homeUI::class.java)
                startActivity(Home)

            }
            else{
                Log.e("Task message ", "Failed Login"+task.exception);
            }
        }

    }


}