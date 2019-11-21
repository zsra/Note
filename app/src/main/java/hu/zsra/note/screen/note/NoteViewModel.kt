package hu.zsra.note.screen.note

import android.app.Application
import android.provider.SyncStateContract.Helpers.update
import android.util.Log
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

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val database = dataSource


    private val _eventNoteFinish = MutableLiveData<Boolean>()
    val eventNoteFinish: LiveData<Boolean>
        get() = _eventNoteFinish


    private var newNote = MutableLiveData<Note?>()

    private suspend fun insert(note : Note) {
        withContext(Dispatchers.IO) {
            database.insert(note)
        }
    }

    private suspend fun update(note : Note) {
        withContext(Dispatchers.IO) {
            database.update(note)
        }
    }

    init {
        initNewNote()
    }

    private fun initNewNote() {
        uiScope.launch {
            newNote.value = getNewNoteFromDatabase()
        }
    }

    private suspend fun getNewNoteFromDatabase() : Note? {
        return withContext(Dispatchers.IO) {
            var note = database.getNewNote()
            note
        }
    }

    fun onCreateNewNote() {
        uiScope.launch {
            val newNoteOnCreate = Note()
            insert(newNoteOnCreate)
            newNote.value = getNewNoteFromDatabase()
        }
    }

    public fun onSave() {
        uiScope.launch {
            val newNote = Note()
            newNote.noteTitle = R.id.noteTitle_input.toString()
            Log.i("Title\t", newNote.noteTitle)
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
