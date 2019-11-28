package hu.zsra.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/* https://jsonplaceholder.typicode.com/albums */

@Entity(tableName = "albun_table")
data class Album(
    @PrimaryKey(autoGenerate = true)
    var albumId : Long = 0L,
    @ColumnInfo(name = "userId")
    var userId: Long = 0L,
    @ColumnInfo(name = "title")
    var title: String = ""
)