package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class UserRegister : AppCompatActivity() {
    private var imageUri: Uri? = null
    private lateinit var socialMediaList: ArrayList<SocialMediaData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        socialMediaList = arrayListOf()
        configureButtons()
    }

    private fun configureButtons() {
        configureRegisterButton()
        configureSelectImageButton()
        configureAddSocialMediaButton()
    }

    private fun configureAddSocialMediaButton() {
        val addSocialMediaButton = findViewById<Button>(R.id.userRegisterenterSocialMediaButton)

        addSocialMediaButton.setOnClickListener {
            val socialMediaLink = findViewById<TextView>(R.id.userRegisterSocialMediaAccountText)
            val socialMediaData = SocialMediaData(socialMediaLink.text.toString())
            socialMediaLink.text = ""
            socialMediaList.add(socialMediaData)
            val toast = Toast.makeText(this, "Account Added", Toast.LENGTH_SHORT)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }


    }

    private fun configureSelectImageButton() {
        val selectImageButton = findViewById<Button>(R.id.userRegisterUploadImageButton)
        selectImageButton.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null && data.data != null) {
            imageUri = data.data
            val imageView = findViewById<ImageView>(R.id.userRegisterUploadImageView)
            imageView.setImageURI(imageUri)
        }
    }

    private fun configureRegisterButton() {
        val userRegisterButton = findViewById<Button>(R.id.userRegisterButtonUser)
        userRegisterButton.setOnClickListener {
            saveSocialMediaData()
            saveUserData()
        }
    }

    private fun saveSocialMediaData() {
        val phoneNumber = findViewById<TextView>(R.id.userRegisterTextPhone).text.toString()
        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/SocialMedia")
        database.child(phoneNumber).setValue(socialMediaList).addOnSuccessListener {
            Toast.makeText(this, "SocialMedia Accounts Added", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "Can´t Access DataBase", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveUserData() {
        val firstName = findViewById<TextView>(R.id.userRegisterTextPersonFirstName).text.toString()
        val lastName = findViewById<TextView>(R.id.userRegisterTextPersonLastName).text.toString()
        val phoneNumber = findViewById<TextView>(R.id.userRegisterTextPhone).text.toString()
        val email = findViewById<TextView>(R.id.userRegisterTextEmailAddress).text.toString()
        val description = findViewById<TextView>(R.id.userRegisterTextDescription).text.toString()

        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/Users")

        val storage = FirebaseStorage.getInstance("gs://vertiefungfhws.appspot.com")
            .getReference(phoneNumber)

        imageUri?.let { image ->
            storage.putFile(image).addOnSuccessListener { }
                .addOnFailureListener { }
        }


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
            Toast.makeText(this, "Can´t Access DataBase", Toast.LENGTH_SHORT).show()
        }

    }

}