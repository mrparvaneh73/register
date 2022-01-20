package com.example.testshanbeh

import android.annotation.SuppressLint
import android.content.Context

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View

import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.testshanbeh.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var mainbinding :ActivityMainBinding
    @SuppressLint("CommitPrefEdits")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            mainbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainbinding.root)
        mainbinding.minfo.visibility = View.INVISIBLE
        val hidinfo = findViewById<Button>(R.id.hideinfo)
        val shpref = getSharedPreferences("info", Context.MODE_PRIVATE)
        fun validateFullName():Boolean{
          return  if (mainbinding.fullname.text.isEmpty()) {
              mainbinding.fullname.error = "field can't be empty!!"
              false
            }else true

        }
        fun validateUserName():Boolean{
           return if (mainbinding.username.length() == 0) {
               mainbinding.username.error = "field can't be empty!!"
               false
            }else true
        }
        fun validateEmail():Boolean{
          return    if (mainbinding.email.length() == 0){
              mainbinding.email.error = "field can't be empty!!"
              false
          }else if (!Patterns.EMAIL_ADDRESS.matcher(mainbinding.email.text.trim()).matches()) {
              mainbinding.email.error = "unvalid email address!!"
              false
          }else true
        }
        fun validatePassword():Boolean{
           return if (mainbinding.password.length() == 0) {
               mainbinding.password.error = "field can't be empty!!"
               false
           }else true
        }
        fun validateRetypePassword():Boolean{
           return if(mainbinding.repassword.text.isEmpty()){
               mainbinding.repassword.error = "field can't be empty!!"
               false
           }else if(mainbinding.repassword.text.toString() != mainbinding.repassword.text.toString()){
               mainbinding.repassword.error = "mismatch!!"
               false
           }else true
        }
        fun validateGender():Boolean{
            return if ( mainbinding.radioGroup1       .checkedRadioButtonId == -1) {
                Toast.makeText(this, "please enter your gender", Toast.LENGTH_SHORT).show()
                false
            }else true
        }

        mainbinding.btnshowinfo.setOnClickListener {
         if (!validateFullName() || !validateUserName() ||  !validateEmail() || !validatePassword() || !validateRetypePassword() || !validateGender()){
            return@setOnClickListener
         }else {
                val sedit = shpref.edit()
                sedit.putString("fulln", mainbinding.fullname.text.toString()).apply()
                sedit.putString("usern", mainbinding.username.text.toString()).apply()
                sedit.putString("passw", mainbinding.password.text.toString()).apply()
                sedit.putString("emai", mainbinding.email.text.toString()).apply()
                sedit.putString(
                    "gen", (if (mainbinding.radioGroup1.checkedRadioButtonId == mainbinding.radioButton1.id) {
                        mainbinding.radioButton1.text
                    } else mainbinding.radioButton2.text) as String?
                ).apply()
                Toast.makeText(this, "YOU ARE REGISTRED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                mainbinding.fullname.text.clear()
                mainbinding.username.text.clear()
                mainbinding.email.text.clear()
                mainbinding.password.text.clear()
                mainbinding.repassword.text.clear()
                mainbinding.radioGroup1.clearCheck()
            }

        }
        mainbinding.btnshowinfo.setOnClickListener {
            mainbinding.minfo.visibility = View.VISIBLE
            mainbinding.fullnameinfo.text = shpref.getString("fulln", mainbinding.fullname.text.toString())
            mainbinding.usernameinfo.text = shpref.getString("usern", mainbinding.username.text.toString())
            mainbinding.passinfo.text = shpref.getString("passw", mainbinding.passinfo.text.toString())
            mainbinding.emailinfo.text = shpref.getString("emai", mainbinding.email.text.toString())
            if (mainbinding.radioGroup1.checkedRadioButtonId == mainbinding.radioButton1.id) {
                mainbinding.geninfo.text = mainbinding.radioButton1.text
            } else {
                mainbinding.geninfo.text = mainbinding.radioButton1.text
            }

        }
        hidinfo.setOnClickListener {
            mainbinding.minfo.visibility = View.INVISIBLE
        }


    }


}

