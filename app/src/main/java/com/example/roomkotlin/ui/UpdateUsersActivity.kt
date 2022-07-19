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

class UpdateUsersActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var namaAnda : EditText
    private lateinit var alamatAnda : EditText
    private lateinit var phoneAnda : EditText
    private lateinit var emailAnda : EditText
    private lateinit var dobAnda : EditText
    private lateinit var genderAnda : EditText
    private lateinit var simpanData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_users)
        namaAnda = findViewById(R.id.EditTextFullNameUpdate)
        alamatAnda = findViewById(R.id.EditTextAlamatUpdate)
        phoneAnda = findViewById(R.id.EditTextPhoneUpdate)
        emailAnda = findViewById(R.id.EditTextEmailUpdate)
        dobAnda = findViewById(R.id.EditTextDobUpdate)
        genderAnda = findViewById(R.id.EditTextGenderUpdate)
        simpanData = findViewById(R.id.buttonUpdateData)


        // ambil data
        val intent = intent
        if (intent.hasExtra(EXTRA_ID)){
            namaAnda.setText(intent.getStringExtra(EXTRA_FULLNAME))
            alamatAnda.setText(intent.getStringExtra(EXTRA_ALAMAT))
            phoneAnda.setText(intent.getStringExtra(EXTRA_PHONE))
           emailAnda.setText(intent.getStringExtra(EXTRA_EMAIL))
            dobAnda.setText(intent.getStringExtra(EXTRA_DOB))
            genderAnda.setText(intent.getStringExtra(EXTRA_GENDER))
        }
        simpanData.setOnClickListener {
            val updateData = Intent()
            val nama = namaAnda.text.toString()
            val alamat = alamatAnda.text.toString()
            val phone = phoneAnda.text.toString()
            val email = emailAnda.text.toString()
            val dob = dobAnda.text.toString()
            val gender = genderAnda.text.toString()

            Log.d(TAG, "Nama: $nama , Alamat: $alamat, Phone: $phone, Email : $email, TTL: $dob, Jekel: $gender")

            if (TextUtils.isEmpty(nama)
                || TextUtils.isEmpty(alamat)
                || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(email)
                || TextUtils.isEmpty(dob)
                || TextUtils.isEmpty(gender))
            {
                Toast.makeText(applicationContext,"Kolom Data Wajib Di Isi", Toast.LENGTH_LONG).show()
                setResult(Activity.RESULT_CANCELED , updateData)
            }else
            {
                var id : Int = intent.getIntExtra(EXTRA_ID, -1)
                Log.d(TAG, "ID: $id")
                updateData.putExtra(EXTRA_FULLNAME, nama)
                updateData.putExtra(EXTRA_ALAMAT,alamat)
                updateData.putExtra(EXTRA_PHONE,phone)
                updateData.putExtra(EXTRA_EMAIL,email)
                updateData.putExtra(EXTRA_DOB,dob)
                updateData.putExtra(EXTRA_GENDER,gender)
                updateData.putExtra(EXTRA_ID,id);
                setResult(Activity.RESULT_OK, updateData)


                Log.d(TAG, "onCreate Users: $updateData")

            }
            finish()
        }
    }
    companion object {
        const val EXTRA_ID = "com.example.crudroom.EXTRA_ID"
        const val EXTRA_FULLNAME = "com.example.crudroom.EXTRA_FULLNAME"
        const val EXTRA_ALAMAT = "com.example.crudroom.EXTRA_ALAMAT"
        const val EXTRA_PHONE = "com.example.crudroom.EXTRA_PHONE"
        const val EXTRA_EMAIL = "com.example.crudroom.EXTRA_EMAIL"
        const val EXTRA_DOB = "com.example.crudroom.EXTRA_DOB"
        const val EXTRA_GENDER = "com.example.crudroom.EXTRA_GENDER"
    }
}