package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.adapters.adapterCartProduit
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragement: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        val buttonUser = view.findViewById<ImageButton>(R.id.user)
        val search = view.findViewById<EditText>(R.id.searchEditText)
        val buttonTousproduits = view.findViewById<LinearLayout>(R.id.TousProduits)
        val category_btn = view.findViewById<TextView>(R.id.Lien_category)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)

        category_btn.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProduitsFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.category
        }

        buttonTousproduits.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProduitsFragement())
                .commit()
        }

        search.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, RechercheFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.search
        }

        buttonUser.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfilFragment())
                .commit()
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

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,true)


        val adapter = adapterCartProduit(listProduit) { produit ->
            Toast.makeText(requireContext(), "${produit.nomP} ajouté au panier !", Toast.LENGTH_SHORT).show()
        }
        recyclerView.adapter = adapter

    }
}