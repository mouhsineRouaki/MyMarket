package com.example.mymarket.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R

class adapterRechercheCategory(
    private val productList: List<Produit>
) : RecyclerView.Adapter<adapterRechercheCategory.ProductViewHolder>() {

     var selectedPosition: Int = -1

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.nom_category)
        val underline: View = itemView.findViewById(R.id.underline_view)
        val layout:LinearLayout = itemView.findViewById(R.id.layout_cat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recherche, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val produit = productList[position]

        holder.category.text = produit.category.toUpperCase()

        if (position == selectedPosition) {
            holder.underline.visibility = View.VISIBLE
        } else {
            holder.underline.visibility = View.GONE
        }
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position

            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
