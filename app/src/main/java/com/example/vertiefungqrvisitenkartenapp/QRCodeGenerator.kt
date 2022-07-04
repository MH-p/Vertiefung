package com.example.vertiefungqrvisitenkartenapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter


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



        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode("d", BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            (findViewById<ImageView>(R.id.generatedQrCodeView) .setImageBitmap(bmp))
        } catch (e: WriterException) {
            e.printStackTrace()
        }





//        qrCodeDisplayImage.setImageResource(qrCodeImage.hashCode())



    }


}