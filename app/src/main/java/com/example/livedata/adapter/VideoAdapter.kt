package com.example.livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.livedata.databinding.ItemVideoBinding
import com.example.livedata.model.server.Vid

class VideoAdapter(private var context: Context?, private var list: ArrayList<Vid>):
    RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemVideoBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            with(list[position]) {
                Glide.with(context!!).load(thumbnail).into(binding.video)
                binding.videoTitle.text = text
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}