package com.fileskripsi.skripsi.Backward_Session

import android.util.Log
import androidx.core.util.rangeTo

class Backward {

    fun backward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi : Double, Umur : Int
                 ,Gender:String, diabetes :String,Sport : String, stressval :String) {


        var sb = StringBuilder()

        var flag = true


        if (smoke_qty.isEmpty()) {
            if (Smoke == "Tidak" && Gender == "Perempuan") {
                flag
            }
            if (bmi in 18.5 rangeTo (24.9) && Umur <= 40) {
                flag
            }
            if (Sport == "Rutin" && diabetes == "Tidak") {
                flag
            }
            if (stressval == "Tidak") {
                flag
                Log.d("error", "anda memiliki resiko rendah  ")

            } else {
                Log.d("error", "jawaban anda kurang sesuai ")
            }
        }

    }
}