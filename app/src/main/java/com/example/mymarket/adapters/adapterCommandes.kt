package com.example.mymarket.adapters

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.DATA.Produit
import com.example.mymarket.Fragements.DetailsHisoriqueCommandes
import com.example.mymarket.R
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService

class adapterCommandes(
    private var CommandesList: List<Commandes>,
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
        holder.prix.text = String.format("Prix :%.2f DH", commande.prixTotal)
        holder.TotalCategory.text = "Total Category :${commande.TotalCategory}"
        startAutoRefresh(10000L)

        holder.itemView.setOnClickListener{
            val fragmentDestination = DetailsHisoriqueCommandes()
            val bundle = Bundle()
            bundle.putInt("num",commande.Num)
            fragmentDestination.arguments = bundle
            fragmentDestination.show(fragement, fragmentDestination.tag)

        }

    }
    fun startAutoRefresh(refreshInterval:Long) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                CommandesList=CommandesList
                notifyDataSetChanged()
                handler.postDelayed(this, refreshInterval)
            }
        }, refreshInterval)
    }

    override fun getItemCount(): Int {
        return CommandesList.size
    }
}
