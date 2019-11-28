package hu.zsra.note.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import hu.zsra.note.model.Contact
import hu.zsra.note.model.Note

@Dao
interface NoteDatabaseDao {

    @Insert
    fun insert(note : Note)

    @Update
    fun update(note : Note)

    @Query("select * from note_table order by created_at desc")
    fun getAllNotes() : LiveData<List<Note>>

    @Query("select * from note_table where noteId = :id")
    fun getNoteById(id : Long) : Note

    @Query("delete from note_table")
    fun clear()

    @Query("select * from note_table where noteId = :id")
    fun getNoteByIdLiveData(id : Long) : LiveData<Note>

    @Query("Select * from note_table order by noteId desc limit 1")
    fun getNewNote() : Note?

    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("select * from contact_table order by created_at desc")
    fun getAllContacts() : LiveData<List<Contact>>

    @Query("select * from contact_table where contactId = :id")
    fun getContactById(id : Long) : Contact

    @Query("delete from contact_table")
    fun clearContact()

    @Query("select * from contact_table where contactId = :id")
    fun getContactByIdLiveData(id : Long) : LiveData<Contact>

    @Query("Select * from contact_table order by contactId desc limit 1")
    fun getNewContact() : Contact?

}