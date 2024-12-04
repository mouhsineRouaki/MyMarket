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

        recyclerViewProduit.layoutManager = GridLayoutManager(requireContext(), 2)

        search.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RechercheFragement())
                .commit()
            val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigation?.selectedItemId = R.id.search
        }


        backButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigation?.selectedItemId = R.id.home
        }



        val listProduit= listOf(
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45),
            Produit("produit","hgdhed",45.0,"udgj",45)
        )
        val adapterPro = adapterCartProduit2(listProduit,parentFragmentManager)
        recyclerViewProduit.adapter = adapterPro
    }
}