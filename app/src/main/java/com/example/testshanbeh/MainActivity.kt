package com.example.testshanbeh

import android.annotation.SuppressLint
import android.content.Context

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View

import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.LinearLayoutCompat


class MainActivity : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fullname = findViewById<EditText>(R.id.fullname)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val pass = findViewById<EditText>(R.id.password)
        val repass = findViewById<EditText>(R.id.repassword)
        val gender = findViewById<RadioGroup>(R.id.radioGroup1)
        val radiobutton1 = findViewById<RadioButton>(R.id.radioButton1)
        val radiobutton2 = findViewById<RadioButton>(R.id.radioButton2)
        val btnreg = findViewById<Button>(R.id.btnregister)
        val btninf = findViewById<Button>(R.id.btnshowinfo)
        val fullnameinfo = findViewById<TextView>(R.id.fullnameinfo)
        val usernameinfo = findViewById<TextView>(R.id.usernameinfo)
        val emailinfo = findViewById<TextView>(R.id.emailinfo)
        val passinfo = findViewById<TextView>(R.id.passinfo)
        val genderinfo = findViewById<TextView>(R.id.geninfo)
        val minfo = findViewById<LinearLayoutCompat>(R.id.minfo)
        minfo.visibility = View.INVISIBLE
        val hidinfo = findViewById<Button>(R.id.hideinfo)
        val shpref = getSharedPreferences("info", Context.MODE_PRIVATE)
        fun validateFullName():Boolean{
          return  if (fullname.text.isEmpty()) {
              fullname.error = "field can't be empty!!"
              false
            }else true

        }
        fun validateUserName():Boolean{
           return if (username.length() == 0) {
               username.error = "field can't be empty!!"
               false
            }else true
        }
        fun validateEmail():Boolean{
          return    if (email.length() == 0){
              email.error = "field can't be empty!!"
              false
          }else if (!Patterns.EMAIL_ADDRESS.matcher(email.text.trim()).matches()) {
              email.error = "unvalid email address!!"
              false
          }else true
        }
        fun validatePassword():Boolean{
           return if (pass.length() == 0) {
               pass.error = "field can't be empty!!"
               false
           }else true
        }
        fun validateRetypePassword():Boolean{
           return if(repass.text.isEmpty()){
               repass.error = "field can't be empty!!"
               false
           }else if(repass.text.toString() != pass.text.toString()){
               repass.error = "mismatch!!"
               false
           }else true
        }
        fun validateGender():Boolean{
            return if (gender.checkedRadioButtonId == -1) {
                Toast.makeText(this, "please enter your gender", Toast.LENGTH_SHORT).show()
                false
            }else true
        }

        btnreg.setOnClickListener {
         if (!validateFullName() || !validateUserName() ||  !validateEmail() || !validatePassword() || !validateRetypePassword() || !validateGender()){
            return@setOnClickListener
         }else {
                val sedit = shpref.edit()
                sedit.putString("fulln", fullname.text.toString()).apply()
                sedit.putString("usern", username.text.toString()).apply()
                sedit.putString("passw", pass.text.toString()).apply()
                sedit.putString("emai", email.text.toString()).apply()
                sedit.putString(
                    "gen", (if (gender.checkedRadioButtonId == radiobutton1.id) {
                        radiobutton1.text
                    } else radiobutton2.text) as String?
                ).apply()
                Toast.makeText(this, "YOU ARE REGISTRED SUCCESSFULLY", Toast.LENGTH_SHORT).show()
                fullname.text.clear()
                username.text.clear()
                email.text.clear()
                pass.text.clear()
                repass.text.clear()
                gender.clearCheck()
            }

        }
        btninf.setOnClickListener {
            minfo.visibility = View.VISIBLE
            fullnameinfo.text = shpref.getString("fulln", fullname.text.toString())
            usernameinfo.text = shpref.getString("usern", username.text.toString())
            passinfo.text = shpref.getString("passw", pass.text.toString())
            emailinfo.text = shpref.getString("emai", email.text.toString())
            if (gender.checkedRadioButtonId == radiobutton1.id) {
                genderinfo.text = radiobutton1.text
            } else {
                genderinfo.text = radiobutton2.text
            }

        }
        hidinfo.setOnClickListener {
            minfo.visibility = View.INVISIBLE
        }


    }


}

