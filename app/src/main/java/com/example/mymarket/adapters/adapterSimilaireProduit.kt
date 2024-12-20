package com.example.mymarket.adapters

import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.R


class adapterSimilaireProduit(
    private val productList: List<Produit>
) : RecyclerView.Adapter<adapterSimilaireProduit.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productDetails = itemView.findViewById<TextView>(R.id.product_details)
        val productPrice: TextView = itemView.findViewById(R.id.prix)
        val textViewOverly :TextView =itemView.findViewById(R.id.text_overlay)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produit_cart2, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]
        holder.textViewOverly.visibility= View.GONE
        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productDetails.text = produit.description
        holder.productPrice.text = "${produit.prix} DH"
        if (produit.quantite ==0){
            holder.textViewOverly.visibility= View.VISIBLE
            holder.textViewOverly.rotation = 45f
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
