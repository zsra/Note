package hu.zsra.note.screen.notelist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.NotesListFragmentBinding

class NotesListFragment : Fragment() {

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : NotesListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.notes_list_fragment, container, false)

        binding.goBackToTitleFromNoteList.setOnClickListener {
            view : View -> view.findNavController()
            .navigate(R.id.action_notesListFragment_to_titleFragment)
        }

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = NoteListViewModelFactory(dataSource, application)

        val notesListViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(NotesListViewModel::class.java)

        binding.noteListViewModelxml = notesListViewModel

        binding.lifecycleOwner = this

        val adapter = NoteListAdapter()
        binding.listOfNotes.adapter = adapter

        notesListViewModel.notesList?.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        val manager = GridLayoutManager(activity, 1)
        binding.listOfNotes.layoutManager = manager


        return binding.root
    }

}
