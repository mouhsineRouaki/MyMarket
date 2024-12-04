package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.adapters.adapterCartProduit2
import com.example.mymarket.adapters.adapterRechercheCategory
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class RechercheFragement: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recherher_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerViewCategory: RecyclerView = view.findViewById(R.id.recycle_category)
        val recyclerViewProduit: RecyclerView = view.findViewById(R.id.recycle_produit_recherche)
        val backButton: ImageButton = view.findViewById(R.id.retour)


        backButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigation?.selectedItemId = R.id.home
        }

        recyclerViewCategory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
        recyclerViewProduit.layoutManager = GridLayoutManager(requireContext(), 2)


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
        val adapterCat = adapterRechercheCategory(listProduit)
        val adapterPro = adapterCartProduit2(listProduit,parentFragmentManager)

        recyclerViewCategory.adapter = adapterCat
        recyclerViewProduit.adapter = adapterPro
    }
}