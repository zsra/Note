package hu.zsra.note.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.zsra.note.model.Note

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note : Note)

    @Query("select * from note_table order by created_at desc")
    fun getAllNotes() : LiveData<List<Note>>?

    @Query("select * from note_table where noteId = :id")
    fun getNoteById(id : Long) : Note?

    @Query("delete from note_table where noteId = :id")
    fun deleteNoteById(id : Long)
}