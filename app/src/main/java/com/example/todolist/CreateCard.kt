package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.view.View
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_create_card.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {

    private lateinit var database: myDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        database= Room.databaseBuilder(
            applicationContext,myDatabase::class.java,"To_Do"
        ).build()
        Save.setOnClickListener{
            if(createTitle.text.toString().trim{it<=' '}.isNotEmpty()
                && createPriority.text.toString().trim{it<=' '}.isNotEmpty() ){
                var title=createTitle.getText().toString()
                var priority=createPriority.getText().toString()
                DataObject.setData(title,priority)
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0,title,priority))
                }

                val intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}