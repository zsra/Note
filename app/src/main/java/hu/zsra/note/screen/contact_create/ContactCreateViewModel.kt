package hu.zsra.note.screen.contact_create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.*

class ContactCreateViewModel(
    dataSource: NoteDatabaseDao,
    private val contactKey: Long = 0L) : ViewModel() {

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var contactName = ""
    var contactEmail = ""

    private val _navigateToContactList = MutableLiveData<Boolean?>()
    val navigateToContactList : LiveData<Boolean?>
        get() = _navigateToContactList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToContactList.value = null
    }

    fun onSetContact() {
        uiScope.launch {
            _navigateToContactList.value = true

            withContext(Dispatchers.IO) {
                val newContact = db.getContactById(contactKey)
                newContact.contactName = contactName
                newContact.contactEmail = contactEmail
                db.update(newContact)
            }


        }
    }
}
