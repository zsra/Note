package hu.zsra.note.screen.note_create

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

class NoteCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: NoteCreateFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_create_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = this!!.arguments?.let { NoteCreateFragmentArgs.fromBundle(it) }
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NoteCreateViewModelFactory(dataSource, arguments!!.noteKey)

        val noteCreateViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(NoteCreateViewModel::class.java)

        binding.noteCreateViewModel = noteCreateViewModel

        binding.navigateToNoteList.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(NoteCreateFragmentDirections
                    .actionNoteCreateFragmentToNoteListFragment())

                noteCreateViewModel.doneNavigating()
            }
        })


        return binding.root
    }

}
