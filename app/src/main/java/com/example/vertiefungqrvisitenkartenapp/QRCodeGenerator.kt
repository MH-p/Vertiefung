package com.example.vertiefungqrvisitenkartenapp


import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import io.github.g0dkar.qrcode.QRCode
import java.io.File

import java.io.FileOutputStream


class QRCodeGenerator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_generator)
        generateQrCode()
    }

    private fun generateQrCode() {
        val sharedPreferences = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val savedPhoneNumber = sharedPreferences.getString("PHONE_NUMBER",null)

        val qrCodeDisplayImage = findViewById<ImageView>(R.id.generatedQrCodeView)


        val localFile = File.createTempFile("test", "jpg")


      FileOutputStream(localFile).use {
            QRCode("https://github.com/g0dkar/qrcode-kotlin")
                .render()
                .writeImage(it)
        }






//        qrCodeDisplayImage.setImageResource(qrCodeImage.hashCode())



    }


}