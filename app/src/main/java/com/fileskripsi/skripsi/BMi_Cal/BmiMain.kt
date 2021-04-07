package com.fileskripsi.skripsi.BMi_Cal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fileskripsi.skripsi.R

class BmiMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_main)
        supportActionBar?.hide()


        val mfragmentManager = supportFragmentManager
        val mfsatu =BMI_fragment()
        val fragment = mfragmentManager.findFragmentByTag(BMI_fragment::class.java.simpleName)

        mfragmentManager
            .beginTransaction()
            .add(R.id.frameContainer,mfsatu,BMI_fragment::class.java.simpleName)
            .commit()


    }
}