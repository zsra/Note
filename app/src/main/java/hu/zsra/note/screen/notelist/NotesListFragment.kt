package hu.zsra.note.screen.notelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController

import hu.zsra.note.R
import hu.zsra.note.databinding.NotesListFragmentBinding

class NotesListFragment : Fragment() {

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

        return binding.root
    }

}
