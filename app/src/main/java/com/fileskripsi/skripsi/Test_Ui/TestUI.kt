package com.fileskripsi.skripsi.Test_Ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.util.rangeTo
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import com.fileskripsi.skripsi.Backward_Session.Backward
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.CF_data.Cf_Class
import com.fileskripsi.skripsi.CF_data.Cf_hitung
import com.fileskripsi.skripsi.Data_class_Value.AnswerBackward
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.fileskripsi.skripsi.R
import com.fileskripsi.skripsi.Res_test
import com.fileskripsi.skripsi.databinding.ActivityTestUIBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_test_u_i.*
import kotlin.math.roundToInt

class TestUI : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {
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
   // private lateinit var CF_Data2: MutableList<MedAns>
    var dbRef: DatabaseReference? =null
    var database1:FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    private lateinit var  binding:ActivityTestUIBinding
    lateinit var hasil : List<Double>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_test_u_i)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test_u_i)
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
        //CF_Data2 = mutableListOf()
        Submit.setOnClickListener(this)
        Cf_data()


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
        auth = FirebaseAuth.getInstance()
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
        var Result = ""
        var dbansw = ""
        val result = StringBuilder()
        val result1 = StringBuilder()
        val result2 = StringBuilder()
        val result3 = StringBuilder()
        val result4 = StringBuilder()
        val ref = FirebaseDatabase.getInstance().getReference("User_ans")
        val nlistcfcombinerendah = mutableListOf<Double>()
        val nlistcfcombineSedang = mutableListOf<Double>()
        val nlistcfcombineTinggi = mutableListOf<Double>()
        val Nlist_combine = mutableListOf<Double>()
        var nlist = mutableListOf<Double>()
        val listCF_Rendah = listOf<Double>(0.8, 0.8, 0.8, 0.8, 0.8, -1.0, 1.0, 0.8, 1.0)
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
        Log.d("bmi", bmi.toString())
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
            val A16 = 1.0
            nlist.add(A16)

        }
        if (radioButton5.isChecked) {
            result1.append("Perempuan")
            val A17 = -1.0
            nlist.add(A17)
        }
        //riwayat diabetes
        if (radioButton6.isChecked) {
            result2.append("Tidak")
            val A18 = 1.0
            nlist.add(A18)
        }
        if (radioButton7.isChecked) {
            result2.append("Ya")
            val A19 = -1.0
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
            val A24 = 1.0
            nlist.add(A24)
        }
        if (radioButton12.isChecked) {
            result4.append("Ya")
            val A23 = -1.0
            nlist.add(A23)
        }

        val x = AnswerBackward(result.toString(),jumlah_batang, LDL, Data_Tensi, bmi1.toDouble(), dataumur, result1.toString(), result2.toString(), result3.toString(), result4.toString(), hasil[0])

        if (x.Umur <= 40) {
            Backward().backward(x.Smoke, SmokeqtyBackwardlow(jumlah_batang), x.Ldl, x.Tensi, x.bmi, x.Umur, x.Gender, x.diabetes, x.Sport, x.stressval, x.Cf)
        }
        if (x.Umur in 40 rangeTo (50))
        {
            Backward().Medium_Backward(SmokeBackwardMed(x.Smoke),jumlah_batang, x.Ldl, x.Tensi, x.bmi, x.Umur, x.Gender, x.diabetes, x.Sport, x.stressval, x.Cf)
            println(x)
        }
    }

    override fun onClick(v: View?) {
        answerSheets_data()
    }
    private fun Cf_data(){
        val flag = true
        val cfUser = listOf<Double>(0.0,0.4,0.6,0.8)
        var nlist = mutableListOf<Double>()
         val Cf_datauser = resources.getStringArray(R.array.CF_list)
        val arrayAdapter = ArrayAdapter(this@TestUI,android.R.layout.simple_spinner_dropdown_item,Cf_datauser)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = this
        spinner2.adapter = arrayAdapter
        spinner2.onItemSelectedListener = this
        spinner3.adapter =arrayAdapter
        spinner3.onItemSelectedListener = this
        spinner4.adapter =arrayAdapter
        spinner4.onItemSelectedListener = this
        spinner5.adapter =arrayAdapter
        spinner5.onItemSelectedListener = this
        spinner6.adapter =arrayAdapter
        spinner6.onItemSelectedListener = this
        spinner7.adapter =arrayAdapter
        spinner7.onItemSelectedListener = this
        spinner8.adapter =arrayAdapter
        spinner8.onItemSelectedListener = this
        spinner9.adapter =arrayAdapter
        spinner9.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        var nlistSpinner = mutableListOf<Double>()
        var cf1 = binding?.spinner?.selectedItem.toString()
        var cf2 = binding?.spinner2?.selectedItem.toString()
        var cf3 = binding?.spinner3?.selectedItem.toString()
        var cf4 = binding?.spinner4?.selectedItem.toString()
        var cf5 = binding?.spinner5?.selectedItem.toString()
        var cf6 = binding?.spinner6?.selectedItem.toString()
        var cf7 = binding?.spinner7?.selectedItem.toString()
        var cf8 = binding?.spinner8?.selectedItem.toString()
        var cf9 = binding?.spinner9?.selectedItem.toString()

        nlistSpinner.add(cf1.toDouble())
        nlistSpinner.add(cf2.toDouble())
        nlistSpinner.add(cf3.toDouble())
        nlistSpinner.add(cf4.toDouble())
        nlistSpinner.add(cf5.toDouble())
        nlistSpinner.add(cf6.toDouble())
        nlistSpinner.add(cf7.toDouble())
        nlistSpinner.add(cf8.toDouble())
        nlistSpinner.add(cf9.toDouble())
        CertainFactor(
                nlistSpinner[0],
                nlistSpinner[1],
                nlistSpinner[2],
                nlistSpinner[3],
                nlistSpinner[4],
                nlistSpinner[5],
                nlistSpinner[6],
                nlistSpinner[7],
                nlistSpinner[8])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun CertainFactor(cf1:Double,cf2: Double,cf3:Double,cf4: Double,cf5:Double,cf6: Double,cf7:Double,cf8: Double,cf9: Double){
        var CfTampil: Double
        var CfTampil1: Double
        var CF_rule :Double
        var CF_pakar :Double
        var CF_pakar1 :Double
        var CF_New :Double
        var CF_Pakar_data = mutableListOf<Double>()
        var Cf_New_data = mutableListOf<Double>()
        var hasil_hitung = mutableListOf<Double>()
        var nlistcfcombine = ArrayList<Double>()
        var dataCombine: Double
        val listMB = listOf<Double>(1.0, 0.8, 0.5, 0.4, 0.8,0.5 , 0.6, 0.7, 0.8)
        val listMD = listOf<Double>(0.4, 0.4, 0.6, 0.5, 0.6,0.6 , 0.6, 0.4, 0.6)
        var x = listOf(cf1, cf2, cf3, cf4, cf5, cf6, cf7, cf8, cf9)
        for (i in listMB.indices)
        {
            for (j in listMD.indices)
            {
                if (i == 0) {
                    CF_pakar = listMB[i] - listMD[j]
                    CF_Pakar_data.add(CF_pakar)
                }
            }
        }
        for (i in x.indices) {
            if (i==0) {
                for (j in CF_Pakar_data.indices) {
                    CF_rule = x[j] * CF_Pakar_data[j]
                    nlistcfcombine.add(CF_rule)
                }
            }
        }
        for (i in x.indices) {
            if (i==0) {
                for (j in CF_Pakar_data.indices) {
                    CF_New = x[j] * (listMB[i] - listMD[j])
                    Cf_New_data.add(CF_New)
                }
            }
        }

        for (i in nlistcfcombine.indices)
        {
            if (i==0){
                for (j in Cf_New_data.indices){
                    dataCombine = nlistcfcombine[j]  +Cf_New_data[j]*(1-nlistcfcombine[j])
                    hasil_hitung.add(dataCombine)

                }

            }
        }
        CfTampil1 = (hasil_hitung[0]+ hasil_hitung[1]+ hasil_hitung[2]+ hasil_hitung[3]+ hasil_hitung[4]+ hasil_hitung[5]+hasil_hitung[6]+hasil_hitung[7]+ hasil_hitung[8])*100 / 9
        CfTampil = CfTampil1.roundToInt().toDouble()
        println(" hasil Hitung :" + CfTampil)
        println("CF_Combine hitung  = $hasil_hitung")
        hasil= listOf(CfTampil)
        println("hasil CF : " +hasil)
    }

    private fun SmokeqtyBackwardlow(qty:String):String{
        if (qty.isEmpty())
        {
            val Intent = Intent(this@TestUI, Res_test::class.java)
            startActivity(Intent)
        }
        else if (qty.isNotEmpty()) {
            val Intent = Intent(this@TestUI, homeUI::class.java)
            startActivity(Intent)
            Toast.makeText(this, "Jawaban Anda Kurang Sesuai ", Toast.LENGTH_SHORT).show()
        }
        return  qty
    }
    private fun SmokeBackwardMed(qty:String):String {
        if (qty.isEmpty())
        {
            val Intent = Intent(this@TestUI, Res_test::class.java)
            startActivity(Intent)
        }
        else if (qty.isNotEmpty()) {
            val Intent = Intent(this@TestUI, homeUI::class.java)
            startActivity(Intent)
        }
        return qty
    }
}
