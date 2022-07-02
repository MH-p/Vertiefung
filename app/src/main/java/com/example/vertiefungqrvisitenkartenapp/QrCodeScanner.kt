package com.example.vertiefungqrvisitenkartenapp

import android.content.ContentValues.TAG
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*
import com.google.firebase.database.*
import java.util.*


class QrCodeScanner : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code_scanner)
        configureButtons()
        startQrCodeScanner()


    }


    private fun configureButtons() {

        val makeFriendButton = findViewById<Button>(R.id.makeFriendButton);
        makeFriendButton.setOnClickListener {
//           val scannerView= findViewById<CodeScannerView>(R.id.scanner_view);
//            scannerView.setTransitionVisibility(View.GONE);


        }
        val addFriendButton = findViewById<Button>(R.id.addFriendButton)
        addFriendButton.setOnClickListener {

        }


    }


    private fun startQrCodeScanner() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA),
                123
            )
        } else {
            startScanning()
            loadAndSaveScannedUser("1232312")
        }
    }

    private fun startScanning() {

        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)
        codeScanner = CodeScanner(this, scannerView)
        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()

            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }


    }


    private fun loadAndSaveScannedUser(phoneNumberOfScannedUser: String) {

        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/Users")

        database.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, prevChildKey: String?) {
                val user: UserData? = dataSnapshot.getValue(UserData::class.java)
                if (user != null && user.phoneNumber == phoneNumberOfScannedUser) {
                    saveScannedUser(user)
                }

            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, prevChildKey: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })


    }


    fun saveScannedUser(user: UserData) {

        val database =
            FirebaseDatabase.getInstance("https://vertiefungfhws-default-rtdb.europe-west1.firebasedatabase.app")
                .getReference("Data/Contacts")
//todo zuerst eigene tel dann gescante tel
        database.child("2222").child(user.phoneNumber).setValue(user).addOnSuccessListener {
            Toast.makeText(this, "Friend Added!", Toast.LENGTH_LONG).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Not ok", Toast.LENGTH_LONG).show()
        }


    }


    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}



