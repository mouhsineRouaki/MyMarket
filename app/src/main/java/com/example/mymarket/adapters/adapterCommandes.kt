package com.example.mymarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.R

class adapterCommandes(
    private val CommandesList: List<Commandes>
) : RecyclerView.Adapter<adapterCommandes.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val num: TextView = itemView.findViewById(R.id.commande_num)
        val status: TextView = itemView.findViewById(R.id.commande_status)
        val date = itemView.findViewById<TextView>(R.id.commande_date)
        val prix: TextView = itemView.findViewById(R.id.commande_prix_total)
        val TotalCategory: TextView = itemView.findViewById(R.id.commande_total_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_commandes, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val commande = CommandesList[position]

        holder.num.text = "Commande # ${commande.Num}"
        holder.status.text =commande.status
        holder.date.text = commande.dateCmd.date.toString()
        holder.prix.text = commande.prixTotal.toString()
        holder.TotalCategory.text = commande.TotalCategory.toString()

    }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}
