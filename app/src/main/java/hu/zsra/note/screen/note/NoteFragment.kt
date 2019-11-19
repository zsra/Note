package hu.zsra.note.screen.note

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.NoteFragmentBinding
import hu.zsra.note.screen.contact.ContactViewModelFactory
import kotlinx.android.synthetic.main.contact_fragment.*

class NoteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : NoteFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.note_fragment, container, false)

        binding.goBackToTitleFromNote.setOnClickListener { view: View ->
            view.findNavController()
                .navigate(R.id.action_noteFragment_to_titleFragment)
        }

        binding.shareNote.setOnClickListener {
            view : View -> view.findNavController()
            .navigate(R.id.action_noteFragment_to_contactListFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactViewModelFactory(dataSource, application)

        val noteViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(NoteViewModel::class.java)

        binding.noteViewModelxml = noteViewModel

        noteViewModel.eventNoteFinish.observe(this, Observer {
            HideKeyboard()
            noteSaved()
        })

        return binding.root
    }


    override fun onStart() {
        super.onStart()
        ShowKeyboard()
    }

    fun ShowKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(inputName, 0)
    }

    fun HideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    private fun noteSaved() {
        Toast.makeText(activity, "Note saved", Toast.LENGTH_SHORT).show()
    }
}
