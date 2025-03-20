package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListDestinationAdapter(private val listDestinations: ArrayList<Destination>) :
    RecyclerView.Adapter<ListDestinationAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row_destination, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, location, description, photo) = listDestinations[position]
        Glide.with(holder.itemView.context).load(photo).into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvLocation.text = location
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listDestinations[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listDestinations.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvLocation: TextView = itemView.findViewById(R.id.tv_item_location)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Destination)
    }
}
