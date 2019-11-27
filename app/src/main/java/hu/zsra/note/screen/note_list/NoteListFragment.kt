package hu.zsra.note.screen.note_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase

class NoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NoteListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_list_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NoteListViewModelFactory(dataSource, application)

        val noteListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NoteListViewModel::class.java)

        binding.noteListViewModel = noteListViewModel

        val adapter = NoteAdapter(NoteListener { noteId ->
            noteListViewModel.onNoteClicked(noteId)
        })
        binding.note_list.adapter = adapter

        return binding.root
    }


}
