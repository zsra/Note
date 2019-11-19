package hu.zsra.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var noteId : Long = 0L,
    @ColumnInfo(name = "created_at")
    var createdAt : Long = System.currentTimeMillis(),
    @ColumnInfo(name = "title")
    var noteTitle : String = "Untitled_ $createdAt",
    @ColumnInfo(name = "text")
    var noteText : String = ""
)