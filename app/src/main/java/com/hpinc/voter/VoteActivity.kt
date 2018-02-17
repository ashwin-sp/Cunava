package com.hpinc.voter

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

import com.example.feedback.R


class VoteActivity : Activity() {

    private var radioGroup: RadioGroup? = null
    private var candidate1: RadioButton? = null
    private var candidate2: RadioButton? = null
    private var nato: RadioButton? = null
    private var button: Button? = null

    private var db = DatabaseHelper(this@VoteActivity)
    private lateinit var sb: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote)
        sb = db.readableDatabase

        radioGroup = findViewById<View>(R.id.radioGroup1) as RadioGroup



        radioGroup!!.setOnCheckedChangeListener { group, checkedId ->
            // find which radio button is selected
            if (checkedId == R.id.radio0) {
                Toast.makeText(applicationContext, "choice: ASSA", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radio1) {
                Toast.makeText(applicationContext, "choice: PHPRY", Toast.LENGTH_SHORT).show()
            } else if (checkedId == R.id.radioButton5) {
                Toast.makeText(applicationContext, "choice: NOTA", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "choose any one option",
                        Toast.LENGTH_SHORT).show()
            }
        }


        candidate1 = findViewById<View>(R.id.radio0) as RadioButton
        candidate2 = findViewById<View>(R.id.radio1) as RadioButton
        nato = findViewById<View>(R.id.radioButton5) as RadioButton

        button = findViewById<View>(R.id.button1) as Button
        button!!.setOnClickListener {
            val selectedId = radioGroup!!.checkedRadioButtonId
            // find which radioButton is checked by id
            when (selectedId) {
                candidate1!!.id -> {
                    Toast.makeText(applicationContext, "You chose 'ASSA'", Toast.LENGTH_SHORT).show()
                    var query = "UPDATE LOGGER SET voted='true' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                    query = "UPDATE LOGGER SET castedTo='ASSA' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                }
                candidate2!!.id -> {
                    Toast.makeText(applicationContext, "You chose 'PHPRY'", Toast.LENGTH_SHORT).show()
                    var query = "UPDATE LOGGER SET voted='true' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                    query = "UPDATE LOGGER SET castedTo='PHPRY' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                }
                nato!!.id -> {
                    Toast.makeText(applicationContext, "You chose 'NOTA'", Toast.LENGTH_SHORT).show()
                    var query = "UPDATE LOGGER SET voted='true' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                    query = "UPDATE LOGGER SET castedTo='NOTA' WHERE first_name='"+ LoginActivity.user +"' and password='"+LoginActivity.pass+"'"
                    sb.execSQL(query)
                }
                else -> Toast.makeText(applicationContext, "you did not choose any option", Toast.LENGTH_SHORT).show()
            }

            val i = Intent(applicationContext, ResultActivity::class.java)

            startActivity(i)
        }

    }

    override fun onBackPressed() {
        Log.d("CDA", "onBackPressed Called")
        val setIntent = Intent(Intent.ACTION_MAIN)
        setIntent.addCategory(Intent.CATEGORY_HOME)
        setIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(setIntent)
    }

}


