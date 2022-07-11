package com.example.vertiefungqrvisitenkartenapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import java.io.File

class UserProfile : AppCompatActivity() {
    private var user: UserData? = null
    private lateinit var socialMediaArrayList: ArrayList<SocialMediaData>
    private lateinit var socialMediaRecyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        socialMediaRecyclerview = findViewById(R.id.socialMediaRecyclerView)
        socialMediaRecyclerview.layoutManager = LinearLayoutManager(this)
        socialMediaRecyclerview.setHasFixedSize(true)
        socialMediaArrayList = arrayListOf()
        setUpUserProfile()
    }

    private fun setUpUserProfile() {
        getData()
        setData()
    }

    private fun getData() {
        intent?.let {
            user = intent.extras?.getParcelable("user")
        }
        val userPhoneNumber = user?.phoneNumber ?: ""
        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/SocialMedia").child(userPhoneNumber)

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val socialMediaAccountLink =
                            userSnapshot.getValue(SocialMediaData::class.java)
                        socialMediaArrayList.add(socialMediaAccountLink!!)
                    }
                    val adapter = SocialMediaRecyclerAdapter(socialMediaArrayList)
                    socialMediaRecyclerview.adapter = adapter

                    adapter.setOnUserClickListener(object :
                        SocialMediaRecyclerAdapter.OnUserClickListener {
                        override fun onUserClick(position: Int) {
                            val browserIntent =
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse(socialMediaArrayList[position].socialMediaAccountLink)
                                )
                            startActivity(browserIntent)
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun setData() {
        val fullUserName = user?.userFirstName + " " + user?.userLastName
        val userProfileName = findViewById<TextView>(R.id.userProfileNameView)
        userProfileName.text = fullUserName

        val userDescription = findViewById<TextView>(R.id.userProfileDescriptionView)
        userDescription.text = user?.description

        val userPhoneNumber = findViewById<TextView>(R.id.userProfilePhoneNumberView)
        userPhoneNumber.text = user?.phoneNumber

        val userProfileImage = findViewById<ImageView>(R.id.userProfileImage)

        val storage = user?.phoneNumber?.let {
            FirebaseStorage.getInstance("gs://vertiefungfhws.appspot.com/")
                .getReference(it)
        }

        val localFile = File.createTempFile("temp", "jpg")
        storage?.getFile(localFile)?.addOnSuccessListener {

            val profileImage = BitmapFactory.decodeFile(localFile.absolutePath)
            userProfileImage.setImageBitmap(profileImage)

        }?.addOnFailureListener {
            Toast.makeText(this, "Could Not Fetch Image!", Toast.LENGTH_SHORT).show()
        }
    }
}


