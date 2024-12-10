package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterSimilaireProduit

class DetailsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.similar_produit)
        val imageDetails = view.findViewById<ImageView>(R.id.imageDetails)
        val nomDetails = view.findViewById<TextView>(R.id.nomDetails)
        val prixDetails = view.findViewById<TextView>(R.id.prixDetails)
        val quantiteDetails = view.findViewById<TextView>(R.id.quantiteDetails)
        val addToCartButton = view.findViewById<Button>(R.id.add_to_cart_button)
        var category :String?=""
        val bundle = arguments
        if (bundle != null) {
            val nomP = bundle.getString("nomP")
            category = bundle.getString("cat")
            val description = bundle.getString("desc")
            val prix = bundle.getDouble("prix")
            val quantite = bundle.getInt("quantite")
            val image = bundle.getInt("img")

            nomDetails.text = nomP
            prixDetails.text = "Prix :$prix dh"
            quantiteDetails.text = "Quantité : $quantite"
            imageDetails.setImageResource(image)
        }


            val listProduit = ProduitService.findAll().filter { it.category == category }

            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


            val adapter = adapterSimilaireProduit(listProduit)
            recyclerView.adapter = adapter

        }
}