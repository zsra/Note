package hu.zsra.note.screen.notelist

import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.zsra.note.model.Note

@BindingAdapter("setNoteText")
fun TextView.setNoteTitleString(item : Note) {
    text = item.noteTitle
}