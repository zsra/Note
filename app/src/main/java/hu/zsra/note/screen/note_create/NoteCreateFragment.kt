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

import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.NoteCreateFragmentBinding
import kotlinx.android.synthetic.main.note_create_fragment.*
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import hu.zsra.note.R


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

        noteCreateViewModel.navigateToNoteList.observe(this, Observer {
            if(it == true) {
                noteCreateViewModel.noteTitle = note_title_crt.text.toString()
                noteCreateViewModel.noteText = note_body_crt.text.toString()
                this.findNavController().navigate(NoteCreateFragmentDirections
                    .actionNoteCreateFragmentToNoteListFragment())

                getActivity()?.getWindow()?.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                noteCreateViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}

