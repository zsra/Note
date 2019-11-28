package hu.zsra.note.screen.album_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.zsra.note.database.NoteDatabaseDao

class AlbumDetailsViewModelFactory(
    private val dataSource: NoteDatabaseDao,
    private val albumKey: Long) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumDetailsViewModel::class.java)) {
            return AlbumDetailsViewModel(dataSource, albumKey) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}