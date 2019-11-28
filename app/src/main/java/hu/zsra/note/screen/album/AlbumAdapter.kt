package hu.zsra.note.screen.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.zsra.note.model.Album

class AlbumAdapter(val clickListener: AlbumListener) : ListAdapter<Album, AlbumAdapter.ViewHolder>(AlbumDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: AlbumItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Album, clickListener: AlbumListener) {
            binding.album = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AlbumItemLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.albumId == newItem.albumId
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}

class AlbumListener(val clickListener: (albumId: Long) -> Unit) {
    fun onClick(album: Album) = clickListener(album.albumId)
}