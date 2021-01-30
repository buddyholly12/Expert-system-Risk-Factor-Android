package com.fileskripsi.skripsi.Test_Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import com.fileskripsi.skripsi.AnswerSheets
import com.fileskripsi.skripsi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_u_i.*
import kotlinx.android.synthetic.main.activity_test_u_i.*
import kotlin.math.roundToInt

class TestUI : AppCompatActivity(), View.OnClickListener {
    private lateinit var database: FirebaseDatabase
    private lateinit var jumlahbtg: EditText
    private lateinit var LDl: EditText
    private lateinit var Tensi: EditText
    private lateinit var radioButtondata: RadioButton
    private lateinit var Submit: Button
    lateinit var chb: CheckBox
    lateinit var genderradio: RadioButton
    lateinit var genderradio1: RadioButton
    private lateinit var answerSheets1: MutableList<AnswerSheets>
    private lateinit var berat_badan: EditText
    private lateinit var tinggi_badan: EditText
    private lateinit var Data_umur: EditText
    private lateinit var Gender: EditText
    var dbRef: DatabaseReference? =null
    var database1:FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_u_i)
        database = FirebaseDatabase.getInstance()
        question_List()
        //answer()
        // dbRef = database1?.reference!!.child("Data_Jawaban")

        auth = FirebaseAuth.getInstance();
        database1 = FirebaseDatabase.getInstance()

        dbRef = database1?.reference!!.child("Data User")

        answerSheets1 = mutableListOf()
        Submit = findViewById(R.id.Submitbutton)
        jumlahbtg = findViewById(R.id.jumlah)
        LDl = findViewById(R.id.ldl_score)
        Tensi = findViewById(R.id.Tensi)
        chb = findViewById(R.id.checkBox)
        Submit.setOnClickListener(this)

    }

    private fun question_List() {
        val ref = FirebaseDatabase.getInstance().getReference("/Database_Risk_Factor/Question")
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb = StringBuilder()
                var sb1 = StringBuilder()
                var sb2 = StringBuilder()
                var sb3 = StringBuilder()
                var sb4 = StringBuilder()
                var sb5 = StringBuilder()
                var sb6 = StringBuilder()
                var sb7 = StringBuilder()
                var sb8 = StringBuilder()
                for (data in snapshot.children) {
                    if (data.child("ID_Question").getValue() == "Q1") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q2") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb1.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q3") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb2.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q4") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb3.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q5") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb4.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q6") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb5.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q7") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb6.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q8") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb7.append("$data1\n")
                    }
                    if (data.child("ID_Question").getValue() == "Q9") {
                        var data1 = data.child("nama_Pertanyaan").getValue()
                        sb8.append("$data1\n")
                    }
                }
                qustionno1.setText(sb)
                qustionno2.setText(sb1)
                question3.setText(sb2)
                question4.setText(sb3)
                question5.setText(sb4)
                question6.setText(sb5)
                question7.setText(sb6)
                question8.setText(sb7)
                question9.setText(sb8)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "Data error ")
            }

        })

    }
    // answer_Buffer
    private fun answerSheets_data() {
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val datauser = user?.uid
        jumlahbtg = findViewById(R.id.jumlah)
        LDl = findViewById(R.id.ldl_score)
        Tensi = findViewById(R.id.Tensi)
        chb = findViewById(R.id.checkBox)
        berat_badan = findViewById(R.id.beratbadan)
        tinggi_badan = findViewById(R.id.tinggibadan)
        Data_umur = findViewById(R.id.Umur)
        // Gender = findViewById(R.id.Gender)
        var Result = ""
        var dbansw = ""
        val result = StringBuilder()
        val result1 = StringBuilder()
        val result2 = StringBuilder()
        val result3 = StringBuilder()
        val cf1 = 1
        val cf2 = 0.8
        val cf3 = 0.4
        val ref = FirebaseDatabase.getInstance().getReference("User_ans")
        if (chb.isChecked) {
            result.append("\nTidak")
            jumlahbtg.isEnabled = false
        }
        val jumlah_batang = jumlahbtg.text.toString().trim()
        val LDL = LDl.text.toString().trim()
        val Data_Tensi = Tensi.text.toString().trim()
        val bb = berat_badan.text.toString().toDouble()
        val tinggi = tinggi_badan.text.toString().toDouble()
        val bmi = bb / tinggi / tinggi * 10000
        val bmi1 = bmi.roundToInt()
        val dataumur = Data_umur.text.toString().trim()
        if (radioButton4.isChecked) {
            result1.append("Laki-laki")
        }
        if (radioButton5.isChecked) {
            result1.append("Perempuan")
        }

        if (radioButton6.isChecked) {
            result2.append("Tidak")
        }
        if (radioButton7.isChecked) {
            result2.append("Ya")
        }
        if (radioButton8.isChecked) {
            result3.append("Rutin")
        }
        if (radioButton9.isChecked) {
            result3.append("Jarang")
        }
        if (radioButton10.isChecked) {
            result3.append("Tidak")
        }
        val taskid = ref.push().key
        val jawabanUser = AnswerSheets(result.toString(), jumlah_batang, LDL, Data_Tensi, result1.toString(), bmi1.toDouble(), result2.toString(), result3.toString(), dataumur)
        if (jawabanUser.Smoke == null) {
            AnswerSheets("", jumlah_batang, LDL, Data_Tensi, result1.toString(), bmi1.toDouble(), result2.toString(),
                    result3.toString(), dataumur)
        }

        answerSheets1.add(jawabanUser)

        Log.d("Test", answerSheets1.toString())
        Log.d("Test", jawabanUser.smoke_qty)
        Log.d("Test", jawabanUser.Ldl)
        Log.d("Test", jawabanUser.Tensi)

        val result4 = StringBuilder()

    }


    override fun onClick(v: View?) {
        answerSheets_data()

    }

}
