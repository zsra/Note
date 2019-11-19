package hu.zsra.note.screen.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.navigation.findNavController

import hu.zsra.note.R
import hu.zsra.note.databinding.TitleFragmentBinding

class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : TitleFragmentBinding = inflate(
            inflater, R.layout.title_fragment, container, false)

        binding.goToAddNewNote.setOnClickListener {
            view : View -> view.findNavController()
            .navigate(R.id.action_titleFragment_to_noteFragment)
        }

        binding.goToAddNewContact.setOnClickListener {
            view : View -> view.findNavController()
            .navigate(R.id.action_titleFragment_to_contactFragment)
        }

        binding.goToNoteList.setOnClickListener {
            view : View -> view.findNavController()
            .navigate(R.id.action_titleFragment_to_notesListFragment)
        }

        return binding.root
    }



}
