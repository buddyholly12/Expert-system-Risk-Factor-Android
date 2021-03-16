package com.fileskripsi.skripsi.Test_Ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import androidx.core.util.rangeTo
import androidx.core.view.isGone
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.CF_data.Cf_Class
import com.fileskripsi.skripsi.CF_data.Cf_hitung
import com.fileskripsi.skripsi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
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
    private lateinit var CF_list: MutableList<Cf_Class>
    private lateinit var CF_Data: MutableList<Cf_hitung>
    private lateinit var CF_Data1: MutableList<Cf_hitung>
    private lateinit var CF_Data2: MutableList<Cf_hitung>
    var dbRef: DatabaseReference? =null
    var database1:FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_u_i)
        database = FirebaseDatabase.getInstance()
        supportActionBar?.hide()
        question_List()
        //answer()
        // dbRef = database1?.reference!!.child("Data_Jawaban")

        auth = FirebaseAuth.getInstance()
        database1 = FirebaseDatabase.getInstance()
        dbRef = database1?.reference!!.child("Data User")

        answerSheets1 = mutableListOf()
        Submit = findViewById(R.id.Submitbutton)
        jumlahbtg = findViewById(R.id.jumlah)
        LDl = findViewById(R.id.ldl_score)
        Tensi = findViewById(R.id.Tensi)
        chb = findViewById(R.id.checkBox)
        CF_list = mutableListOf()
        CF_Data = mutableListOf()
        CF_Data1 = mutableListOf()
        CF_Data2 = mutableListOf()
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
                    if (data.child("ID_Question").value == "Q1") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q2") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb1.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q3") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb2.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q4") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb3.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q5") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb4.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q6") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb5.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q7") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb6.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q8") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb7.append("$data1\n")
                    }
                    if (data.child("ID_Question").value == "Q9") {
                        var data1 = data.child("nama_Pertanyaan").value
                        sb8.append("$data1\n")
                    }
                }
                qustionno1.text = sb
                qustionno2.text = sb1
                question3.text = sb2
                question4.text = sb3
                question5.text = sb4
                question6.text = sb5
                question7.text = sb6
                question8.text = sb7
                question9.text = sb8
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
        //Gender = findViewById(R.id.Gender)
        var data: Double
        var data1: Double
        var data2: Double
        var dataCombine: Double
        var dataCombine1: Double
        var dataCombine2: Double
        var dataCombine3: Double
        var dataCombine4: Double
        var dataCombine5: Double
        var dataCombine6: Double
        var dataCombine7: Double
        var dataCombine8: Double
        var dataCombine9: Double
        var dataCombine10: Double
        var dataCombine11: Double
        var dataCombine12: Double
        var dataCombine13: Double
        var dataCombine14: Double
        var dataCombine15: Double
        var dataCombine16: Double
        var dataCombine17: Double
        var dataCombine18: Double
        var dataCombine19: Double
        var dataCombine20: Double
        var dataCombine21: Double
        var dataCombine22: Double
        var dataCombine23: Double
        var dataCombine24: Double
        var dataCombine25: Double
        var dataCombine26: Double
        val result = StringBuilder()
        val result1 = StringBuilder()
        val result2 = StringBuilder()
        val result3 = StringBuilder()
        val result4 = StringBuilder()
        val ref = FirebaseDatabase.getInstance().getReference("User_ans")
        val ref1 = FirebaseDatabase.getInstance().getReference("Client_Ans")
        val nlistcfcombinerendah = mutableListOf<Double>()
        val nlistcfcombineSedang = mutableListOf<Double>()
        val nlistcfcombineTinggi = mutableListOf<Double>()
        val Nlist_combine = mutableListOf<Double>()
        var nlist = mutableListOf<Double>()
        val listCF_Rendah = listOf<Double>(0.8, 0.8, 0.8, 0.8, 0.8, 0.8, 0.6, 0.8, 0.8)
        val listCF_Sedang = listOf<Double>(0.4, 0.6, 0.6, 0.6, 1.0, -1.0, 1.0, 0.6, 0.6)
        val listCF_Tinggi = listOf<Double>(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0)


        if (chb.isChecked) {
            result.append("\nTidak")
            var A1 = 0.8

            nlist.add(A1)

            jumlahbtg.isGone = true
            //  nlistcfcombine.add()
        }

        //jumlah batang + cf
        val jumlah_batang = jumlahbtg.text.toString().trim()
        if (jumlah_batang.isEmpty()) {
            jumlahbtg.isGone = true
        } else {
            if (jumlah_batang.toInt() <= 10) {
                var A2 = 0.4
                nlist.add(A2)
            }
            if (jumlah_batang.toInt() >= 10) {
                var A3 = 1.0
                nlist.add(A3)
            }
        }


        // ldl + CF
        val LDL = LDl.text.toString().toInt()
        if (LDL <= 75) {
            var A4 = 0.8
            nlist.add(A4)
        } else if (LDL in 75 rangeTo (150)) {
            val A5 = 0.6
            nlist.add(A5)
        } else if (LDL > 150) {
            val A6 = 1.0
            nlist.add(A6)
        }

        /// Tensi + CF
        val Data_Tensi = Tensi.text.toString().toInt()
        if (Data_Tensi <= 120) {
            val A7 = 0.8
            nlist.add(A7)
        } else if (Data_Tensi in 120 rangeTo (140)) {
            val A8 = 0.6
            nlist.add(A8)
        } else if (Data_Tensi >= 140) {
            val A9 = 1.0
            nlist.add(A9)
        }

        // BErat Badan + cf
        val bb = berat_badan.text.toString().toDouble()
        val tinggi = tinggi_badan.text.toString().toDouble()
        val bmi = bb / tinggi / tinggi * 10000
        val bmi1 = bmi.roundToInt()
        if (bmi1.toDouble() in 18.5 rangeTo (24.9)) {
            val A10 = 0.8
            nlist.add(A10)

        } else if (bmi1.toDouble() in 25.0 rangeTo (29.9)) {
            val A11 = 0.6
            nlist.add(A11)
        } else if (bmi1.toDouble() in 30.00 rangeTo (34.9)) {
            val A12 = 1.0
            nlist.add(A12)
        }

        val dataumur = Data_umur.text.toString().toInt()
        if (dataumur <= 40) {
            val A13 = 0.8
            nlist.add(A13)
        } else if (dataumur in 40 rangeTo (50)) {
            val A14 = 0.6
            nlist.add(A14)
        } else if (dataumur >= 50) {
            val A15 = 1.0
            nlist.add(A15)
        }
        // gender
        if (radioButton4.isChecked) {
            result1.append("Laki-laki")
            val A16 = 0.8
            nlist.add(A16)

        }
        if (radioButton5.isChecked) {
            result1.append("Perempuan")
            val A17 = 0.6
            nlist.add(A17)
        }
        //riwayat diabetes
        if (radioButton6.isChecked) {
            result2.append("Tidak")
            val A18 = 0.6
            nlist.add(A18)
        }
        if (radioButton7.isChecked) {
            result2.append("Ya")
            val A19 = 0.4
            nlist.add(A19)
        }

        // olahraga
        if (radioButton8.isChecked) {
            result3.append("Rutin")
            val A20 = 0.8
            nlist.add(A20)
        }
        if (radioButton9.isChecked) {
            result3.append("Jarang")
            val A21 = 0.6
            nlist.add(A21)
        }
        if (radioButton10.isChecked) {
            result3.append("Tidak")
            val A22 = 1.0
            nlist.add(A22)
        }

        // tingkat stress
        if (radioButton11.isChecked) {
            result4.append("Tidak")
            val A24 = 0.6
            nlist.add(A24)
        }
        if (radioButton12.isChecked) {
            result4.append("ya")
            val A23 = 0.4
            nlist.add(A23)
        }



        val taskid = ref1.push().key
        val jawabanUser = AnswerSheets(result.toString(), jumlah_batang, LDL, Data_Tensi, bmi1.toDouble(),dataumur, result1.toString(),result2.toString(), result3.toString(), result4.toString(),0.0)
        if (jawabanUser.Smoke == null) {
            AnswerSheets("", jumlah_batang, LDL, Data_Tensi,bmi1.toDouble(),dataumur,result1.toString(),  result2.toString(), result3.toString(),result4.toString(), 0.0)
        }

        //answerSheets1.add(jawabanUser)

       // Log.d("Test", answerSheets1.toString())
        Log.d("Test", jawabanUser.smoke_qty)
        Log.d("Test", jawabanUser.Smoke.toString())
        Log.d("Test", jawabanUser.Ldl.toString())
        Log.d("Test", jawabanUser.Tensi.toString())


        val X = Cf_hitung(nlist[0],nlist[1],nlist[2],nlist[3],nlist[4],nlist[5],nlist[6],nlist[7],nlist[8])
        Log.d("Test", X.toString())
        Log.d("Test", nlist.toString())


        for (i in nlist.indices) {
            if (jawabanUser.Umur <= 40) {
                for (j in listCF_Rendah.indices) {
                    data = nlist[i] * listCF_Rendah[j]
                    nlistcfcombinerendah.add(data)
                }
            }
            if (jawabanUser.Umur in 40.rangeTo(50)) {
                for (k in listCF_Sedang.indices) {
                    data1 = nlist[i] * listCF_Sedang[k]
                    nlistcfcombineSedang.add(data1)
                }
            }
            if (jawabanUser.Umur in 51.rangeTo(90) ){
                for (l in listCF_Tinggi.indices){
                    data2 = nlist[i]*listCF_Tinggi[l]
                    nlistcfcombineTinggi.add(data2)
                }

            }

        }
        if (nlistcfcombinerendah != null) {
            for (i in nlistcfcombinerendah.indices) {
                if (i == 0) {
                    dataCombine = nlistcfcombinerendah[0] + nlistcfcombinerendah[1] * (1 - nlistcfcombinerendah[0])
                    dataCombine1 = nlistcfcombinerendah[1] + dataCombine * (1 - nlistcfcombinerendah[1])
                    dataCombine2 = nlistcfcombinerendah[2] + dataCombine1 * (1 - nlistcfcombinerendah[2])
                    dataCombine3 = nlistcfcombinerendah[3] + dataCombine2 * (1 - nlistcfcombinerendah[3])
                    dataCombine4 = nlistcfcombinerendah[4] + dataCombine3 * (1 - nlistcfcombinerendah[4])
                    dataCombine5 = nlistcfcombinerendah[5] + dataCombine4 * (1 - nlistcfcombinerendah[5])
                    dataCombine6 = nlistcfcombinerendah[6] + dataCombine5 * (1 - nlistcfcombinerendah[6])
                    dataCombine7 = nlistcfcombinerendah[7] + dataCombine6 * (1 - nlistcfcombinerendah[7])
                    dataCombine8 = nlistcfcombinerendah[8] + dataCombine7 * (1 - nlistcfcombinerendah[8])
                    val x = Cf_Class(dataCombine, dataCombine1, dataCombine2, dataCombine3, dataCombine4, dataCombine5, dataCombine6, dataCombine7, dataCombine8)
                    val datalist = (x.cf1 + x.cf2 + x.cf3 + x.cf4 + x.cf5 + x.cf6 + x.cf7 + x.cf8 + x.cf9) / 9 * 100
                    val data = datalist.roundToInt()
                    CF_list.add(x)
                    val jawabanUser = AnswerSheets(result.toString(), jumlah_batang, LDL, Data_Tensi, bmi1.toDouble(),dataumur, result1.toString(),result2.toString(),
                        result3.toString(), result4.toString(),data.toDouble())
                    answerSheets1.add(jawabanUser)
                    Log.d("CF_list", CF_list.toString())
                    Log.d("datalist", data.toDouble().toString())
                    Log.d("datalist", answerSheets1.toString())




//                    if (taskid != null) {
//                        ref1.child(datauser.toString()).child(taskid).setValue(jawabanUser)
//                    }

                }

            }

        }
        if (nlistcfcombineSedang.indices != null) {
            for (i in nlistcfcombineSedang.indices) {
                if (i == 0) {
                    dataCombine9 = nlistcfcombineSedang[0] + nlistcfcombineSedang[1] * (1 - nlistcfcombineSedang[0])
                    dataCombine10 = nlistcfcombineSedang[1] + dataCombine9 * (1 - nlistcfcombineSedang[1])
                    dataCombine11 = nlistcfcombineSedang[2] + dataCombine10 * (1 - nlistcfcombineSedang[2])
                    dataCombine12 = nlistcfcombineSedang[3] + dataCombine11 * (1 - nlistcfcombineSedang[3])
                    dataCombine13 = nlistcfcombineSedang[4] + dataCombine12 * (1 - nlistcfcombineSedang[4])
                    dataCombine14 = nlistcfcombineSedang[5] + dataCombine13 * (1 - nlistcfcombineSedang[5])
                    dataCombine15 = nlistcfcombineSedang[6] + dataCombine14 * (1 - nlistcfcombineSedang[6])
                    dataCombine16 = nlistcfcombineSedang[7] + dataCombine15 * (1 - nlistcfcombineSedang[7])
                    dataCombine17 = nlistcfcombineSedang[8] + dataCombine16 * (1 - nlistcfcombineSedang[8])
                    val x = Cf_Class(dataCombine9, dataCombine10, dataCombine11, dataCombine12, dataCombine13, dataCombine14, dataCombine15, dataCombine16, dataCombine17)
                    val datalist = (x.cf1 + x.cf2 + x.cf3 + x.cf4 + x.cf5 + x.cf6 + x.cf7 + x.cf8 + x.cf9) / 9 * 100
                    val data = datalist.roundToInt()
                    CF_list.add(x)
                    Log.d("CF_list", CF_list.toString())
                    Log.d("datalist", datalist.toString())
                    if (taskid != null) {
                        ref.child(taskid).child("Hasil Cf :\n" + data.toString()).setValue(jawabanUser)
                    }
                }
            }
        }
        if (nlistcfcombineTinggi.indices != null) {
            for (i in nlistcfcombineTinggi.indices) {
                if (i == 0) {
                    dataCombine18 = nlistcfcombineTinggi[0] + nlistcfcombineTinggi[1] * (1 - nlistcfcombineTinggi[0])
                    dataCombine19 = nlistcfcombineTinggi[1] + dataCombine18 * (1 - nlistcfcombineTinggi[1])
                    dataCombine20 = nlistcfcombineTinggi[2] + dataCombine19 * (1 - nlistcfcombineTinggi[2])
                    dataCombine21 = nlistcfcombineTinggi[3] + dataCombine20 * (1 - nlistcfcombineTinggi[3])
                    dataCombine22 = nlistcfcombineTinggi[4] + dataCombine21 * (1 - nlistcfcombineTinggi[4])
                    dataCombine23 = nlistcfcombineTinggi[5] + dataCombine22 * (1 - nlistcfcombineTinggi[5])
                    dataCombine24 = nlistcfcombineTinggi[6] + dataCombine23 * (1 - nlistcfcombineTinggi[6])
                    dataCombine25 = nlistcfcombineTinggi[7] + dataCombine24 * (1 - nlistcfcombineTinggi[7])
                    dataCombine26 = nlistcfcombineTinggi[8] + dataCombine25 * (1 - nlistcfcombineTinggi[8])
                    val x = Cf_Class(dataCombine18, dataCombine19, dataCombine20, dataCombine21, dataCombine22, dataCombine23, dataCombine24, dataCombine25, dataCombine26)
                    val datalist = (x.cf1 + x.cf2 + x.cf3 + x.cf4 + x.cf5 + x.cf6 + x.cf7 + x.cf8 + x.cf9) / 9 * 100
                    val data = datalist.roundToInt()
                    CF_list.add(x)
                    Log.d("CF_list", CF_list.toString())
                    Log.d("datalist", datalist.toString())
//                    if (taskid != null) {
//                        ref.child(taskid).child(data.toString()).setValue(jawabanUser)
//                    }
                }
            }
        }



    }


    override fun onClick(v: View?) {
        answerSheets_data()

    }

}
