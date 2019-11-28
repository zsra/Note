package hu.zsra.note.screen.contact_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Contact
import kotlinx.coroutines.Job

class ContactDetailsViewModel(
    dataSource: NoteDatabaseDao,
    private val contactKey: Long = 0L) : ViewModel() {

    val db = dataSource
    private var viewModelJob = Job()

    private val contact: LiveData<Contact>

    fun getContact()  =contact

    init {
        contact = db.getContactByIdLiveData(contactKey)
    }

    private val _navigateToContactList = MutableLiveData<Boolean?>()
    val navigateToContactList: LiveData<Boolean?> get() = _navigateToContactList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToContactList.value = null
    }

    fun onClose() {
        _navigateToContactList.value = true
    }
}