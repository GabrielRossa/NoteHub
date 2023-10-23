package com.example.notehub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notehub.models.Note
import com.example.notehub.utilities.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDataBaseSQL : RoomDatabase(){

    abstract fun getNoteDao() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDataBaseSQL? = null

        fun getDatabase(context: Context) : NoteDataBaseSQL{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBaseSQL::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance

                instance

            }
        }

    }
}