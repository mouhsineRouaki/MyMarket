package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.VilleService
import com.example.mymarket.Service.villeTypeService

class WelcomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_comptes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnLogin = view.findViewById<TextView>(R.id.tologin)
        val btnInscription = view.findViewById<TextView>(R.id.tosignup)

        VilleService.create(ville(villeType.Safi, 10_000))
        VilleService.create(ville(villeType.CasaBlanca, 20_000))
        VilleService.create(ville(villeType.Agadir, 60_000))
        VilleService.create(ville(villeType.Tanger, 100_000))
        VilleService.create(ville(villeType.Marrakech, 150_000))
        VilleService.create(ville(villeType.Rabat, 200_000))
        VilleService.create(ville(villeType.Fes, 170_000))
        VilleService.create(ville(villeType.Meknes, 130_000))
        VilleService.create(ville(villeType.Oujda, 80_000))
        VilleService.create(ville(villeType.Nador, 70_000))
        VilleService.create(ville(villeType.Tetouan, 90_000))
        VilleService.create(ville(villeType.Kenitra, 95_000))
        VilleService.create(ville(villeType.Laayoune, 50_000))
        VilleService.create(ville(villeType.Essaouira, 40_000))
        VilleService.create(ville(villeType.ElJadida, 55_000))
        VilleService.create(ville(villeType.Mohammedia, 85_000))
        VilleService.create(ville(villeType.Settat, 45_000))
        VilleService.create(ville(villeType.Taza, 35_000))
        VilleService.create(ville(villeType.Khouribga, 30_000))
        VilleService.create(ville(villeType.BeniMellal, 60_000))
        VilleService.create(ville(villeType.Guelmim, 25_000))
        VilleService.create(ville(villeType.Dakhla, 20_000))
        VilleService.create(ville(villeType.Zagora, 15_000))
        VilleService.create(ville(villeType.KsarElKebir, 22_000))


        villeTypeService.create(villeType.Safi)
        villeTypeService.create(villeType.CasaBlanca)
        villeTypeService.create(villeType.Agadir)
        villeTypeService.create(villeType.Tanger)

        btnLogin.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_animations,
                    R.anim.aucun,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, loginFragment())
                .commit()
        }

        btnInscription.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_animations,
                    R.anim.aucun,
                    R.anim.pop_enter_animations,
                    R.anim.pop_sortie_animations
                )
                .replace(R.id.fragmentContainer, Inscriptionfragment())
                .commit()
        }
    }
}
