package com.example.vertiefungqrvisitenkartenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner

class UserProfile : AppCompatActivity() {
    private var userImageInt: Int = 0
    private var userName :String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        configureUserProfile(

        )
    }

    private fun configureUserProfile() {
        getData()
        setData()

    }

    private fun getData() {
        if (intent.hasExtra("userName")) {
//            userImageInt = intent.getIntExtra("userImage", 1)
userName= intent.getStringExtra("userName").toString()

        } else {

            Toast.makeText(this, "Please Try Later Again", Toast.LENGTH_LONG).show()

        }

    }

    private fun setData() {
//        val userImage = findViewById<ImageView>(R.id.userProfileImage);
//        userImage.setImageResource(userImageInt);
//val


    }

}


