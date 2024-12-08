package com.example.mymarket.adapters

import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.Fragements.PanierFragment
import com.example.mymarket.R
import com.example.mymarket.Service.PanierService


class adapterCartProduit(
    private var productList: List<Produit>,
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
    class CarouselEffect : RecyclerView.ItemDecoration() {
        override fun onDrawOver(c: android.graphics.Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val centerX = parent.width / 2
            val scaleFactor = 0.8f
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                val childCenterX = (child.left + child.right) / 2
                val scale = 1 - Math.abs(childCenterX - centerX) / centerX.toFloat() * (1 - scaleFactor)
                child.scaleX = scale
                child.scaleY = scale
            }
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productPrice.text = "Prix : ${produit.prix} DH"

        holder.addToCartButton.setOnClickListener {
            object{
                var panier = PanierService.create(produit)
            }
            Toast.makeText(it.context, "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
