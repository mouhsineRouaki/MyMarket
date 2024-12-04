package com.example.mymarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.findFirstRoot
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.R

class adapterCategory(
    private val CommandesList: List<Category>
) : RecyclerView.Adapter<adapterCategory.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.id_image)
        val texte=itemView.findViewById<TextView>(R.id.id_nomCategory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val categories = CommandesList[position]

        holder.texte.text = categories.nom
        holder.image.setImageResource(categories.image)

    }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}