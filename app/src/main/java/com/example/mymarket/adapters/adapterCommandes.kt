package com.example.mymarket.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.Fragements.DetailsHisoriqueCommandes
import com.example.mymarket.Fragements.bottomLayoutFragement
import com.example.mymarket.R

class adapterCommandes(
    private val CommandesList: List<Commandes>,
    val fragement:FragmentManager
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

        holder.num.text = "Commande #${commande.Num}"
        holder.status.text ="Status :${commande.status}"
        holder.date.text = "${commande.dateCmd}"
        holder.prix.text = "Total :${commande.prixTotal.toString()} DH"
        holder.TotalCategory.text = "Total Category :${commande.TotalCategory.toString()}"
        holder.itemView.setOnClickListener{
            val fragmentDestination = DetailsHisoriqueCommandes()
            val bundle = Bundle()
            bundle.putInt("num",commande.Num)
            fragmentDestination.arguments = bundle
            fragmentDestination.show(fragement, fragmentDestination.tag)

        }

    }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}
