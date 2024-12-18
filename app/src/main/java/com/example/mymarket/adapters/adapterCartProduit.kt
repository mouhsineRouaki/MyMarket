package com.example.mymarket.adapters

import android.os.Build
import com.example.mymarket.DATA.Produit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Notification
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService
import java.util.Locale


class adapterCartProduit(
    private var productList: List<Produit>,
) : RecyclerView.Adapter<adapterCartProduit.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        val produitDescription: TextView = itemView.findViewById(R.id.descriptionProduit)
        val addToCartButton: Button = itemView.findViewById(R.id.addToCartButton)
        val cart = itemView.findViewById<LinearLayout>(R.id.cartProduit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_produit_cart, parent, false)
        return ProductViewHolder(view)
    }
    class CarouselEffect : RecyclerView.ItemDecoration() {
        override fun onDrawOver(c: android.graphics.Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val centerX = parent.width / 2
            val scaleFactor = 0.8f
            for (i in 0 until parent.childCount) {
                val child = parent.getChildAt(i)
                val childCenterX = (child.left + child.right) / 2
                val scale = 1 - Math.abs(childCenterX - centerX) / centerX.toFloat() * (1 - scaleFactor)
                child.scaleX = scale
                child.scaleY = scale
            }
        }
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val produit = productList[position]
        val currentLocale = getCurrentAppLanguage(holder.itemView.context)
        if (currentLocale == "ar") {
            holder.cart.setBackgroundResource(R.drawable.linear_radius_arabic)
        }

        holder.productImage.setImageResource(produit.image)
        holder.productName.text = produit.nomP.toUpperCase()
        holder.productPrice.text = String.format("${holder.itemView.context.getString(R.string.prix)} : %.2f ${holder.itemView.context.getString(R.string.DH)}", produit.prix)
        holder.produitDescription.text = produit.description.toUpperCase()

        holder.addToCartButton.setOnClickListener {
            val p = PanierService.findAll().map { it.nomP }
            if(produit.quantite == 0){
                Toast.makeText(holder.itemView.context, "Stock insuffisant pour ${produit.nomP}", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!p.contains(produit.nomP)) {
                PanierService.create(produit)
                NotificationService.create(Notification(produit.image,"le ${produit.nomP} est ajouter dans votre Pannier"))
                Toast.makeText(it.context, "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(it.context, "${produit.nomP} Produit deja en panier", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
    fun getCurrentAppLanguage(context: android.content.Context): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.resources.configuration.locales[0].language
        } else {
            context.resources.configuration.locale.language
        }
    }
}
