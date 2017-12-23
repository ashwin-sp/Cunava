package com.hpinc.voter


import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.app.Activity
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userName = findViewById<View>(R.id.editText1) as EditText
        password = findViewById<View>(R.id.editText2) as EditText
        entercode = findViewById<View>(R.id.editText3) as EditText

        db = DatabaseHelper(this@LoginActivity)
        sb = db.readableDatabase

    }

    fun Login(v: View) {

        user = userName.text.toString()

        pass = password.text.toString()

        c = entercode.text.toString()

        if (user == "" || pass == "" || c == "") {
            Toast.makeText(applicationContext, "Please enter Name or Password or code", Toast.LENGTH_SHORT).show()
        } else {
            val args = arrayOf(user)
            val crs = sb.rawQuery("SELECT * FROM LOGGER WHERE first_name = ?", args)
            val sappa = db.getyourdata2(user, pass)
            val s = Integer.toString(sappa)
            if (crs.count == 0) {

                Toast.makeText(applicationContext, "Username or Password Invalid", Toast.LENGTH_SHORT).show()

            } else {
                if (c == s) {

                    if (RegisterActivity.count[db.getyourdata2(user, pass)] == 1) {
                        Toast.makeText(applicationContext, "Already voted...", Toast.LENGTH_SHORT).show()
                    } else {
                        val home = Intent(this@LoginActivity, VoteActivity::class.java)

                        startActivity(home)
                        overridePendingTransition(R.anim.open_translate, R.anim.close_scale)
                    }
                } else {
                    Toast.makeText(applicationContext, "Invalid Registration code" + sappa, Toast.LENGTH_SHORT).show()
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
}
