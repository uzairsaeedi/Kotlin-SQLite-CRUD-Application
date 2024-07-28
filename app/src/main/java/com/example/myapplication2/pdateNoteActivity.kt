package com.example.myapplication2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class pdateNoteActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdate_note)

        val btnUpdate: Button = findViewById(R.id.btnUpdate)
        val etName: EditText =findViewById(R.id.etName)

        databaseHelper =DatabaseHelper(this)
        val id = intent.getLongExtra("id",-1)
        val name = intent.getStringExtra("name")
        etName.setText(name)

        btnUpdate.setOnClickListener{
            val newName =etName.text.toString()
            if(newName.isNotEmpty()){
                databaseHelper.updateNote(id,newName)
                finish()
            }
        }
    }
}