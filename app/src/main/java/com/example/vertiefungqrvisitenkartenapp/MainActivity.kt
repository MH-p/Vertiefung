package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {
    private lateinit var userArrayList: ArrayList<UserData>
    private lateinit var userRecyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        checkIfUserIsAlreadyRegistered()

        userRecyclerview = findViewById(R.id.userProfileList)
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)
        userArrayList = arrayListOf<UserData>()


        configureButtons()

        configureRecyclerView()


    }


    private fun configureRecyclerView() {
        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/Contacts").child("2222")//Todo hier eigene tel

        val intent = Intent(this, UserProfile::class.java)
        database.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserData::class.java)
                        userArrayList.add(user!!)

                    }
                    val adapter = UserRecyclerViewAdapter(userArrayList)
                    userRecyclerview.adapter = adapter


                    adapter.setOnUserClickListener(object :
                        UserRecyclerViewAdapter.OnUserClickListener {
                        override fun onUserClick(position: Int) {
                            intent.putExtra("user", userArrayList[position])
                            startActivity(intent)
                        }

                    })

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })

    }



    private fun configureButtons() {
        configureGoToScanner()
    }

    private fun configureGoToScanner() {
        val gotToScannerButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        gotToScannerButton.setOnClickListener {
            val intent = Intent(this, ManageContacts::class.java)
            startActivity(intent)
        }
    }


    private fun checkIfUserIsAlreadyRegistered() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedFirstName = sharedPreferences.getString("FIRST_NAME", null)
        val savedLastName = sharedPreferences.getString("LAST_NAME", null)
        val savedPhoneNumber = null
        val savedEmail = sharedPreferences.getString("EMAIL", null)
        val savedDescription = sharedPreferences.getString("DESCRIPTION", null)

        if (savedPhoneNumber.isNullOrBlank()) {
            val intent = Intent(this, UserRegister::class.java)
            startActivity(intent)
        } else {
            //todo load all kontatks for phonenumber
        }


    }


    override fun onBackPressed() {
        super.onBackPressed();
        finish()
    }


}





