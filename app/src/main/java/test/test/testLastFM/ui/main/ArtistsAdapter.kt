package test.test.testLastFM.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.test.testLastFM.data.entities.ArtistEntity
import test.test.testLastFM.databinding.ListItemArtistBinding


class ArtistsAdapter : ListAdapter<ArtistEntity, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArtistViewHolder(
            ListItemArtistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as ArtistViewHolder).bind(plant)
    }

    class ArtistViewHolder(
        private val binding: ListItemArtistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArtistEntity) {
            binding.apply {
                artist = item
                executePendingBindings()
            }
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<ArtistEntity>() {

    override fun areItemsTheSame(oldItem: ArtistEntity, newItem: ArtistEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ArtistEntity, newItem: ArtistEntity): Boolean {
        return oldItem == newItem
    }

}