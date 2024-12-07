package com.example.mymarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.findFirstRoot
import androidx.core.content.ContextCompat
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
        val category = CommandesList[position]

        holder.texte.text = category.nom
        holder.image.setImageResource(category.image)
        val background = if (category.Select) {
            R.drawable.stroke_black
        } else {
            R.drawable.cercle
        }
        holder.image.setBackgroundResource(background)

        holder.itemView.setOnClickListener {
            category.Select = !category.Select
            notifyItemChanged(position)
        }


        }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}