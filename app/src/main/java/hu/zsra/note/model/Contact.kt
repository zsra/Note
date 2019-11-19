package hu.zsra.note.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact (
    @PrimaryKey(autoGenerate = true)
    val contactId : Long = 0L,
    @ColumnInfo(name = "name")
    var Name : String = "",
    @ColumnInfo(name = "email")
    var Email : String = ""
)