package hu.zsra.note.screen.contact

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import hu.zsra.note.R
import hu.zsra.note.database.NoteDatabase
import hu.zsra.note.databinding.ContactFragmentBinding
import kotlinx.android.synthetic.main.contact_fragment.*

class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : ContactFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.contact_fragment, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = NoteDatabase.getInstance(application).noteDatabaseDao
        val viewModelFactory = ContactViewModelFactory(dataSource, application)

        val contactViewModel = ViewModelProviders.of(
            this, viewModelFactory).get(ContactViewModel::class.java)

        binding.contactViewModelxml = contactViewModel

        binding.lifecycleOwner = this

        contactViewModel.eventContactFinish.observe(this, Observer {
            HideKeyboard()
            contactSaved()
        })

        binding.backToTitleFromContact.setOnClickListener {
                view : View -> view.findNavController()
            .navigate(R.id.action_contactFragment_to_titleFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        ShowKeyboard()
    }

    fun ShowKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(inputName, 0)
    }

    fun HideKeyboard() {
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view!!.windowToken, 0)
    }

    private fun contactSaved() {
        Toast.makeText(activity, "Contact saved", Toast.LENGTH_SHORT).show()
    }

}
