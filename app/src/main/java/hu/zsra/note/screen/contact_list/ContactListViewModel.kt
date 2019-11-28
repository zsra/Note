package hu.zsra.note.screen.contact_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Contact
import kotlinx.coroutines.*

class ContactListViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application
) : ViewModel() {
    // TODO: Implement the ViewModel

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val NewContact = MutableLiveData<Contact?>()

    val Contacts = db.getAllContacts()

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    private val _navigateToContactCreate = MutableLiveData<Contact>()
    val navigateToContactCreate : LiveData<Contact>
        get() = _navigateToContactCreate

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    fun doneNavigating() {
        _navigateToContactCreate.value = null
    }

    private val _navigateToContactDetails = MutableLiveData<Long>()
    val navigateToContactDetails
        get() = _navigateToContactDetails

    fun onContactClicked(id: Long) {
        _navigateToContactDetails.value = id
    }

    fun onContactDetailsNavigated() {
        _navigateToContactDetails.value = null
    }

    init {
        initializeNewContact()
    }

    private fun initializeNewContact() {
        uiScope.launch {
            NewContact.value = getNewContactFromDatabase()
        }
    }

    private suspend fun getNewContactFromDatabase(): Contact? {
        return withContext(Dispatchers.IO) {
            db.getNewContact()
        }
    }

    private suspend fun insert(contact : Contact) {
        withContext(Dispatchers.IO) {
            db.insert(contact)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            db.clearContact()
        }
    }

    fun onNew() {
        uiScope.launch {
            val nContact = Contact()

            insert(nContact)

            NewContact.value = getNewContactFromDatabase()
            _navigateToContactCreate.value = NewContact.value
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            NewContact.value = null
            _showSnackbarEvent.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}