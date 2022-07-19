package com.example.roomkotlin.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.roomkotlin.R

class NewUsersActivity : AppCompatActivity() {
    private lateinit var namaAnda : EditText
    private lateinit var alamatAnda : EditText
    private lateinit var phoneAnda : EditText
    private lateinit var emailAnda : EditText
    private lateinit var dobAnda : EditText
    private lateinit var genderAnda :EditText
    private lateinit var simpanData : Button
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_users)
        namaAnda = findViewById(R.id.EditTextFullNameAdd)
        alamatAnda = findViewById(R.id.EditTextAlamatAdd)
        phoneAnda = findViewById(R.id.EditTextPhoneAdd)
        emailAnda = findViewById(R.id.EditTextEmailAdd)
        dobAnda = findViewById(R.id.EditTextDobAdd)
        genderAnda = findViewById(R.id.EditTextGenderAdd)
        simpanData = findViewById(R.id.buttonInsertData)
        simpanData.setOnClickListener {
            val insertData = Intent()
            val nama = namaAnda.text.toString()
            val alamat = alamatAnda.text.toString()
            val phone = phoneAnda.text.toString()
            val email = emailAnda.text.toString()
            val dob = dobAnda.text.toString()
            val gender = genderAnda.text.toString()

            Log.i(TAG, "Nama: $nama , Alamat: $alamat, Phone: $phone, Email : $email, TTL: $dob, Jekel: $gender")

            if (TextUtils.isEmpty(nama)
                || TextUtils.isEmpty(alamat)
                || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(dob)
                || TextUtils.isEmpty(gender))
            {
                Toast.makeText(applicationContext,"Kolom Data Wajib Di Isi",Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED , insertData)
            }else
            {
                insertData.putExtra(EXTRA_FULLNAME, nama)
                insertData.putExtra(EXTRA_ALAMAT,alamat)
                insertData.putExtra(EXTRA_PHONE,phone)
                insertData.putExtra(EXTRA_EMAIL,email)
                insertData.putExtra(EXTRA_DOB,dob)
                insertData.putExtra(EXTRA_GENDER,gender)
                setResult(Activity.RESULT_OK, insertData)

                Log.i(TAG, "onCreate Users: $insertData")
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_FULLNAME = "com.example.crudroom.EXTRA_FULLNAME"
        const val EXTRA_ALAMAT = "com.example.crudroom.EXTRA_ALAMAT"
        const val EXTRA_PHONE = "com.example.crudroom.EXTRA_PHONE"
        const val EXTRA_EMAIL = "com.example.crudroom.EXTRA_EMAIL"
        const val EXTRA_DOB = "com.example.crudroom.EXTRA_DOB"
        const val EXTRA_GENDER = "com.example.crudroom.EXTRA_GENDER"
    }
}