package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterSimilaireProduit
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val retour = view.findViewById<ImageView>(R.id.retour)
        retour.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.home
        }
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
            val produit= Produit(image,nomP.toString(),description.toString(),prix,category.toString(),quantite)
            addToCartButton.setOnClickListener {
                val p = PanierService.findAll().map { it.nomP }
                if(!p.contains(nomP)) {
                    PanierService.create(produit)
                    Toast.makeText(it.context, "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(it.context, "${produit.nomP} Produit deja en panier", Toast.LENGTH_SHORT).show()
                }
            }
        }


            val listProduit = ProduitService.findAll().filter { it.category == category }

            recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


            val adapter = adapterSimilaireProduit(listProduit)
            recyclerView.adapter = adapter

        }
}