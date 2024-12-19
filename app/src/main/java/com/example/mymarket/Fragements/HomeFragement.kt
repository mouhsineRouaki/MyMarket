package com.example.mymarket.Fragements

import android.annotation.SuppressLint
import android.graphics.Outline
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.Service.VilleService
import com.example.mymarket.Service.utilisateurService
import com.example.mymarket.adapters.adapterCartProduit
import com.example.mymarket.adapters.adapterCartProduit2
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class HomeFragement: Fragment() {
     lateinit var recyclerView: RecyclerView
     lateinit var adapter: adapterCartProduit
     var handler: Handler? = null
     var runnable: Runnable? = null
     var currentPosition = 0
    lateinit var countNotification:TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_activity, container, false)
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        val recyclerPlusVente: RecyclerView = view.findViewById(R.id.recycle_plusVente)
        val buttonUser = view.findViewById<ImageButton>(R.id.user)
        val search = view.findViewById<EditText>(R.id.searchEditText)
        val buttonTousproduits = view.findViewById<CardView>(R.id.TousProduits)
        val category_btn = view.findViewById<TextView>(R.id.Lien_category)
        val nom = view.findViewById<TextView>(R.id.nom)
        countNotification = view.findViewById<TextView>(R.id.countNotification)
        val notification = view.findViewById<ImageButton>(R.id.notification)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val u = utilisateurService.getUser()
            nom.text = u.nom
            RefrechNotification()
            buttonUser.apply {
                setImageURI(u.image)
                clipToOutline = true
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        outline.setOval(0, 0, view.width, view.height)
                    }
                }
            }


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
                .replace(R.id.fragment_container, CategoryFragment())
                .commit()
            bottomNavigation?.selectedItemId = R.id.category
        }

        buttonUser.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfilFragment())
                .commit()
        }
        notification.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, NotificationFragment())
                .commit()
        }


        val listProduitPromotions=ProduitService.findAll().filter { it.Promo > 0 }.toMutableList()

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerPlusVente.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        adapter = adapterCartProduit(listProduitPromotions,parentFragmentManager)
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(adapterCartProduit.CarouselEffect())
        
        val adapter1 = adapterCartProduit2(ProduitService.findAll(),parentFragmentManager)
        recyclerPlusVente.adapter = adapter1
        startAutoScroll()

    }
    fun startAutoScroll() {
        handler = Handler(Looper.getMainLooper())
        runnable = object : Runnable {
            override fun run() {
                val itemCount = adapter.itemCount
                if (currentPosition == itemCount - 1) {

                    recyclerView.smoothScrollToPosition(0)
                    currentPosition = 1
                } else {
                    recyclerView.smoothScrollToPosition(currentPosition)
                    currentPosition++
                }

                handler?.postDelayed(this, 4000)
            }
        }
        handler?.postDelayed(runnable!!, 3000)
    }
    fun RefrechNotification() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                val count=NotificationService.findAll().count()
                countNotification.text = count.toString()
                handler.postDelayed(this, 1)
            }
        }, 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacks(runnable!!)
    }

}