package com.example.mymarket.adapters

import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.text.toUpperCase
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.R


class adapterCartProduit(
    private val productList: List<Produit>,
    private val onAddToCartClick: (Produit) -> Unit
) : RecyclerView.Adapter<adapterCartProduit.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produit_cart, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productPrice.text = "Prix : ${produit.prix} DH"

        holder.addToCartButton.setOnClickListener {
            onAddToCartClick(produit)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
