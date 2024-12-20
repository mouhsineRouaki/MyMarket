package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterCartProduit2
import com.example.mymarket.adapters.adapterRechercheCategory
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProduitsFragement: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tous_produits_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewProduit: RecyclerView = view.findViewById(R.id.recycle_tous_produits)
        val backButton: ImageButton = view.findViewById(R.id.retour)
        val search = view.findViewById<EditText>(R.id.searchEditText)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        recyclerViewProduit.layoutManager = GridLayoutManager(requireContext(), 2)

        search.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CategoryFragment())
                .commit()
        }


        backButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.home
        }




        val adapterPro = adapterCartProduit2(ProduitService.findAll(),parentFragmentManager)
        recyclerViewProduit.adapter = adapterPro
    }
}