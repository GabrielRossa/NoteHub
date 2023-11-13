package com.example.notehub.data

import com.example.notehub.models.Note
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class NotesRepositoryFirebase
    @Inject constructor(private val notesRef: CollectionReference)
    : NotesRepository{

    private var _notes = MutableStateFlow(listOf<Note>())
    override val allNotes: Flow<List<Note>>
        get() = _notes.asStateFlow()

    init {
        notesRef.addSnapshotListener { snapshot, _ ->
            if(snapshot != null){
                var notes = mutableListOf<Note>()
                snapshot.documents.forEach{ doc ->
                    val note = doc.toObject<Note>()
                    if (note != null){
                        note.docId = doc.id
                        notes.add(note)
                    }
                }
                _notes.value = notes
            }else{
                _notes = MutableStateFlow(listOf())
            }
        }
    }

    override suspend fun insert(note: Note) {
        val doc = if (note.docId.isNullOrEmpty()) {
            notesRef.document()
        } else {
            notesRef.document(note.docId)
        }
        note.docId = doc.id
        doc.set(note)
    }

    override suspend fun delete(note: Note) {
        notesRef.document(note.docId).delete()
    }
}