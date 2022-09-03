package com.pppig236.scoutingappredo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MainActivity : AppCompatActivity() {
    private val permissionsRequestCode = 123
    private lateinit var managePermissions: ManagePermissions

    private lateinit var tableRecyclerView: RecyclerView
    private var userList = ArrayList<User>()
    private lateinit var tableRowAdapter: TableRowAdapter
    private lateinit var user: User

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userList.add(User("573", 1, 100))

        tableRecyclerView = findViewById(R.id.table_recycler_view)
        tableRowAdapter = TableRowAdapter(userList)

        tableRecyclerView.layoutManager = LinearLayoutManager(this)
        tableRecyclerView.adapter = tableRowAdapter

        // Initialize a list of required permissions to request runtime
        val list = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // Initialize a new instance of ManagePermissions class
        managePermissions = ManagePermissions(this, list, permissionsRequestCode)

        val buttonScanner = findViewById<Button>(R.id.scanner_view)
        val fragment = ScannerFragment()
        val headerLayout = findViewById<TableLayout>(R.id.table_heading_layout)
        buttonScanner.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()
            showHide(buttonScanner)
            showHide(headerLayout)
            showHide(tableRecyclerView)
        }
        val buttonPerm = findViewById<Button>(R.id.btnRequest)

        if (managePermissions.isPermissionsGranted() == 0)
            showHide(buttonPerm)

        // Button to check permissions states
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
            showHide(buttonPerm)
        }
    }

    private fun showHide(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) {
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