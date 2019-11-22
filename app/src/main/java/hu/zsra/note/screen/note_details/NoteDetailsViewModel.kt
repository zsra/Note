package hu.zsra.note.screen.note_details

import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NoteDetailsViewModel(
    dataSource: NoteDatabaseDao,
    private val noteKey: Long) : ViewModel() {
    // TODO: Implement the ViewModel

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
}
