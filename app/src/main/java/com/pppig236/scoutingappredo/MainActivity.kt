package com.pppig236.scoutingappredo

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    private val permissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize a list of required permissions to request runtime
        val list = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissions(this, list, permissionsRequestCode)

        val buttonScanner = findViewById<Button>(R.id.scanner_view)
        val fragment = ScannerFragment()
        buttonScanner.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
            showHide(buttonScanner)
        }

        val buttonPerm = findViewById<Button>(R.id.btnRequest)
        // Button to check permissions states
        showHide(buttonPerm)

        buttonPerm.setOnClickListener {
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