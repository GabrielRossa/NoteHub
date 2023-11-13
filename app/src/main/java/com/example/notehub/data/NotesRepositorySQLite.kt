package com.example.notehub.data

import com.example.notehub.models.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositorySQLite
    @Inject constructor(val noteDao: NoteDao)
    : NotesRepository {
    override val allNotes: Flow<List<Note>>
        get() = noteDao.getAllNotes()

    override suspend fun insert(note: Note) {
        if(note.id == 0 ){
            noteDao.insert(note)
        }else{
            noteDao.update(note.id, note.title, note.note)
        }
    }

    override suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
    }