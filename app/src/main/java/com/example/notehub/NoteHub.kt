package com.example.notehub

import android.app.Application
import android.content.Context
import com.example.notehub.data.NoteDao
import com.example.notehub.data.NoteDataBaseSQL
import com.example.notehub.data.NotesRepository
import com.example.notehub.data.NotesRepositoryFirebase
import com.example.notehub.data.NotesRepositorySQLite
import com.google.firebase.Firebase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class NoteHub : Application(){

    @Provides
    fun provideNotesRepository(noteDao: NoteDao)
    : NotesRepositorySQLite{
        return NotesRepositorySQLite(noteDao)
    }

    @Provides
    fun provideNotesRepositoryFirebase(notesRef: CollectionReference)
    : NotesRepository {
        return NotesRepositoryFirebase(notesRef)
    }

    @Provides
    fun provideNoteDao(database: NoteDataBaseSQL) : NoteDao{
        return database.getNoteDao()
    }

    @Provides
    fun provideNotesRef(): CollectionReference{
        return Firebase.firestore.collection("notes")
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx : Context): NoteDataBaseSQL{
        return NoteDataBaseSQL.getDatabase(ctx)
    }
}