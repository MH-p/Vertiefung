package com.example.vertiefungqrvisitenkartenapp

//import android.content.pm.PackageManager
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import com.budiyev.android.codescanner.AutoFocusMode
//import com.budiyev.android.codescanner.CodeScanner
//import com.budiyev.android.codescanner.CodeScannerView
//import com.budiyev.android.codescanner.DecodeCallback
//import com.budiyev.android.codescanner.ErrorCallback
//import com.budiyev.android.codescanner.ScanMode
//
//
//class QrGeneratorFragment : Fragment() {
//    private lateinit var codeScanner: CodeScanner
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        var v = inflater.inflate(R.layout.fragment_qr_generator, container, false)
//
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.CAMERA
//            ) == PackageManager.PERMISSION_DENIED
//        ) {
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(android.Manifest.permission.CAMERA),
//                123
//            )
//        } else {
//            val scannerView = v.findViewById<CodeScannerView>(R.id.scanner_view)
//
//
//            codeScanner = CodeScanner(requireContext(), scannerView)
//
//            // Parameters (default values)
//            codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
//            codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
//            // ex. listOf(BarcodeFormat.QR_CODE)
//            codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
//            codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
//            codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
//            codeScanner.isFlashEnabled = false // Whether to enable flash or not
//
//            // Callbacks
//            codeScanner.decodeCallback = DecodeCallback {
//                requireActivity().runOnUiThread {
////                   Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
//                }
//            }
//            codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
//                requireActivity().runOnUiThread {
////                    Toast.makeText(
////                        this, "Camera initialization error: ${it.message}",
////                        Toast.LENGTH_LONG
////                    ).show()
//                }
//            }
//
//            scannerView.setOnClickListener {
//                codeScanner.startPreview()
//            }
//        }
//
//
//
//
//
//
//
//
//
//
//
//        return  v
//    }
//
//
//
//    override fun onResume() {
//        super.onResume()
//        codeScanner.startPreview()
//    }
//
//    override fun onPause() {
//        codeScanner.releaseResources()
//        super.onPause()
//    }
//
//}