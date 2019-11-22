package hu.zsra.note.screen.note_create

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao

class NoteCreateViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val noteKey: Long) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteCreateViewModel::class.java)) {
            return NoteCreateViewModel(dataSource, noteKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}