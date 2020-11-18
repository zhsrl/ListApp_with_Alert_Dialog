package com.e.listapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton

class ListAdapter(var userList: MutableList<User?>, val context: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view){

        lateinit var userImg: ImageView
        lateinit var userFullName: TextView
        lateinit var removeButton: Button

        fun initAll(){
            userImg = view.findViewById(R.id.userImgIV)
            userFullName = view.findViewById(R.id.userFullNameTV)
            removeButton = view.findViewById(R.id.removeBTN)
        }

        fun bind(user: User?){
            initAll()

            Glide.with(view)
                .load(user!!.userImage)
                .into(userImg)

            userFullName.text = user.fullname

            removeButton.setOnClickListener {
                remove(position)
            }
        }

        fun remove(position: Int){
            userList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, userList.size)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_model,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }





}