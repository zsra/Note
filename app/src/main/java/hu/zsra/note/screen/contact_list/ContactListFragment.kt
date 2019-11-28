package hu.zsra.note.screen.contact_list

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.ContactListFragmentBinding
import hu.zsra.note.databinding.NoteListFragmentBinding

class ContactListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: ContactListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_list_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactListViewModelFactory(dataSource, application)

        val contactListViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContactListViewModel::class.java)

        binding.contactListViewModel = contactListViewModel

        binding.back.setOnClickListener {
            view : View -> view.findNavController().navigate(ContactListFragmentDirections
            .actionContactListFragmentToNoteListFragment())
        }

        val adapter = ContactAdapter(ContactListener { contactId ->
            contactListViewModel.onContactClicked(contactId)
        })
        binding.contactList.adapter = adapter

        contactListViewModel.Contacts!!.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.setLifecycleOwner(this)

        contactListViewModel.showSnackBarEvent.observe(this, Observer {
            if(it == true) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    "Notes all deleted",
                    Snackbar.LENGTH_SHORT
                ).show()

                contactListViewModel.doneShowingSnackbar()
            }
        })

        contactListViewModel.navigateToContactCreate.observe(this, Observer {
                contact -> contact?.let {
            this.findNavController().navigate(ContactListFragmentDirections
                .actionContactListFragmentToContactCreateFragment(contact.contactId))

            contactListViewModel.doneNavigating()
        }
        })

        contactListViewModel.navigateToContactDetails.observe(this, Observer {
                contact -> contact?.let {
            this.findNavController().navigate(ContactListFragmentDirections
                .actionContactListFragmentToContactDetailsFragment(contact))
        }
        })

        val manager = GridLayoutManager(activity, 1)
        binding.contactList.layoutManager = manager

        return binding.root
    }


}
