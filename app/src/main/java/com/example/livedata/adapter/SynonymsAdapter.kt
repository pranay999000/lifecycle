package com.example.livedata.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.livedata.databinding.ItemSynonymsBinding

class SynonymsAdapter(private val context: Context?, private val list: ArrayList<String>):
    RecyclerView.Adapter<SynonymsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSynonymsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemSynonymsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.synonyms.text = list[position]
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}