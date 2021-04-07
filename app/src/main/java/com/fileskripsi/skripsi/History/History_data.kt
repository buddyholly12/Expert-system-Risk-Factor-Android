package com.fileskripsi.skripsi.History

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.fileskripsi.skripsi.Data_class_Value.AnswerSheets
import com.fileskripsi.skripsi.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class History_data : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null
    private lateinit var historyList : MutableList<AnswerSheets>
    private lateinit var lv_history :ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_data)
        supportActionBar?.hide()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Client_Ans/")
        val taskid = databaseReference?.push()!!.key
        val taskid2 = databaseReference
        auth = FirebaseAuth.getInstance();
        val user = auth.currentUser
        var userreference = databaseReference?.child(user?.uid.toString())
        historyList = mutableListOf()
        lv_history = findViewById(R.id.Lv_history)
        userreference?.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists())
                {
                    for (data in snapshot.children)
                    {
                        val dataHistory = data.getValue(AnswerSheets::class.java)
                        println(dataHistory)
                        if (dataHistory!= null)
                        {
                            historyList.add(dataHistory)
                            println("\n $historyList \n")
                        }
                    }
                    val adapter = HistoryAdapter(applicationContext,R.layout.listview,historyList)
                    lv_history.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}