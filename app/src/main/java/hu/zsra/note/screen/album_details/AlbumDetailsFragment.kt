package hu.zsra.note.screen.album_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import hu.zsra.note.R

class AlbumDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = AlbumDetailsFragment()
    }

    private lateinit var viewModel: AlbumDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.album_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlbumDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
