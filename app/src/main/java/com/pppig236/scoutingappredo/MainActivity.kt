package com.pppig236.scoutingappredo

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.*
import java.io.File


class MainActivity : AppCompatActivity() {
    private lateinit var codeScanner: CodeScanner
    private val permissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        // Initialize a list of required permissions to request runtime
        val list = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissions(this, list, permissionsRequestCode)

        val button = findViewById<Button>(R.id.btnRequest)
        // Button to check permissions states
        showHide(button)

        button.setOnClickListener {
            managePermissions.checkPermissions()
            if (Environment.isExternalStorageManager()) {
                // create a new csv file
                createCsv(Environment.getExternalStorageDirectory().path + "/Download/data.csv")
                // If you don't have access, launch a new activity to show the user the system's dialog
                // to allow access to the external storage
            } else {
                val intent = Intent()
                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
                val uri: Uri = Uri.fromParts("package", this.packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }

        // TO-DO: make a home page to display all the data!
        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            showHide(button)

            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()
            }
            appendCsv(
                Environment.getExternalStorageDirectory().path + "/Download/data.csv",
                "\n${it.text}"
            )
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

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun showHide(view: View) {
        view.visibility = if (managePermissions.isPermissionsGranted() == 0) {
            View.INVISIBLE
        } else {
            View.VISIBLE
        }
    }

    private fun createCsv(fileName: String) {
        val file = File(fileName)

        // create a new file
        file.writeText("")
    }

    private fun appendCsv(fileName: String, text: String) {
        val file = File(fileName)

        file.appendText(text)
    }
}