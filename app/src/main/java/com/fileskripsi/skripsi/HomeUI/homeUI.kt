package com.fileskripsi.skripsi.HomeUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fileskripsi.skripsi.History.History_data
import com.fileskripsi.skripsi.LoginRegistUI.loginRegist
import com.fileskripsi.skripsi.R
import com.fileskripsi.skripsi.Backward_Session.Menu_test
import com.fileskripsi.skripsi.Bmi_Calculator.Calculator_bmi
import com.fileskripsi.skripsi.Res_test
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_u_i.*


class homeUI : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_u_i)
        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance()

        databaseReference = database?.reference!!.child("Data User")

        logout_button.setOnClickListener{
            logout()
            val Intent =Intent(this@homeUI,loginRegist::class.java)
            startActivity(Intent)
        }
        getdata()
        Start_test.setOnClickListener{
            val Intent =Intent(this@homeUI, Menu_test::class.java)
            startActivity(Intent)
        }

        button2.setOnClickListener{
            val Intent = Intent(this@homeUI,History_data::class.java)
            startActivity(Intent)
        }
        button3.setOnClickListener {
            val Intent = Intent(this@homeUI, Res_test::class.java)
            startActivity(Intent)
        }
        button4.setOnClickListener{
            val Intent = Intent(this@homeUI, Calculator_bmi::class.java)
            startActivity(Intent)
        }


    }


    private fun logout()
    {
        auth = FirebaseAuth.getInstance();
        auth.signOut()
    }

    private  fun getdata(){
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        email.text = user?.email


        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
              TV_nama.text = snapshot.child("DisplayName").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}