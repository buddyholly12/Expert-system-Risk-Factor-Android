package com.fileskripsi.skripsi.RegistUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.fileskripsi.skripsi.HomeUI.homeUI
import com.fileskripsi.skripsi.R
import com.fileskripsi.skripsi.User
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home_u_i.*
import kotlinx.android.synthetic.main.activity_regist_u_i.*
import java.util.regex.Pattern

class RegistUI : AppCompatActivity(){

    private lateinit var auth:FirebaseAuth
    private lateinit var displayName: EditText
    private lateinit var status: EditText
    private lateinit var email: EditText
    private lateinit var Password: EditText
    private  lateinit var  Regist :Button
    var dbRef: DatabaseReference? =null
    var database1:FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regist_u_i)

        auth = FirebaseAuth.getInstance();
        database1 = FirebaseDatabase.getInstance()

        dbRef = database1?.reference!!.child("Data User")

        displayName = findViewById(R.id.displayName) as EditText
        status = findViewById(R.id.status) as EditText
        Regist = findViewById(R.id.Register_session) as Button
        email = findViewById(R.id.email) as EditText
        Password = findViewById(R.id.Password) as EditText



        Regist.setOnClickListener {
            if (email.text.toString().trim().isNotEmpty()|| Password.text.toString().trim().isNotEmpty()){
                createUser(email.text.toString().trim(),Password.text.toString().trim())
                val Home = Intent(this@RegistUI,homeUI::class.java)
                startActivity(Home)
            }
            else{
                Toast.makeText(applicationContext, "input data ", Toast.LENGTH_SHORT).show()
            }
            //getdata()
        }


    }


    fun createUser(email:String,Password:String){
        auth.createUserWithEmailAndPassword(email,Password).addOnCompleteListener { task ->
            if (task.isSuccessful){

                val curruser = auth.currentUser
                val curruserdb = dbRef?.child((curruser?.uid!!))
                curruserdb?.child("DisplayName")?.setValue(displayName.text.toString())
                curruserdb?.child("Status")?.setValue(status.text.toString())
                Log.e("Task message ", "Successful Register");
                finish()
            }
            else{
                Log.e("Task message ", "Failed Register"+task.exception);
            }
        }
    }
 override fun onStart(){
     super.onStart()
     val dataUser= auth.currentUser
     if(dataUser!= null){
         val Home = Intent(this@RegistUI,homeUI::class.java)
         startActivity(Home)
     }
 }



}