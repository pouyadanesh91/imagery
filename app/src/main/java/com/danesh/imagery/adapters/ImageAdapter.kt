package com.danesh.imagery.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danesh.imagery.R
import com.danesh.imagery.models.ImageHitModel
import kotlinx.android.synthetic.main.image_thumb_list_item.view.*
import java.util.*

class ImageAdapter(val images: ArrayList<ImageHitModel>) :
    RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        var clickListener: ClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_thumb_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        val image = images[position]
        holder.itemView.txt_imageViews.text = image.views.toString()

        Glide.with(holder.itemView.context)
            .load(image.previewURL)
            .override(image.previewWidth,image.previewHeight)
            .fitCenter()
            .into(holder.itemView.img_mainImage)

        holder.itemView.itemHolder.setOnClickListener {

            clickListener!!.onItemClick(position, holder.itemView, image._id)


        }
    }

    override fun getItemCount(): Int {
        return images.size
    }

    interface ClickListener {
        fun onItemClick(position: Int, v: View, id: Int)
    }

}