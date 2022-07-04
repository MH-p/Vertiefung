package com.example.vertiefungqrvisitenkartenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class UserProfile : AppCompatActivity() {
    private var user:UserData? = null

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


        intent?.let {
            user= intent.extras?.getParcelable<UserData>("user")
        }


    }

    private fun setData() {
//        val userImage = findViewById<ImageView>(R.id.userProfileImage);
//        userImage.setImageResource(userImageInt);


        val fullUserName = user?.userFirstName +" "+ user?.userLastName

        val userProfileName=findViewById<TextView>(R.id.userProfileNameView)
        userProfileName.text = fullUserName

        val userDescription=findViewById<TextView>(R.id.userProfileDescriptionView)
        userDescription.text = user?.description ?: "can't fetch Data!"

        val userPhoneNumber=findViewById<TextView>(R.id.userProfilePhoneNumberView)
        userPhoneNumber.text = user?.phoneNumber ?: "can't fetch Data!"




    }

}


