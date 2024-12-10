package com.example.mymarket.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.DATA.ProduitPanier
import com.example.mymarket.Fragements.PanierFragment
import com.example.mymarket.R
import com.example.mymarket.Service.PanierService

class adapterPanier(
    private val productList: MutableList<Produit>,
    val fragment: PanierFragment,
    val quantite :Boolean,
    private val onAddToCartClick: (Produit) -> Unit
) : RecyclerView.Adapter<adapterPanier.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.image_produitP)
        val productName: TextView = itemView.findViewById(R.id.nom_produitP)
        val productPrice: TextView = itemView.findViewById(R.id.prix_original)
        val productReduit: TextView = itemView.findViewById(R.id.prix_reduit)
        val prixTotal: TextView = itemView.findViewById(R.id.total_produit)
        val btnIncrement: ImageView = itemView.findViewById(R.id.btn_increment)
        val btnDecrement: ImageView = itemView.findViewById(R.id.btn_decrement)
        val textQuantite: TextView = itemView.findViewById(R.id.text_quantite)
        val btnRemove: ImageButton = itemView.findViewById(R.id.btn_remove_item)
        val reduction: TextView = itemView.findViewById(R.id.label_reduction)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.linearLayout)
        val desc: TextView = itemView.findViewById(R.id.description_produit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.panier_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produit = productList[position]
        for (i in 0 until holder.linearLayout.childCount) {
            val child = holder.linearLayout.getChildAt(i)
             if(quantite){
                 child.visibility = View.VISIBLE
            }else{
                 child.visibility = View.GONE
            }
        }
        if(quantite){
            holder.btnRemove.visibility = View.VISIBLE
        }else{
            holder.btnRemove.visibility = View.GONE
            holder.desc.text = "Quantite : ${produit.quantitePanier}"
        }

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.uppercase()
        holder.productPrice.text = "Prix : ${produit.prix} DH"
        holder.textQuantite.text = "${produit.quantitePanier}"

        val prixReduit = produit.prix * (1 - produit.Promo / 100.0)
        holder.productReduit.text = String.format("%.2f DH", prixReduit)

        if (produit.Promo <= 0) {
            holder.reduction.visibility = View.GONE
            holder.productReduit.visibility = View.GONE
        } else {
            holder.reduction.visibility = View.VISIBLE
            holder.reduction.text = "-${produit.Promo}%"
            holder.productReduit.visibility = View.VISIBLE
        }

        holder.btnIncrement.setOnClickListener {
            if (produit.quantite > 0) {
                produit.quantitePanier = holder.textQuantite.text.toString().toInt()+ 1
                holder.textQuantite.text= produit.quantitePanier.toString()
                updateTotalPrix(holder, produit)
                fragment.updateTotal()
            } else {
                Toast.makeText(it.context, "Stock insuffisant pour ${produit.nomP}", Toast.LENGTH_SHORT).show()
            }
        }

        holder.btnDecrement.setOnClickListener {
            if (holder.textQuantite.text.toString().toInt() > 1) {
                produit.quantitePanier = holder.textQuantite.text.toString().toInt()- 1
                holder.textQuantite.text= produit.quantitePanier.toString()
                updateTotalPrix(holder, produit)
                fragment.updateTotal()

            } else {
                val dialog = AlertDialog.Builder(it.context)
                dialog.setMessage("Voulez-vous supprimer ${produit.nomP} du panier ?")
                dialog.setPositiveButton("OK") { _, _ ->
                    PanierService.deleteByPosition(position)
                    fragment.updateTotal()
                    notifyItemRemoved(position)
                }
                dialog.setNegativeButton("Annuler", null)
                dialog.create().show()
            }
        }

        holder.btnRemove.setOnClickListener {
            PanierService.deleteByPosition(position)
            notifyItemRemoved(position)
            notifyDataSetChanged()
            fragment.updateTotal()
        }
        updateTotalPrix(holder, produit)
    }

    fun updateTotalPrix(holder: ViewHolder, produit: Produit) {
        val prix = if (produit.Promo <= 0) {
            produit.prix
        } else {
            produit.prix * (1 - produit.Promo / 100.0)
        }
        val total = holder.textQuantite.text.toString().toInt() * prix
        holder.prixTotal.text = String.format("%.2f", total)
    }



    override fun getItemCount(): Int = productList.size
}
