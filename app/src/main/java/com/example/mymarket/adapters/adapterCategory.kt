package com.example.mymarket.adapters

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.ui.graphics.findFirstRoot
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.ProduitService

class adapterCategory(
    val CommandesList: MutableList<Category>,
    val onCategoryClick: (Category) -> Unit
) : RecyclerView.Adapter<adapterCategory.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image=itemView.findViewById<ImageView>(R.id.id_image)
        val texte=itemView.findViewById<TextView>(R.id.id_nomCategory)
        val la=itemView.findViewById<LinearLayout>(R.id.id_category)

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
        holder.image.apply {
            clipToOutline = true
            outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View, outline: Outline) {
                    outline.setOval(0, 0, view.width, view.height)
                }
            }
        }
        val background = if (category.Select) {
            R.drawable.gardient_category
        } else {
            R.drawable.cercle
        }
        holder.la.setBackgroundResource(background)

        holder.itemView.setOnClickListener {
            onCategoryClick(category)
            notifyItemChanged(position)
            notifyDataSetChanged()
            notifyItemRemoved(position)
        }


        }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}