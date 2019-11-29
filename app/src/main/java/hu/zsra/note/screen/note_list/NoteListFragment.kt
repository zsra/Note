package hu.zsra.note.screen.note_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.NoteListFragmentBinding

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
        binding.noteList.adapter = adapter

        noteListViewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        binding.contacts.setOnClickListener {
            view : View -> view.findNavController().navigate(NoteListFragmentDirections
            .actionNoteListFragmentToContactListFragment())
        }

        noteListViewModel.showSnackBarEvent.observe(this, Observer {
            if(it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Notes all deleted",
                    Snackbar.LENGTH_SHORT
                ).show()

                noteListViewModel.doneShowingSnackbar()
            }
        })

        noteListViewModel.navigateToNoteCreate.observe(this, Observer {
            note -> note?.let {
            this.findNavController().navigate(NoteListFragmentDirections
                .actionNoteListFragmentToNoteCreateFragment(note.noteId))

            noteListViewModel.doneNavigating()
        }
        })

        noteListViewModel.navigateToNoteDetails.observe(this, Observer {
                note -> note?.let {
            this.findNavController().navigate(NoteListFragmentDirections
                .actionNoteListFragmentToNoteDetailsFragment(note))
        }
        })

        val manager = GridLayoutManager(activity, 1)
        binding.noteList.layoutManager = manager

        return binding.root
    }


}
