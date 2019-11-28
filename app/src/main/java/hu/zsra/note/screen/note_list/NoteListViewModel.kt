package hu.zsra.note.screen.note_list

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Note
import kotlinx.coroutines.*

class NoteListViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application) : ViewModel() {
    // TODO: Implement the ViewModel

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val NewNote = MutableLiveData<Note?>()

    val notes = db.getAllNotes()

    private var _showSnackbarEvent = MutableLiveData<Boolean?>()
    val showSnackBarEvent: LiveData<Boolean?>
        get() = _showSnackbarEvent

    private val _navigateToNoteCreate = MutableLiveData<Note>()
    val navigateToNoteCreate : LiveData<Note>
        get() = _navigateToNoteCreate

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = null
    }

    fun doneNavigating() {
        _navigateToNoteCreate.value = null
    }

    private val _navigateToNoteDetails = MutableLiveData<Long>()
    val navigateToNoteDetails
        get() = _navigateToNoteDetails

    fun onNoteClicked(id: Long) {
        _navigateToNoteDetails.value = id
    }

    fun onNoteDetailsNavigated() {
        _navigateToNoteDetails.value = null
    }

    init {
        initializeNewNote()
    }

    private fun initializeNewNote() {
        uiScope.launch {
            NewNote.value = getNewNoteFromDatabase()
        }
    }

    private suspend fun getNewNoteFromDatabase(): Note? {
        return withContext(Dispatchers.IO) {
            db.getNewNote()
        }
    }

    private suspend fun insert(note : Note) {
        withContext(Dispatchers.IO) {
            db.insert(note)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            db.clear()
        }
    }

    fun onNew() {
        uiScope.launch {
            val nnote = Note()

            insert(nnote)

            NewNote.value = getNewNoteFromDatabase()
            _navigateToNoteCreate.value = NewNote.value
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            NewNote.value = null
            _showSnackbarEvent.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
