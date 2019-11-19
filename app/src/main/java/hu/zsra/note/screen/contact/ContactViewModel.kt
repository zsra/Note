package hu.zsra.note.screen.contact

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Contact
import kotlinx.coroutines.*

class ContactViewModel(
    dataSource: NoteDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    val database = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _eventContactFinish = MutableLiveData<Boolean>()
    val eventContactFinish: LiveData<Boolean>
        get() = _eventContactFinish

    private suspend fun insert(contact : Contact) {
        withContext(Dispatchers.IO) {
            database.insert(contact)
        }
    }

    public fun onSave() {
        uiScope.launch {
            val newContact = Contact()
            newContact.Name = R.id.inputName.toString()
            newContact.Email = R.id.inputEmail.toString()
            onContactFinish()
            insert(newContact)
            Log.i("Data saved.", "ex: name = ${newContact.Name}")
            onContactFinishComplete()


        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onContactFinish() {
        _eventContactFinish.value = true
    }

    fun onContactFinishComplete() {
        _eventContactFinish.value = false
    }
}
