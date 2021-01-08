package com.fileskripsi.skripsi.HomeUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fileskripsi.skripsi.LoginRegistUI.loginRegist
import com.fileskripsi.skripsi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
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

               displayname.text = snapshot.child("DisplayName").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}