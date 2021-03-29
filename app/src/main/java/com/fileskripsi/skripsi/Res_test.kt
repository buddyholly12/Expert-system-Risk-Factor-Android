package com.fileskripsi.skripsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_u_i.*
import kotlinx.android.synthetic.main.activity_res_test.*
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
        databaseReference = database?.reference!!.child("Client_Ans")
        view_hasil()
    }
    private fun view_hasil(){
        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)
        val taskid = databaseReference?.push()?.key

        userreference?.child(taskid.toString())?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                Tv_hasil.text = snapshot.child("hasil_backward").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }


}