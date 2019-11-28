package hu.zsra.note.screen.note_create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.*

class NoteCreateViewModel(
    dataSource: NoteDatabaseDao,
    private val noteKey: Long = 0L) : ViewModel() {

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    var noteTitle = ""
    var noteText = ""

    private val _navigateToNoteList = MutableLiveData<Boolean?>()
    val navigateToNoteList : LiveData<Boolean?>
        get() = _navigateToNoteList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    
    fun doneNavigating() {
        _navigateToNoteList.value = null
    }

    fun onSetNote() {
        uiScope.launch {
            _navigateToNoteList.value = true

            withContext(Dispatchers.IO) {
                val newNote = db.getNoteById(noteKey)
                Log.i("note", noteTitle)
                newNote.noteTitle = noteTitle
                newNote.noteText = noteText
                db.update(newNote)
            }


        }
    }
}
