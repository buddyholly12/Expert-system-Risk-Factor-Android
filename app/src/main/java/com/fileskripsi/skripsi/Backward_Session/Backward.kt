package com.fileskripsi.skripsi.Backward_Session

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.core.util.rangeTo
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Backward {

   var auth: FirebaseAuth? = null
    var databaseReference :  DatabaseReference? = null

    fun backward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi : Double, Umur : Int
                 ,Gender:String, diabetes :String,Sport : String, stressval :String,Cf:Double) {

        val ref = FirebaseDatabase.getInstance().getReference("Processing_Data/BackwardRes")
        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        var sb = StringBuilder()
        var flag = true

        auth = FirebaseAuth.getInstance()
        val user = auth!!.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val datauser = user?.uid

        if (smoke_qty.isEmpty()) {
            if (Smoke == "Tidak" && Gender == "Perempuan") {
                flag
            }
            if(Ldl < 75 && Tensi <= 120)
            {
                flag
            }
            if (bmi in 18.5 rangeTo (24.9) && Umur <= 40) {
                flag
            }
            if (Sport == "Rutin" && diabetes == "Tidak") {
                flag
            }
            if (stressval == "Tidak" && Cf<= 62.0) {
                flag
                val results = " Resiko rendah"
                sb.append(results)
            }
            println(sb.toString())
        }
        else if (smoke_qty.isNotEmpty())
        {
            val results = "Jawaban anda kurang sesuai "
            sb.append(results)
            println(sb.toString())

        }
        val taskid = ref1.push().key
        if (taskid!=null)
        {
            Log.d("test",taskid)
            val dataBackward = AnswerSheets(taskid.toString(),Smoke,smoke_qty,Ldl,Tensi,bmi,Umur,Gender,diabetes,Sport,stressval,Cf,sb.toString())
            ref1.child(datauser.toString()).child(taskid).setValue(dataBackward)
        }

    }
    fun Medium_Backward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi : Double, Umur : Int
                        ,Gender:String, diabetes :String,Sport : String, stressval :String,Cf:Double){
        val ref = FirebaseDatabase.getInstance().getReference("Processing_Data/BackwardRes")
        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        var sb = StringBuilder()
        var flag = true

        auth = FirebaseAuth.getInstance()
        val user = auth!!.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val datauser = user?.uid

        if(smoke_qty.toInt()<=10)
        {
            if (Smoke.isEmpty() && Gender == "Perempuan") {
                flag
            }
            if(Ldl in 75 rangeTo (150) && Tensi in 120 rangeTo (140))
            {
                flag
            }
            if (bmi in 25.0 rangeTo (29.9) && Umur in 40 rangeTo (50)) {
                flag
            }
            if (Sport == "Jarang" && diabetes == "Ya") {
                flag
            }
            if (stressval == "Ya" && Cf<= 62.0) {
                flag
                val results = "Resiko Sedang "
                sb.append(results)
            }
        }
        else if(smoke_qty.isEmpty())
        {
            val results = "Jawaban anda kurang sesuai "
            sb.append(results)
            println(sb.toString())
        }
        val taskid = ref1.push().key
        if (taskid!=null)
        {
            Log.d("test",taskid)
            val dataBackward = AnswerSheets(taskid.toString(),Smoke,smoke_qty,Ldl,Tensi,bmi,Umur,Gender,diabetes,Sport,stressval,Cf,sb.toString())
            ref1.child(datauser.toString()).child(taskid).setValue(dataBackward)
        }
    }

}