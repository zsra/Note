package hu.zsra.note.screen.contactlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import hu.zsra.note.database.NoteDatabaseDao

class ContactListViewModel(
    dataSource: NoteDatabaseDao,
    application: Application) : AndroidViewModel(application) {

}
