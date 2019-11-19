package hu.zsra.note.screen.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Note
import kotlinx.coroutines.*

class NoteViewModel(dataSource: NoteDatabaseDao,
                    application: Application
                    ) : AndroidViewModel(application) {

    val database = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _eventNoteFinish = MutableLiveData<Boolean>()
    val eventNoteFinish: LiveData<Boolean>
        get() = _eventNoteFinish

    private suspend fun insert(note : Note) {
        withContext(Dispatchers.IO) {
            insert(note)
        }
    }

    public fun onSave() {
        uiScope.launch {
            val newNote = Note()
            newNote.noteTitle = R.id.noteTitle_input.toString()
            newNote.noteText = R.id.EditNoteText.toString()
            onNoteFinish()
            insert(newNote)
            onNoteFinishComplete()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun onNoteFinish() {
        _eventNoteFinish.value = true
    }

    fun onNoteFinishComplete() {
        _eventNoteFinish.value = false
    }
}
