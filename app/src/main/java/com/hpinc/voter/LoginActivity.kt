package com.hpinc.voter


import android.Manifest
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.Toast

import com.example.feedback.R


class LoginActivity : Activity() {
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var entercode: EditText

    private lateinit var db: DatabaseHelper
    private lateinit var sb: SQLiteDatabase
    private val PERMISSION_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userName = findViewById<View>(R.id.editText1) as EditText
        password = findViewById<View>(R.id.editText2) as EditText
        entercode = findViewById<View>(R.id.editText3) as EditText

        db = DatabaseHelper(this@LoginActivity)
        sb = db.readableDatabase

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

            // Here, thisActivity is the current activity
            if (ContextCompat.checkSelfPermission(this@LoginActivity,
                            Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this@LoginActivity,
                            Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this@LoginActivity,
                                Manifest.permission.SEND_SMS)) {

                } else {
                    ActivityCompat.requestPermissions(this@LoginActivity,
                            arrayOf(Manifest.permission.SEND_SMS, Manifest.permission.READ_PHONE_STATE),
                            PERMISSION_REQUEST_CODE)
                }
            }
        }

    }

    fun Login(v: View) {

        user = userName.text.toString()

        pass = password.text.toString()

        c = entercode.text.toString()

        if (user == "" || pass == "" || c == "") {
            Toast.makeText(applicationContext, "Please enter Name or Password or code", Toast.LENGTH_SHORT).show()
        } else {
            var args = arrayOf(user, pass)
            val crs = sb.rawQuery("SELECT * FROM LOGGER WHERE first_name = ? and password = ?", args)
            args = arrayOf(user, pass, c)
            val crCode = sb.rawQuery("SELECT * FROM LOGGER WHERE first_name = ? and password = ? and Register = ?", args)
            if (crs.count == 0) {

                Toast.makeText(applicationContext, "Username or Password Invalid", Toast.LENGTH_SHORT).show()

            } else {
                if (crCode.count != 0) {

                    if (db.getVotedStatus(user, pass) == "true") {
                        Toast.makeText(applicationContext, "Already voted...", Toast.LENGTH_SHORT).show()
                    } else {
                        val home = Intent(this@LoginActivity, VoteActivity::class.java)

                        startActivity(home)
                        overridePendingTransition(R.anim.open_translate, R.anim.close_scale)
                    }
                } else {
                    Toast.makeText(applicationContext, "Invalid Registration code", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }


    fun Register(v: View) {
        val register1 = Intent(this@LoginActivity, RegisterActivity::class.java)
        startActivity(register1)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_login, menu)
        return true
    }

    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        val setIntent = Intent(Intent.ACTION_MAIN)
        setIntent.addCategory(Intent.CATEGORY_HOME)
        setIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(setIntent)
    }

    companion object {
        lateinit var user: String
        lateinit var pass: String
        lateinit var c: String
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (!(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(this, "Please grant permission for sending sms to Verification client", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}
