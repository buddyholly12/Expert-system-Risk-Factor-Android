package com.fileskripsi.skripsi.Data_class_Value

data class AnswerSheets(val Smoke:String,val smoke_qty:String, val Ldl:Int,val Tensi:Int,val bmi : Double,val Umur : Int
                        ,val Gender:String,val diabetes :String,val Sport : String,val stressval :String,val Cf:Double,val Hasil_backward:String) {
    constructor():this("","",0,0,0.0, 0, "","","","",0.0,""){

    }
}