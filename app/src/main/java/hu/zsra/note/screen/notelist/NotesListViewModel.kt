package hu.zsra.note.screen.notelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import hu.zsra.note.database.NoteDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NotesListViewModel(
    dataSource: NoteDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    val database = dataSource
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val notesList = database.getAllNote()


}
