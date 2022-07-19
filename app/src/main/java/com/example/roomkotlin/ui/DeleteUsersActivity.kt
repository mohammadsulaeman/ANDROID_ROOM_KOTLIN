package com.example.roomkotlin.ui

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DiffUtil
import com.example.roomkotlin.R

class DeleteUsersActivity : AppCompatActivity() {
    private var TAG = "MainActivity"
    private lateinit var namaAnda : TextView
    private lateinit var alamatAnda : TextView
    private lateinit var phoneAnda : TextView
    private lateinit var emailAnda : TextView
    private lateinit var dobAnda : TextView
    private lateinit var genderAnda : TextView
    private lateinit var deleteData : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_users)
        namaAnda = findViewById(R.id.textViewFullNameDelete)
        alamatAnda = findViewById(R.id.textViewAlamatDelete)
        phoneAnda = findViewById(R.id.textViewPhoneDelete)
        emailAnda = findViewById(R.id.textViewEmailDelete)
        dobAnda = findViewById(R.id.textViewDobDelete)
        genderAnda = findViewById(R.id.textViewGenderDelete)
        deleteData = findViewById(R.id.buttonDeleteData)

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

        deleteData.setOnClickListener {
            val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@DeleteUsersActivity)
            alertDialog.setMessage("Apa Anda Yakin Menghapus Data Ini ?")
            alertDialog.setPositiveButton("YAKIN!", object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    val deleteDataUsers = Intent()
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
                        setResult(Activity.RESULT_CANCELED , deleteDataUsers)
                    }else
                    {
                        var id : Int = intent.getIntExtra(EXTRA_ID, -1)
                        Log.d(TAG, "ID: $id")
                        deleteDataUsers.putExtra(EXTRA_FULLNAME, nama)
                        deleteDataUsers.putExtra(EXTRA_ALAMAT,alamat)
                        deleteDataUsers.putExtra(EXTRA_PHONE,phone)
                        deleteDataUsers.putExtra(EXTRA_EMAIL,email)
                        deleteDataUsers.putExtra(EXTRA_DOB,dob)
                        deleteDataUsers.putExtra(EXTRA_GENDER,gender)
                        deleteDataUsers.putExtra(EXTRA_ID,id);
                        setResult(Activity.RESULT_OK, deleteDataUsers)

                        Log.d(TAG, "onCreate Users: $deleteDataUsers")

                    }
                    finish()
                }

            }).setNegativeButton("TIDAK!",object : DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface, which: Int) {
                    dialog.dismiss()
                }

            }).create().show()
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