package hu.zsra.note.screen.contact_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao

class ContactDetailsViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val contactKey: Long) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactDetailsViewModel::class.java)) {
            return ContactDetailsViewModel(dataSource, contactKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}