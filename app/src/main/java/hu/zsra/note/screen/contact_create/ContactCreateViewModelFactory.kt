package hu.zsra.note.screen.contact_create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao

class ContactCreateViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val contactKey: Long) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactCreateViewModel::class.java)) {
            return ContactCreateViewModel(dataSource, contactKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}