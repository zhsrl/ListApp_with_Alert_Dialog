package com.e.listapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recView: RecyclerView
    lateinit var adapter: ListAdapter
    lateinit var addUser: Button
    var users: MutableList<User?> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recView = findViewById(R.id.recView)
        adapter = ListAdapter(userGenerator(),applicationContext)
        recView.layoutManager = LinearLayoutManager(this)
        recView.adapter = adapter


        addUser = findViewById(R.id.addBTN)

        addUser.setOnClickListener{
            addNewContact()
        }


    }

    fun userGenerator(): MutableList<User?>{


        var user1: User = User(R.drawable.user1,"John Doe")
        var user2: User = User(R.drawable.user2, "Alisa Ten")
        var user3: User = User(R.drawable.user3,"Shamoi Pakita")
        var user4: User = User(R.drawable.user4,"Beisenbek Baisakov")
        var user5: User = User(R.drawable.user5,"Jima Lee")

        users.add(user1)
        users.add(user2)
        users.add(user3)
        users.add(user4)
        users.add(user5)

        return users
    }

    @SuppressLint("InflateParams")
    fun addNewContact(){
        val dialogView = LayoutInflater.from(applicationContext).inflate(R.layout.add_contact_model, null)

        var fullname = dialogView.findViewById<EditText>(R.id.contactFullNameET)
        var add = dialogView.findViewById<Button>(R.id.addContactBTN)
        var cancel = dialogView.findViewById<Button>(R.id.cancelBTN)

        val Builder = AlertDialog.Builder(this, R.style.Theme_AppCompat_Light)
            .setView(dialogView)
            .setTitle("Add Contact")

        val nAlert = Builder.show()

        add.setOnClickListener{
            users.add(User(fullname = fullname.text.toString().trim()))
            adapter.notifyDataSetChanged()
            nAlert.dismiss()
        }

        cancel.setOnClickListener{
            nAlert.dismiss()
        }
    }

}