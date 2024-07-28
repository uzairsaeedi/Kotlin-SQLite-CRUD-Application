package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var databseHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAdd: FloatingActionButton
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        fabAdd = findViewById(R.id.fabAdd)

        databseHelper = DatabaseHelper(this)

        fabAdd.setOnClickListener{
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        updateRecyclerView()
    }
    private fun updateRecyclerView(){
        val notes = databseHelper.getAllNotes()
        adapter = NoteAdapter(notes){
            note->
            deleteNote(note)
        }
        recyclerView.layoutManager =LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun deleteNote(note: Notes){
        databseHelper.deleteNote(note.id)
        updateRecyclerView()
    }

    override fun onResume(){
        super.onResume()

        val notes = databseHelper.getAllNotes()
        recyclerView.adapter=NoteAdapter(notes){
            note->
            deleteNote(note)
        }
    }
}