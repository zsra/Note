package hu.zsra.note.screen.contact_create

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
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

                val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view!!.getWindowToken(), 0)

                contactCreateViewModel.doneNavigating()
            }
        })

        return binding.root
    }

}
