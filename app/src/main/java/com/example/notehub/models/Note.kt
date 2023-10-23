package com.example.notehub.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "notes_table")
data class Note(
    @PrimaryKey(autoGenerate = true) var id : Int?,
    @ColumnInfo(name = "title") var title : String?,
    @ColumnInfo(name = "note") var note : String?,
    @ColumnInfo(name = "date") var date : String?
) : Serializable
