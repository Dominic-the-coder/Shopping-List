package com.dominic.shoppinglist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dominic.shoppinglist.data.model.Shop
import com.dominic.shoppinglist.databinding.LayoutShopItemBinding

class ShopsAdapter(
    private var shops: List<Shop>,
    private val onPress: (Shop) -> Unit
): RecyclerView.Adapter<ShopsAdapter.ShopViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutShopItemBinding.inflate(inflater,parent, false)
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val shop = shops[position]


        holder.binding.run {
            tvName.text = shop.name
            tvNote.text = shop.notes
            cvShop.setOnClickListener {
                onPress(shop)
            }
        }
    }

    override fun getItemCount() = shops.size

    fun setShops(shops: List<Shop>) {
        this.shops = shops
        notifyDataSetChanged()
    }

    class ShopViewHolder(
        val binding: LayoutShopItemBinding
    ): RecyclerView.ViewHolder(binding.root)
}