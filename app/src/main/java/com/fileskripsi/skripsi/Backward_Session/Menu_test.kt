package com.fileskripsi.skripsi.Backward_Session

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fileskripsi.skripsi.R
import com.fileskripsi.skripsi.Test_Ui.TestUI
import kotlinx.android.synthetic.main.activity_menu_test.*

class Menu_test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_test)
        supportActionBar?.hide()
        rendah_quiz.setOnClickListener{
            val Intent = Intent(this@Menu_test, TestUI::class.java)
            startActivity(Intent)
        }
        Sedang_quiz.setOnClickListener{
            val Intent = Intent(this@Menu_test, TestUI::class.java)
            startActivity(Intent)
        }
        TInggi_quiz.setOnClickListener{
            val Intent = Intent(this@Menu_test, TestUI::class.java)
            startActivity(Intent)
        }

    }
}