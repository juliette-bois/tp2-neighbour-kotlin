package fr.juliettebois.dmii.tp03_juliette_bois.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import fr.juliettebois.dmii.tp03_juliette_bois.R
import fr.juliettebois.dmii.tp03_juliette_bois.databinding.NeighborItemBinding
import fr.juliettebois.dmii.tp03_juliette_bois.models.Neighbor

class ListNeighborsAdapter(
    items: List<Neighbor>,
    private val callback: ListNeighborHandler
) : RecyclerView.Adapter<ListNeighborsAdapter.ViewHolder>() {

    private val mNeighbours: List<Neighbor> = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: NeighborItemBinding = NeighborItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemViewHolder = holder as ViewHolder
        val neighbour: Neighbor = mNeighbours[position]
        itemViewHolder.binding.itemListName.text = neighbour.name

        // Display Neighbour Name
        holder.binding.itemListName.text = neighbour.name

        val context = holder.binding.root.context

        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .skipMemoryCache(false)
            .into(holder.binding.itemListAvatar)

        holder.binding.itemListDeleteButton.setOnClickListener {
            callback.onDeleteNeibor(neighbour)
        }
    }

    override fun getItemCount(): Int {
        return mNeighbours.size
    }

    class ViewHolder(var binding: NeighborItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
