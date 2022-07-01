package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class UserRegister : AppCompatActivity() {

//    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)


        val userRegisterButton = findViewById<Button>(R.id.registerButtonUser)

        userRegisterButton.setOnClickListener {
            val firstName = findViewById<TextView>(R.id.editTextPersonFirstName).text.toString()
            val lastName = findViewById<TextView>(R.id.editTextPersonLastName).text.toString()
            val phoneNumber = findViewById<TextView>(R.id.editTextPhone).text.toString()
            val email = findViewById<TextView>(R.id.editTextEmailAddress).text.toString()
            val description = findViewById<TextView>(R.id.editTextDescription).text.toString()

            val database =
                FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                    .getReference("Data/Users")

//            val users: MutableMap<String, UserData> = HashMap()
//            users["23"] = UserData("yto", "Alan Turing","1232312","wdsadas","3231231231")
//val user1=UserData("sdasdsa","dsdadsa","21313123","3213231","231231231")
//            users["21312321"] = user1
//
//            database.updateChildren(users as Map<String, Any>)


            val user = UserData(firstName, lastName, phoneNumber, email, description)
            database.child(phoneNumber).setValue(user).addOnSuccessListener {
                val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.apply {
                    putString("FIRST_NAME", firstName)
                    putString("LAST_NAME", lastName)
                    putString("PHONE_NUMBER", phoneNumber)
                    putString("EMAIL", email)
                    putString("DESCRIPTION", description)


                }.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }.addOnFailureListener {
                Toast.makeText(this, "Not ok", Toast.LENGTH_LONG).show()
            }


        }


    }
}