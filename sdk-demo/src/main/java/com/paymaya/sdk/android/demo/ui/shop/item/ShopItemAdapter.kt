package com.paymaya.sdk.android.demo.ui.shop.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paymaya.sdk.android.demo.R
import com.paymaya.sdk.android.demo.model.ShopProduct
import com.paymaya.sdk.android.demo.ui.shop.OnAddToCartRequestListener
import kotlinx.android.synthetic.main.holder_shop_product.view.*

class ShopItemAdapter(
    private val onAddToCartRequestListener: OnAddToCartRequestListener
) : RecyclerView.Adapter<ShopItemAdapter.ItemViewHolder>() {

    private val items: MutableList<ShopProduct> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.holder_shop_product, parent, false)
        )

    override fun getItemCount(): Int =
        items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setData(items[position])
    }

    fun setItems(items: List<ShopProduct>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(product: ShopProduct) {
            itemView.product_name.text = product.name
            itemView.product_amount.text = product.amount?.value.toString() + " ${product.currency}"
            itemView.shop_product_container.setBackgroundResource(R.drawable.rectangle)
            itemView.add_to_cart_button.setOnClickListener {
                onAddToCartRequestListener.invoke(product)
            }
        }
    }
}