package hu.zsra.note.screen.note_details

import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import hu.zsra.note.model.Note
import hu.zsra.note.utils.convertLongToDateString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NoteDetailsViewModel(
    dataSource: NoteDatabaseDao,
    private val noteKey: Long = 0L) : ViewModel() {

    val db = dataSource
    private var viewModelJob = Job()

    private val note: LiveData<Note>

    fun getNote() = note

    init {
        note = db.getNoteByIdLiveData(noteKey)
    }

    val createdAtString = Transformations.map(note) {
        note.value!!.createdAt.let { it1 -> convertLongToDateString(it1) }
    }

    private val _navigateToNoteList = MutableLiveData<Boolean?>()
    val navigateToNoteList: LiveData<Boolean?> get() = _navigateToNoteList

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigating() {
        _navigateToNoteList.value = null
    }

    fun onClose() {
        _navigateToNoteList.value = true
    }
}
