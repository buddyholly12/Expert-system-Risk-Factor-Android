package com.fileskripsi.skripsi

data class AnswerSheets(val Smoke:String,val smoke_qty:String, val Ldl:String,val Tensi:String,val Gender:String,val bmi : Double
                        ,val diabetes :String,val Sport : String,val Umur : String) {
    constructor():this("","","","","",0.0,"","",""){

    }
}