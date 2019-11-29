package hu.zsra.note.screen.note_list

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.zsra.note.model.Note

@BindingAdapter("setNoteTitleText")
fun TextView.setNoteTitleText(item : Note?) {
    item?.let {
        text = item.noteTitle
    }
}

@BindingAdapter("setNoteBodyText")
fun TextView.setNoteBodyText(item : Note?) {
    item?.let {
        text = item.noteTitle
    }
}

