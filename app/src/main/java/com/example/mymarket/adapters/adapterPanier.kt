package com.example.mymarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R

class adapterPanier(
    private val productList: MutableList<Produit>,
    private val onAddToCartClick: (Produit) -> Unit
) : RecyclerView.Adapter<adapterPanier.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.image_produitP)
        val productName: TextView = itemView.findViewById(R.id.nom_produitP)
        val productPrice: TextView = itemView.findViewById(R.id.prix_original)
        val btnIncrement:ImageView = itemView.findViewById(R.id.btn_increment)
        val btnDecrement:ImageView = itemView.findViewById(R.id.btn_decrement)
        val text_quantite:TextView = itemView.findViewById(R.id.text_quantite)
        val btnRemove:ImageButton = itemView.findViewById(R.id.btn_remove_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.panier_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productPrice.text = "Prix : ${produit.prix} DH"

        holder.btnIncrement.setOnClickListener {
            val getQuantite:Int = holder.text_quantite.text.toString().toInt()
            holder.text_quantite.text = "${getQuantite + 1}"
        }
        holder.btnDecrement.setOnClickListener {
            val getQuantite:Int = holder.text_quantite.text.toString().toInt()
            if (getQuantite>1){
                holder.text_quantite.text = "${getQuantite - 1}"
            }else {
                holder.text_quantite.text = "1"
            }
        }
        holder.btnRemove.setOnClickListener {
            productList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, productList.size)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}