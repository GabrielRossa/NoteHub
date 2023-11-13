package com.example.notehub.data

import androidx.lifecycle.LiveData
import com.example.notehub.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface NotesRepository{
    val allNotes : Flow<List<Note>>
    suspend fun insert(note : Note)
    suspend fun delete(note: Note)
}