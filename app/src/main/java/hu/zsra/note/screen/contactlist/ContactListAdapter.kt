package hu.zsra.note.screen.contactlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import hu.zsra.note.R
import hu.zsra.note.model.Contact

class ContactListAdapter(val contacts: LiveData<List<Contact>>?)
    : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (contacts != null) {
            holder?.txtName?.text = contacts.value?.get(position)?.Name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.contact_item_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return contacts?.value?.size!!
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val txtName = itemView.findViewById<TextView>(R.id.txtName)
    }
}