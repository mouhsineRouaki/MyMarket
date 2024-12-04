package com.example.mymarket.adapters

import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.Fragements.DetailsFragment
import com.example.mymarket.R


class adapterCartProduit2(
    private val productList: List<Produit>,
    val childFragmentManager: FragmentManager
) : RecyclerView.Adapter<adapterCartProduit2.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productDetails: TextView = itemView.findViewById(R.id.product_details)
        val productPrice: TextView = itemView.findViewById(R.id.prix)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produit_cart2, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productDetails.text = produit.description
        holder.productPrice.text = "${produit.prix} DH"
        holder.itemView.setOnClickListener {
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailsFragment())
                .commit()
        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
