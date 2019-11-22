package hu.zsra.note.screen.note_create

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.*

class NoteCreateViewModel(
    dataSource: NoteDatabaseDao,
    private val noteKey: Long) : ViewModel() {

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

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

    fun onSetNote(title : String, text : String) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val newNote = db.getNoteById(noteKey)
                newNote!!.noteTitle = title
                newNote.noteText = text
                db.update(newNote)
            }

            _navigateToNoteList.value = true
        }
    }
}
