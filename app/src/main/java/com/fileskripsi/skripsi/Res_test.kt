package com.fileskripsi.skripsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_u_i.*
import kotlinx.android.synthetic.main.activity_res_test.*
import kotlinx.android.synthetic.main.activity_test_u_i.*
import java.util.*

class Res_test : AppCompatActivity() {
    var dbRef: DatabaseReference? =null
    var database1:FirebaseDatabase? = null
    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_res_test)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance()
        val taskid = databaseReference?.push()?.key
        databaseReference = database?.reference!!.child("Client_Ans/")
        view_hasil()
        backtombol.setOnClickListener({
            val Intent = Intent(this@Res_test,homeUI::class.java)
            startActivity(Intent)
            finish()
        })
    }

    private fun view_hasil(){
        val taskid = databaseReference?.push()!!.key
        val taskid2 = databaseReference
        val user = auth.currentUser
        var userreference = databaseReference?.child(user?.uid.toString())
        //var dataref = databaseReference?.child(taskid2.toString())

        Log.d("dbref",databaseReference.toString())

            userreference?.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("test1", snapshot.toString())
                var sb = StringBuilder()
                var sb1 = StringBuilder()
                //Log.d("test",Tv_hasil.toString())

                for (data in snapshot.children)
                {
                    Tv_hasil.text=data.child("hasil_backward").value.toString()
                    Tv_hasil1.text=data.child("cf").value.toString()
                    Tv_hasil2.text=data.child("deskripsi").value.toString()
                    Tv_hasil3.text = data.child("saran").value.toString()
                }

                Log.d("test data",snapshot.value.toString())

            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        }



}