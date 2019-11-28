package hu.zsra.note.screen.note_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.NoteDetailsFragmentBinding

class NoteDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NoteDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_details_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = this!!.arguments?.let { NoteDetailsFragmentArgs.fromBundle(it) }
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NoteDetailsViewModelFactory(dataSource, arguments!!.noteKey)

        val noteDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NoteDetailsViewModel::class.java)

        binding.noteDetailsViewModel = noteDetailsViewModel

        binding.setLifecycleOwner(this)

        noteDetailsViewModel.navigateToNoteList.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(NoteDetailsFragmentDirections
                    .actionNoteDetailsFragmentToNoteListFragment())

                noteDetailsViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
