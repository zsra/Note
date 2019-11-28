package hu.zsra.note.screen.album

import android.app.Application
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao

class AlbumViewModel(private val dataSource: NoteDatabaseDao,
                     private val application: Application
) : ViewModel() {


}
