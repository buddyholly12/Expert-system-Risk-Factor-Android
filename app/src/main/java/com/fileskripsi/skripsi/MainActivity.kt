package com.fileskripsi.skripsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.fileskripsi.skripsi.LoginRegistUI.loginRegist

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        Handler().postDelayed({ val intent = Intent(this@MainActivity,loginRegist::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}