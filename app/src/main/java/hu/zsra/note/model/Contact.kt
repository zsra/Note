package hu.zsra.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var contactId : Long = 0L,
    @ColumnInfo(name = "created_at")
    var createdAt : Long = System.currentTimeMillis(),
    @ColumnInfo(name = "name")
    var contactName : String = "",
    @ColumnInfo(name = "email")
    var contactEmail : String = ""
)