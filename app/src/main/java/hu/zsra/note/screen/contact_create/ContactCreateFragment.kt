package hu.zsra.note.screen.contact_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hu.zsra.note.R

class ContactCreateFragment : Fragment() {

    companion object {
        fun newInstance() = ContactCreateFragment()
    }

    private lateinit var viewModel: ContactCreateViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.contact_create_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ContactCreateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
