package com.example.roomkotlin.ui

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomkotlin.R
import com.example.roomkotlin.adapter.UsersListAdapter
import com.example.roomkotlin.model.Users
import com.example.roomkotlin.viewmodel.UsersViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var usersViewModel: UsersViewModel
    private lateinit var adapter: UsersListAdapter
    private var insertUserActivityRequestCode = 1
    private var updateUsersActivityRequestCode = 2
    private var deleteUsersActivityRequestCode = 3
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = UsersListAdapter()
        val recyclerView : RecyclerView = findViewById(R.id.recyclerviewUsers)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        usersViewModel = ViewModelProviders.of(this)[UsersViewModel::class.java]

        usersViewModel.getAllUsers().observe(this, Observer {
            Log.d(TAG, "Users Observer: $it")
            adapter.submitList(it)
        })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or  ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                usersViewModel.delete(adapter.getUsersAt(viewHolder.adapterPosition))
                Toast.makeText(applicationContext,"Users Delete",Toast.LENGTH_SHORT).show()
            }
        })

        adapter.setOnItemClickListener(object : UsersListAdapter.OnItemCLickListener{
            override fun onItemClick(users: Users) {
                val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
                alertDialog.setMessage("Apa Yang Ingin Anda Lakukan Dengan Data Ini ?")
                alertDialog.setPositiveButton(getString(R.string.update), object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val updateData = Intent(this@MainActivity, UpdateUsersActivity::class.java)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_ID, users.id)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_FULLNAME,users.fullName)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_ALAMAT,users.alamat)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_PHONE,users.phone)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_EMAIL,users.email)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_DOB,users.dob)
                        updateData.putExtra(UpdateUsersActivity.EXTRA_GENDER,users.gender)
                        startActivityForResult(updateData,updateUsersActivityRequestCode)
                    }
                }).setNegativeButton(getString(R.string.delete), object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        val deleteData = Intent(this@MainActivity, DeleteUsersActivity::class.java)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_ID, users.id)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_FULLNAME,users.fullName)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_ALAMAT,users.alamat)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_PHONE,users.phone)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_EMAIL,users.email)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_DOB,users.dob)
                        deleteData.putExtra(DeleteUsersActivity.EXTRA_GENDER,users.gender)
                        startActivityForResult(deleteData,deleteUsersActivityRequestCode)
                    }

                }).create().show()
            }

        })

        val fabadd : FloatingActionButton = findViewById(R.id.fabAdd)
        fabadd.setOnClickListener {
            val insertData = Intent(this,NewUsersActivity::class.java)
            startActivityForResult(insertData,insertUserActivityRequestCode)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null && requestCode == insertUserActivityRequestCode && resultCode == RESULT_OK)
        {
            val fullName : String = data.getStringExtra(NewUsersActivity.EXTRA_FULLNAME).toString()
            val alamat : String = data.getStringExtra(NewUsersActivity.EXTRA_ALAMAT).toString()
            val phone : String = data.getStringExtra(NewUsersActivity.EXTRA_PHONE).toString()
            val email : String = data.getStringExtra(NewUsersActivity.EXTRA_EMAIL).toString()
            val dob : String = data.getStringExtra(NewUsersActivity.EXTRA_DOB).toString()
            val gender : String = data.getStringExtra(NewUsersActivity.EXTRA_GENDER).toString()

            var users = Users(fullName, alamat, phone, email, dob, gender)
            usersViewModel.insert(users)
            Log.d(TAG, "onActivityResult INsert: $users")
            Toast.makeText(applicationContext,"Users Inserted",Toast.LENGTH_SHORT).show()
        }else if (data != null && requestCode == updateUsersActivityRequestCode && resultCode == RESULT_OK)
        {
            val id  = data.getIntExtra(UpdateUsersActivity.EXTRA_ID,-1)
            Log.i(TAG, "ID Users: $id")
            if (id == -1)
            {
                Toast.makeText(applicationContext,"Update gagal",Toast.LENGTH_SHORT).show()
                return
            }
            val fullNameupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_FULLNAME).toString()
            val alamatupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_ALAMAT).toString()
            val phoneupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_PHONE).toString()
            val emailupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_EMAIL).toString()
            val dobupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_DOB).toString()
            val genderupdate : String = data.getStringExtra(UpdateUsersActivity.EXTRA_GENDER).toString()
            var usersupdate = Users(fullNameupdate, alamatupdate, phoneupdate, emailupdate, dobupdate, genderupdate,id)
            usersViewModel.update(usersupdate)
            Toast.makeText(applicationContext,"Update Berhasil",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityResult Update: $usersupdate")
        }else if (data != null && requestCode == deleteUsersActivityRequestCode && resultCode == RESULT_OK)
        {
            val id  = data.getIntExtra(DeleteUsersActivity.EXTRA_ID,-1)
            Log.i(TAG, "ID Users: $id")
            if (id == -1)
            {
                Toast.makeText(applicationContext,"Delete gagal",Toast.LENGTH_SHORT).show()
                return
            }
            val fullName : String = data.getStringExtra(DeleteUsersActivity.EXTRA_FULLNAME).toString()
            val alamat : String = data.getStringExtra(DeleteUsersActivity.EXTRA_ALAMAT).toString()
            val phone : String = data.getStringExtra(DeleteUsersActivity.EXTRA_PHONE).toString()
            val email : String = data.getStringExtra(DeleteUsersActivity.EXTRA_EMAIL).toString()
            val dob : String = data.getStringExtra(DeleteUsersActivity.EXTRA_DOB).toString()
            val gender : String = data.getStringExtra(DeleteUsersActivity.EXTRA_GENDER).toString()
            var users = Users(fullName, alamat, phone, email, dob, gender,id)
            usersViewModel.delete(users)
            Toast.makeText(applicationContext,"Delete berhasil",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "onActivityResult Delete: $users")
        }else{
            Toast.makeText(applicationContext,"Users tidak tersimpan",Toast.LENGTH_SHORT).show()
        }
    }
}