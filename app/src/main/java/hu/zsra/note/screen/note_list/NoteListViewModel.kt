package hu.zsra.note.screen.note_list

import android.app.Application
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NoteListViewModel(
    private val dataSource: NoteDatabaseDao,
    private val application: Application) : ViewModel() {
    // TODO: Implement the ViewModel

    val db = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
}
