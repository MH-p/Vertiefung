package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class UserRegister : AppCompatActivity() {
    private var imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_register)
        configureButtons()


    }

    private fun configureButtons() {
        configureRegisterButton()
        configureImageButton()

    }

    private fun configureImageButton() {
        val selectImageButton = findViewById<Button>(R.id.userRegisterUploadImageButton)

        selectImageButton.setOnClickListener {

            selectImage()


        }


    }

    private fun selectImage() {


        val intent = Intent();
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 100)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && data != null && data.data != null) {

            imageUri = data.data
            val imageView = findViewById<ImageView>(R.id.registerUploadImageView)

            imageView.setImageURI(imageUri)
        }
    }


    private fun configureRegisterButton() {
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

            val storage = FirebaseStorage.getInstance("gs://vertiefungfhws.appspot.com")
                .getReference(phoneNumber)








            imageUri?.let { image ->
                storage.putFile(image).addOnSuccessListener { println("worked") }
                    .addOnFailureListener { "dont" }
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
                Toast.makeText(this, "Not ok", Toast.LENGTH_LONG).show()
            }


        }

    }


}