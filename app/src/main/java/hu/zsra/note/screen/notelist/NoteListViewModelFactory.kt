package hu.zsra.note.screen.notelist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.screen.contact.ContactViewModel
import java.lang.IllegalArgumentException

class NoteListViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory  {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NotesListViewModel::class.java)) {
            return NotesListViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}