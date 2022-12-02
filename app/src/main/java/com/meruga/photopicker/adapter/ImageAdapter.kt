package com.meruga.photopicker.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meruga.photopicker.R
import kotlinx.android.synthetic.main.list_item.view.*

class ImageAdapter(private val context: Context, private val imageList: List<Uri>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = imageList[position]

        // TODO: Set Image to ImageView
        val imageView = holder.itemView.imageView
        Glide.with(context).load(image).into(imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) { }
}