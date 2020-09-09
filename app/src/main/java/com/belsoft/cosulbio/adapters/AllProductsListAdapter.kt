package com.belsoft.cosulbio.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.belsoft.cosulbio.R
import com.belsoft.cosulbio.models.Product
import kotlinx.android.synthetic.main.all_products_item_cell.view.*

class AllProductsListAdapter (private var items: List<Product>) :
    RecyclerView.Adapter<AllProductsListAdapter.AllProductsListHolder>(){


    class AllProductsListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val view: View = itemView
        val titleTextView: TextView = itemView.titleTextView
        val descriptionTextView: TextView = itemView.descriptionTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductsListHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.all_products_item_cell, parent, false)
        return AllProductsListHolder(itemView)
    }


    override fun onBindViewHolder(holder: AllProductsListHolder, position: Int) {
        val current = items[position]
        holder.titleTextView.text = current.name
        holder.descriptionTextView.text = current.details
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun setItems(items: List<Product>) {
        this.items = items
        notifyDataSetChanged()
    }
}