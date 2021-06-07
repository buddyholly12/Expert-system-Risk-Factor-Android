package com.fileskripsi.skripsi.Bmi_Calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.fileskripsi.skripsi.R
import kotlinx.android.synthetic.main.activity_calculator_bmi.*
import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.math.roundToInt


class Calculator_bmi : AppCompatActivity(), View.OnClickListener {
    private lateinit var berat_badan: EditText
    private lateinit var tinggi_badan: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator_bmi)
        supportActionBar?.hide()
        checker.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var sb = StringBuilder()
        val textdata: TextView = findViewById(R.id.Tv_results) as TextView
        berat_badan = findViewById(R.id.berat_badan)
        tinggi_badan = findViewById(R.id.Tinggi_badan1)
        val bb = berat_badan.text.toString().toDouble()
        val tinggi = tinggi_badan.text.toString().toDouble()
        val bmi = bb / tinggi / tinggi * 10000

        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        val bmi1 = bmi.roundToInt()
        Log.d("bmi", bmi.toString())
        val results =df.format(bmi).toDouble().toString()
        val results1= "%.2f".format(bmi).toDouble()
        sb.append(results1)
        textdata.text = results
    }


}