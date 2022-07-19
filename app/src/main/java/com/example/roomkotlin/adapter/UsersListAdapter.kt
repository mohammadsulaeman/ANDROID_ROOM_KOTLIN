package com.example.roomkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.roomkotlin.R
import com.example.roomkotlin.model.Users

class UsersListAdapter : ListAdapter<Users, UsersListAdapter.UsersViewHolder>(userDiffCallBack)
{

    lateinit var listener : OnItemCLickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return UsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val users = getItem(position)
        holder.namaAnda.text = users.fullName
        holder.alamatAnda.text = users.alamat
        holder.phoneAnda.text = users.phone
        holder.emailAnda.text = users.email
        holder.dobAnda.text = users.dob
        holder.genderAnda.text = users.gender
    }

    inner class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val namaAnda : TextView = itemView.findViewById(R.id.textViewfullnameItem)
        val alamatAnda : TextView = itemView.findViewById(R.id.textViewalamatItem)
        val phoneAnda : TextView = itemView.findViewById(R.id.textViewphoneItem)
        val emailAnda : TextView = itemView.findViewById(R.id.textViewemailItem)
        val dobAnda : TextView = itemView.findViewById(R.id.textViewdobItem)
        val genderAnda : TextView = itemView.findViewById(R.id.textViewgenderItem)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if ( position != RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(getItem(position))
                }
            }
        }


    }

    fun getUsersAt(position: Int) = getItem(position)

    companion object {
        private val userDiffCallBack = object : DiffUtil.ItemCallback<Users>(){
            override fun areItemsTheSame(oldItem: Users, newItem: Users) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Users, newItem: Users) =
                oldItem.fullName == newItem.fullName
                        && oldItem.alamat == newItem.alamat
                        && oldItem.email == newItem.email
                        && oldItem.phone == newItem.phone
                        && oldItem.dob == newItem.dob
                        && oldItem.gender == newItem.gender

        }
    }

    interface OnItemCLickListener{
       fun onItemClick(users: Users)
    }

    fun setOnItemClickListener(listener : OnItemCLickListener)
    {
        this.listener = listener
    }
}