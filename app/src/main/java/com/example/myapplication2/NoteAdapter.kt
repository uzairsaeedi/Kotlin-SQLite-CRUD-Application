package com.example.myapplication2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val notes:List<Notes>, private val onDeleteClick: (Notes)->Unit):
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
       val note = notes[position]
        holder.tvName.text = note.name
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, pdateNoteActivity::class.java)
            intent.putExtra("id",note.id)
            intent.putExtra("name", note.name)
            holder.itemView.context.startActivity(intent)
        }
        holder.btnDelete.setOnClickListener{
            onDeleteClick(note)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note,parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

    }
}