package hu.zsra.note.screen.contact_create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.ContactCreateFragmentBinding
import kotlinx.android.synthetic.main.contact_create_fragment.*

class ContactCreateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ContactCreateFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_create_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = this!!.arguments?.let { ContactCreateFragmentArgs.fromBundle(it) }
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactCreateViewModelFactory(dataSource, arguments!!.contactKey)

        val contactCreateViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContactCreateViewModel::class.java)

        binding.contactCreateViewModel = contactCreateViewModel

        contactCreateViewModel.navigateToContactList.observe(this, Observer {
            if(it == true) {
                contactCreateViewModel.contactName = contact_name_crt.text.toString()
                contactCreateViewModel.contactEmail = contact_email_crt.text.toString()

                this.findNavController().navigate(ContactCreateFragmentDirections
                    .actionContactCreateFragmentToContactListFragment())

                getActivity()?.getWindow()?.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                contactCreateViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
