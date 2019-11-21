package hu.zsra.note.screen.note_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hu.zsra.note.R

class NoteCreateFragment : Fragment() {

    companion object {
        fun newInstance() = NoteCreateFragment()
    }

    private lateinit var viewModel: NoteCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.note_create_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoteCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
