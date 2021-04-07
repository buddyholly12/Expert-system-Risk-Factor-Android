package com.fileskripsi.skripsi.Backward_Session

import android.util.Log
import androidx.core.util.rangeTo
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Backward {

   var auth: FirebaseAuth? = null
    var databaseReference :  DatabaseReference? = null

    fun backward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi: Double, Umur:Int
                 , Gender:String, diabetes:String, Sport: String, stressval:String, Cf:Double) {

        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        var sb = StringBuilder()
        var sb1 = StringBuilder()
        var sb2 = StringBuilder()
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
            if (stressval == "Tidak" && Cf in 41.0 rangeTo(69.0)) {
                flag
                val results = " Resiko rendah"
                sb1.append("berdasarkan data yang anda inputkan anda memiliki resiko rendah")
                sb2.append("jika anda ingin memeriksakan lebih lanjut harap hubungi Dokter spesialis  Jantung terdekat")
                sb.append(results)

            }
            println(sb.toString())
        }
        else if (smoke_qty.isNotEmpty())
        {
            val results = "Anda Bukan termasuk Resiko Rendah "
            sb.append(results)
            println(sb.toString())

        }
        val taskid = ref1.push().key
        if (taskid!=null)
        {
            Log.d("test",taskid)
            val dataBackward = AnswerSheets(taskid.toString(),Smoke,smoke_qty,Ldl,Tensi,bmi,Umur,Gender,diabetes,Sport,stressval,Cf,sb.toString(),sb1.toString(),sb2.toString())
            ref1.child(datauser.toString()).child(taskid).setValue(dataBackward)
        }

    }
    fun Medium_Backward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi : Double, Umur : Int
                        ,Gender:String, diabetes :String,Sport : String, stressval :String,Cf:Double){
        val ref = FirebaseDatabase.getInstance().getReference("Processing_Data/BackwardRes")
        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        var sb = StringBuilder()
        var sb1 = StringBuilder()
        var sb2 = StringBuilder()
        var flag = true

        auth = FirebaseAuth.getInstance()
        val user = auth!!.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val datauser = user?.uid

        if(Smoke.isEmpty())
        {
            if ( smoke_qty.toInt()<=10&& Gender == "Laki-laki") {
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
            if (stressval == "Ya" && Cf in 41.0 rangeTo(69.0)) {
                flag
                val results = "Resiko Sedang "
                sb1.append("berdasarkan data yang anda inputkan anda memiliki resiko Sedang $Cf %")
                sb2.append("jika anda ingin memeriksakan lebih lanjut harap hubungi Dokter spesialis  Jantung terdekat")
                sb.append(results)
            }
        }
        else if(Smoke.isNotEmpty())
        {
            val results = "Anda Bukan termasuk Resiko Sedang "
            sb.append(results)
            println(sb.toString())
        }
        val taskid = ref1.push().key
        if (taskid!=null)
        {
            Log.d("test",taskid)
            val dataBackward = AnswerSheets(taskid.toString(),Smoke,smoke_qty,Ldl,Tensi,bmi,Umur,Gender,diabetes,Sport,stressval,Cf,sb.toString(),sb1.toString(),sb2.toString())
            ref1.child(datauser.toString()).child(taskid).setValue(dataBackward)
        }
    }



    fun HighBackward(Smoke:String, smoke_qty:String, Ldl:Int, Tensi:Int, bmi : Double, Umur : Int
                     ,Gender:String, diabetes :String,Sport : String, stressval :String,Cf:Double)
    {
        val ref = FirebaseDatabase.getInstance().getReference("Processing_Data/BackwardRes")
        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        var sb = StringBuilder()
        var sb1 = StringBuilder()
        var sb2 = StringBuilder()
        var flag = true
        auth = FirebaseAuth.getInstance()
        val user = auth!!.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val datauser = user?.uid

        if( Smoke.isEmpty())
        {
            if (smoke_qty.toInt()>=10 && Gender == "Laki-laki") {
                flag
            }
            if(Ldl >= 150 && Tensi >140)
            {
                flag
            }
            if (bmi in 30.00 rangeTo (34.9) && Umur >50) {
                flag
            }
            if (Sport == "Tidak" && diabetes == "Ya") {
                flag
            }
            if (stressval == "Ya" && Cf in 41.0 rangeTo(69.0)) {
                flag
                val results = "Resiko Tinggi "
                sb1.append("berdasarkan data yang anda inputkan anda memiliki resiko Tinggi $Cf %")
                sb2.append("jika anda ingin memeriksakan lebih lanjut harap hubungi Dokter spesialis  Jantung terdekat ")
                sb.append(results)
            }
        }
        else if(Smoke.isNotEmpty())
        {
            val results = "Anda Bukan termasuk Resiko Sedang"
            sb.append(results)
            println(sb.toString())
        }
        val taskid = ref1.push().key
        if (taskid!=null)
        {
            Log.d("test",taskid)
            val dataBackward = AnswerSheets(taskid.toString(),Smoke,smoke_qty,Ldl,Tensi,bmi,Umur,Gender,diabetes,Sport,stressval,Cf,sb.toString(),sb1.toString(),sb2.toString())
            ref1.child(datauser.toString()).child(taskid).setValue(dataBackward)
        }
    }

}