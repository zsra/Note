package hu.zsra.note.screen.contact_list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.zsra.note.model.Contact

@BindingAdapter("setContactNameText")
fun TextView.setContactNameText(item : Contact?) {
    item?.let {
        text = item.contactName
    }
}

@BindingAdapter("setContactEmailText")
fun TextView.setContactEmailText(item : Contact?) {
    item?.let {
        text = item.contactEmail
    }
}