package com.fileskripsi.skripsi.History

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fileskripsi.skripsi.R

class History_data : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_data)
        supportActionBar?.hide()
    }
}