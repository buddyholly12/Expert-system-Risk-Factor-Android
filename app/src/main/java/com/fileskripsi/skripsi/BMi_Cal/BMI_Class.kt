package com.fileskripsi.skripsi.BMi_Cal

import kotlin.math.roundToInt

class BMI_Class {
    fun Hitung(Tinggi :Int,Beratbadan:Int):Int{
        val bmi = Beratbadan / Tinggi / Tinggi * 10000
        return bmi
    }
}