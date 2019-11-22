package hu.zsra.note.screen.note_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao

class NoteDetailsViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val noteKey: Long) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailsViewModel::class.java)) {
            return NoteDetailsViewModel(dataSource, noteKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}