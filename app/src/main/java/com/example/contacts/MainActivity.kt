package com.example.contacts

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
   lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val etname = findViewById<EditText>(R.id.etName)
        val etnum = findViewById<EditText>(R.id.etNum)
        val addContact = findViewById<Button>(R.id.addContact)
        addContact.setOnClickListener {
            val name = etname.text.toString()
            val num = etnum.text.toString()

            val user = User(name,num)
            database = FirebaseDatabase.getInstance().getReference("User")
            database.child(name).setValue(user).addOnSuccessListener {
                Toast.makeText(this,"Contact added in Firebase!",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

    }
}