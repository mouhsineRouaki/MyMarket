package com.example.mymarket.adapters

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
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
        val itemid : LinearLayout = itemView.findViewById(R.id.itemcmd)
        val num: TextView = itemView.findViewById(R.id.commande_num)
        val status: TextView = itemView.findViewById(R.id.commande_status)
        val date = itemView.findViewById<TextView>(R.id.commande_date)
        val prix: TextView = itemView.findViewById(R.id.commande_prix_total)
        val TotalCategory: TextView = itemView.findViewById(R.id.commande_total_category)
        val iconCommande = itemView.findViewById<ImageView>(R.id.iconCommande)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_commandes, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val commande = CommandesList[position]

        holder.num.text = "N°${commande.Num}"
        holder.status.text ="${commande.status}"
        holder.date.text = "${commande.dateCmd}"
        holder.prix.text = String.format("%.2f %s", commande.prixTotal,holder.itemView.context.getString(R.string.DH))
        holder.TotalCategory.text = "articles(${commande.TotalCategory})"
        startAutoRefresh(10000L)
        if(commande.status == "En cours"){
            holder.iconCommande.setImageResource(R.drawable.en_cours)
            holder.itemid.setBackgroundResource(R.drawable.item_command_encours)
            holder.status.setTextColor(Color.parseColor("#7A6A44"))

        }else if(commande.status == "En attente"){
            holder.iconCommande.setImageResource(R.drawable.en_attente)
            holder.itemid.setBackgroundResource(R.drawable.item_commande_enattent)
            holder.status.setTextColor(Color.parseColor("#7A4F33"))
        }else{
            holder.iconCommande.setImageResource(R.drawable.accepte)
            holder.itemid.setBackgroundResource(R.drawable.item_commandes_bg)
            holder.status.setTextColor(Color.parseColor("#389975"))
        }

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
