package com.example.vertiefungqrvisitenkartenapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
//    private lateinit var recyclerView: RecyclerView;
    val text :String="Random";
    val userPic: Int =R.drawable.images;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureButtons()
        configureRecyclerView()



    }

    private fun configureRecyclerView() {
        val recyclerView=findViewById<RecyclerView>(R.id.userProfileList);

        // this creates a vertical layout Manager
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<UserViewModel>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(UserViewModel(R.drawable.images, "John Doe","john.doe@web.de","+49 176 315789"))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = RecyclerViewAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerView.adapter = adapter

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

}




