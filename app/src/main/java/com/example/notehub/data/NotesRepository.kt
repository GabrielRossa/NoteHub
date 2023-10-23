package com.example.notehub.data

import androidx.lifecycle.LiveData
import com.example.notehub.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NotesRepository
    @Inject constructor(private val noteDao: NoteDao) {

    val allNotes : Flow<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note : Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

    suspend fun update(note: Note){
        noteDao.update(note.id, note.title, note.note)
    }

}