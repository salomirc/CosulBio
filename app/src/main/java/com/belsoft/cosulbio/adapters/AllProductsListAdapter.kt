package com.belsoft.cosulbio.adapters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.models.Product
import kotlinx.android.synthetic.main.all_products_item_cell.view.*


class AllProductsListAdapter(private var items: List<Product>) :
    RecyclerView.Adapter<AllProductsListAdapter.AllProductsListHolder>(){


    class AllProductsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View = itemView
        val productImageView: ImageView = itemView.productImageView
        val nameTextView: TextView = itemView.nameTextView
        val priceTextView: TextView = itemView.priceTextView
        val unitTextView: TextView = itemView.unitTextView
        val detailsTextView: TextView = itemView.detailsTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.all_products_item_cell,
            parent,
            false
        )
        return AllProductsListHolder(itemView)
    }


    override fun onBindViewHolder(holder: AllProductsListHolder, position: Int) {
        val current = items[position]
        if (current.bitmap != null) {
            holder.productImageView.setImageBitmap(current.bitmap)
        } else {
            holder.productImageView.setImageResource(R.drawable.ic_baseline_image_24)
        }
        holder.nameTextView.text = current.name
        holder.priceTextView.text = "${current.price}"
        holder.unitTextView.text = current.unit
        holder.detailsTextView.text = current.details ?: ""
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Product>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun notifyUpdate(position: Int) {
        notifyItemChanged(position)
    }

}