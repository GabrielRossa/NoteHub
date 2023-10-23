package com.example.notehub

import android.app.Application
import android.content.Context
import com.example.notehub.data.NoteDao
import com.example.notehub.data.NoteDataBaseSQL
import com.example.notehub.data.NotesRepository
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
    fun provideNotesRepository(noteDao: NoteDao) : NotesRepository{
        return NotesRepository(noteDao)
    }

    @Provides
    fun provideNoteDao(database: NoteDataBaseSQL) : NoteDao{
        return database.getNoteDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext ctx : Context): NoteDataBaseSQL{
        return NoteDataBaseSQL.getDatabase(ctx)
    }
}