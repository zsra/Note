package hu.zsra.note.screen.contact_details

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
import hu.zsra.note.databinding.ContactDetailsFragmentBinding

class ContactDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ContactDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_details_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val arguments = this!!.arguments?.let { ContactDetailsFragmentArgs.fromBundle(it) }
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactDetailsViewModelFactory(dataSource, arguments!!.contactKey)

        val contactDetailsViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContactDetailsViewModel::class.java)

        binding.contactDetailsViewModel = contactDetailsViewModel

        binding.setLifecycleOwner(this)

        contactDetailsViewModel.navigateToContactList.observe(this, Observer {
            if(it == true) {
                this.findNavController().navigate(ContactDetailsFragmentDirections
                    .actionContactDetailsFragmentToContactListFragment())

                contactDetailsViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
