package com.example.myapplication2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null , DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "Mydatabase.db"
        private const val  DATABASE_VERSION = 1

        const val TABLE_NAME = "notes"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
    }




    fun addNote( name:String ): Long{
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        return db.insert(TABLE_NAME,null,values)
    }

    fun updateNote(id:Long, name: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, name)
        return db.update(TABLE_NAME,values,"$COLUMN_ID = ? ", arrayOf(id.toString()))
    }

    fun deleteNote(id:Long){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,"$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun getAllNotes(): List<Notes>{
        val notes = mutableListOf<Notes>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()){
         do {
             val note = Notes(
                 cursor.getLong(cursor.getColumnIndex(COLUMN_ID)),
                 cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
             )
             notes.add(note)
         }  while(cursor.moveToNext())
        }
        cursor.close()
        return notes
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT)"
                )
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

}
data class Notes(val id: Long, val name: String)