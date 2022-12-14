package com.mariuszmarondel.currenciesapp.screen.main.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mariuszmarondel.currenciesapp.databinding.ItemMainBinding


class Adapter(
    private var items: List<Item>
) : RecyclerView.Adapter<Adapter.ItemViewHolder>() {
    private lateinit var onBottomReachedCallback: () -> Unit
    private lateinit var onItemClickedCallback: (Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(from, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position], position, this.onItemClickedCallback)
        if (position == itemCount - 1) {
            this.onBottomReachedCallback.invoke()
        }
    }

    override fun getItemCount(): Int = items.size

    fun replaceItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setOnBottomReachedListener(callback: () -> Unit) {
        this.onBottomReachedCallback = callback
    }

    fun setOnItemClickedCallback(callback: (Int) -> Unit) {
        this.onItemClickedCallback = callback
    }

    class ItemViewHolder(
        private val binding: ItemMainBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item, position: Int, onClickCallback: (Int) -> Unit) {
            binding.itemMainText.text = item.text
            if (!item.isCurrencyItem) {
                binding.itemMainSeparator.visibility = View.VISIBLE
            } else {
                binding.itemMainSeparator.visibility = View.INVISIBLE
                binding.root.setOnClickListener {
                    onClickCallback.invoke(position)
                }
            }
        }
    }
}