package com.example.ulavto.adapter

import android.R.attr.data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ulavto.R
import com.example.ulavto.databinding.PartsItemBinding


class PartsAdapter(val listener: Listener): RecyclerView.Adapter<PartsAdapter.PartsHolder>(){
    val itemPartsList = ArrayList<ItemParts>()
    class PartsHolder(item: View):RecyclerView.ViewHolder(item){
        val binding = PartsItemBinding.bind(item)
        fun bind(itemParts: ItemParts, listener: Listener) = with(binding){
            im.setImageResource(itemParts.avatarUrl)
            tvTitle.text = itemParts.nameN
            if (itemParts.favorite == 0){
                imFav.setImageResource(R.drawable.favorite_button)
            }
            else{
                imFav.setImageResource(R.drawable.favorite_button_act)
            }
            itemView.setOnClickListener {
                listener.onClick(itemParts)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parts_item, parent, false)
        return PartsHolder(view)
    }

    override fun getItemCount(): Int {
        return itemPartsList.size
    }

    override fun onBindViewHolder(holder: PartsHolder, position: Int) {
        holder.bind(itemPartsList[position], listener)
    }

    fun addParts(itemParts: ItemParts){
        itemPartsList.add(itemParts)
        notifyDataSetChanged()
    }
    fun clear() {
        val size: Int = itemPartsList.size
        itemPartsList.clear()
        notifyItemRangeRemoved(0, size)
    }

    interface Listener{
        fun onClick(itemParts: ItemParts)
    }
}