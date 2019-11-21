package hu.zsra.note.screen.contactlist

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.ContactListFragmentBinding
import hu.zsra.note.screen.contact.ContactViewModelFactory

class ContactListFragment : Fragment() {

    @SuppressLint("WrongConstant")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContactListFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_list_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactViewModelFactory(dataSource, application)

        val contactListViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(ContactListViewModel::class.java)

        binding.contactListViewModelxml = contactListViewModel

        binding.lifecycleOwner = this

        binding.listOfContacts.layoutManager = LinearLayoutManager(application, LinearLayout.VERTICAL, false)
        var adapter = ContactListAdapter(contactListViewModel.contactsList)

        binding.listOfContacts.adapter = adapter

        return binding.root
    }

}
