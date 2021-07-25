package com.example.newwallpaper.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newwallpaper.databinding.ItemPhotoBinding
import com.example.newwallpaper.models.Hit
import com.example.newwallpaper.models.PhotoData
import com.squareup.picasso.Picasso

class PhotoAdapter(var list: List<Hit>, var onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<PhotoAdapter.Vh>() {
    inner class Vh(var itemPhotoBinding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(itemPhotoBinding.root) {

        fun onBind(hit: Hit) {
            Picasso.get().load(hit.previewURL).into(itemPhotoBinding.img)
            itemPhotoBinding.img.setOnClickListener {
                onItemClickListener.onItemClick(hit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface OnItemClickListener{
        fun onItemClick(hit: Hit)
    }
}