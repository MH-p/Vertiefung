package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private lateinit var userArrayList: ArrayList<UserData>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkIfUserIsAlreadyRegistered()
        configureButtons()
        userArrayList = arrayListOf<UserData>()




//        getData()
//        configureRecyclerView()


    }


//    private fun getData() {
//        val database =
//            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
//                .getReference("Data/Users")
//
//
//
//
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    for (userSnapshot in snapshot.children) {
//                        val user = userSnapshot.getValue(UserData::class.java)
//                        userArrayList.add(user!!)
//
//
//                    }
//                    val i=userArrayList
//                    recyclerView.adapter = RecyclerViewAdapter(userArrayList)
//
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//
//        })
//
//
//    }


    private fun configureRecyclerView() {
        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/Users")

        recyclerView = findViewById<RecyclerView>(R.id.userProfileList);



//        database.addChildEventListener(object : ChildEventListener {
//            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
//
//                for (ds in dataSnapshot.children) {
//                    val user: UserData? = dataSnapshot.getValue(UserData::class.java)
//                }
//
//            }
//
//            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
//            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
//            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
//            override fun onCancelled(databaseError: DatabaseError) {}
//        })





        recyclerView.layoutManager = LinearLayoutManager(this)


        val options: FirebaseRecyclerOptions<UserData> = FirebaseRecyclerOptions.Builder<UserData>()
            .setQuery(database, UserData::class.java)
            .build()


        // This will pass the ArrayList to our Adapter
        val adapter = RecyclerViewAdapter(options)



        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter


//        val intent = Intent(this, UserProfile::class.java)
//
////        intent.putExtra("userName",)
//        adapter.setOnUserClickListener(object : RecyclerViewAdapter.OnUserClickListener {
//            override fun onUserClick(position: Int) {
//
//
//                intent.putExtra("userImage", R.drawable.images)
//
//                startActivity(intent)
//            }
//
//        })

    }


    private fun configureButtons() {
        configureGoToScanner()
    }

    private fun configureGoToScanner() {
        val gotToScannerButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        gotToScannerButton.setOnClickListener {
            val intent = Intent(this, QrCodeScanner::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed();
        finish()
    }

    private fun checkIfUserIsAlreadyRegistered() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedFirstName = sharedPreferences.getString("FIRST_NAME", null)
        val savedLastName = sharedPreferences.getString("LAST_NAME", null)
        val savedPhoneNumber = sharedPreferences.getString("PHONE_NUMBER", null)
        val savedEmail = sharedPreferences.getString("EMAIL", null)
        val savedDescription = sharedPreferences.getString("DESCRIPTION", null)

//        if (savedPhoneNumber.isNullOrBlank()) {
//            val intent = Intent(this, UserRegister::class.java)
//            startActivity(intent)
//        }
//        else
//        {
//            //todo load all kontatks for phonenumber
//        }


    }
}




