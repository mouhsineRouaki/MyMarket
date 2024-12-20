package com.example.mymarket.adapters

import android.os.Bundle
import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Notification
import com.example.mymarket.Fragements.DetailsFragment
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService


class adapterCartProduit2(
    private val productList: List<Produit>,
    val childFragmentManager: FragmentManager
) : RecyclerView.Adapter<adapterCartProduit2.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productDetails: TextView = itemView.findViewById(R.id.product_details)
        val productPrice: TextView = itemView.findViewById(R.id.prix)
        val add: ImageButton = itemView.findViewById(R.id.add)
        val textViewOverly :TextView =itemView.findViewById(R.id.text_overlay)
        val promotext : TextView = itemView.findViewById(R.id.promoText2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produit_cart2, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]

        holder.textViewOverly.visibility= View.GONE
        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productDetails.text = produit.description
        holder.productPrice.text = "${produit.prix} ${holder.itemView.context.getString(R.string.DH)}"
        holder.promotext.text = "-${produit.Promo}%"
        holder.promotext.visibility = View.VISIBLE
        if (produit.quantite ==0){
            holder.textViewOverly.visibility= View.VISIBLE
            holder.textViewOverly.rotation = 45f
        }
        if(produit.Promo == 0){
            holder.promotext.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("nomP", produit.nomP)
            bundle.putString("cat", produit.category)
            bundle.putString("desc", produit.description)
            bundle.putDouble("prix", produit.prix)
            bundle.putInt("quantite", produit.quantite)
            bundle.putInt("img", produit.image)
            bundle.putInt("Promo", produit.Promo)

            val fragment = DetailsFragment()
            fragment.arguments = bundle
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }
        holder.add.setOnClickListener {
            val p = PanierService.findAll().map { it.nomP }
                if(produit.quantite == 0){
                    Toast.makeText(holder.itemView.context, "${holder.itemView.context.getString(R.string.StockInsifut)} ${produit.nomP}", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(!p.contains(produit.nomP)) {
                    PanierService.create(produit)
                    NotificationService.create(Notification(produit.image,"le ${produit.nomP} ${holder.itemView.context.getString(R.string.added_to_cart)}"))
                    Toast.makeText(it.context, "${produit.nomP} ${holder.itemView.context.getString(R.string.ajouterAuPanier)}", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(it.context, "${produit.nomP} ${holder.itemView.context.getString(R.string.product_already_in_cart)}", Toast.LENGTH_SHORT).show()
                }

        }

    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
