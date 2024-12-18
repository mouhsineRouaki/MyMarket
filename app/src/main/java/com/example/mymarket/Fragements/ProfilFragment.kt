package com.example.mymarket.Fragements

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Outline
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.Notification
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.NotificationService
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.Service.utilisateurService
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profil_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnFrancais = view.findViewById<TextView>(R.id.btnFrancais)
        val btnArabic = view.findViewById<TextView>(R.id.btnArabic)
        val btn_back = view.findViewById<ImageButton>(R.id.back_button)
        val bottomNavigation = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val imageProfil = view.findViewById<ImageView>(R.id.profile_picture)
        val NomComplet = view.findViewById<TextView>(R.id.user_name)
        val mesInfo = view.findViewById<LinearLayout>(R.id.mesInfo)
        val aide = view.findViewById<LinearLayout>(R.id.aide)
        val partager = view.findViewById<LinearLayout>(R.id.partager)
        val logout = view.findViewById<LinearLayout>(R.id.LougOut)
        aide.setOnClickListener {
            val phoneNumber = "0614291145"
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse(phoneNumber)
            }
            startActivity(intent)
        }
        partager.setOnClickListener {
            val supportNumber = "123456789"
            val message = "Contactez notre support au numéro : $supportNumber"

            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, message)
                putExtra(Intent.EXTRA_SUBJECT, "Numéro de Support")
            }
            startActivity(Intent.createChooser(intent, "Partager le support via :"))
        }
        logout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Confirmation de Loug Out")
            builder.setMessage("do you want to Loug Out")
            builder.setPositiveButton("OK") { dialog, which ->

            }
            builder.setNegativeButton("Annuler",null)
            builder.show()
        }
        mesInfo.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, InformationFragment())
                .commit()
        }
        utilisateurService.findAll().forEach { u ->
            imageProfil.setImageURI(u.image)
            imageProfil.apply {
                clipToOutline = true
                outlineProvider = object : ViewOutlineProvider() {
                    override fun getOutline(view: View, outline: Outline) {
                        outline.setOval(0, 0, view.width, view.height)
                    }
                }
            }
            NomComplet.text = "${u.nom} ${u.prenom}"
        }
        btn_back.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragement())
                .commit()
            bottomNavigation?.selectedItemId = R.id.home
        }

        fun changeLanguage(locale: Locale) {
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            resources.updateConfiguration(config, resources.displayMetrics)
            requireActivity().recreate()
        }



        btnFrancais.setOnClickListener {
            btnFrancais.setBackgroundResource(R.drawable.toggle_selected)
            btnArabic.setBackgroundResource(R.drawable.toggle_unselected)
            changeLanguage(Locale("fr"))
            ProduitService.Clear()
            ProduitService.create(Produit(R.drawable.pomme_fuji,"Pommes Fuji", "Pommes sucrées et croquantes, parfaites pour les collations.", 4.8, "Fruits", 2))
            ProduitService.create(Produit(R.drawable.bananes,10,"Bananes", "Bananes mûres et sucrées, riches en potassium.", 4.7, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.oranges,"Oranges", "Oranges juteuses et sucrées, idéales pour un jus frais.", 4.9, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.raisins,"Raisins", "Raisins frais, parfaits pour une collation rapide.", 4.6, "Fruits", 45))
            ProduitService.create(Produit(R.drawable.poires,10,"Poires", "Poires juteuses et sucrées, parfaites en dessert.", 4.7, "Fruits", 55))
            ProduitService.create(Produit(R.drawable.fraises,"Fraises", "Fraises fraîches et sucrées, parfaites pour les salades de fruits.", 4.8, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.kiwi,"Kiwi", "Kiwi acidulé, riche en vitamine C.", 4.5, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.ananas,"Ananas", "Ananas frais, délicieux et sucré.", 4.7, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.mangue,"Mangue", "Mangue juteuse et sucrée, parfaite en smoothie.", 4.9, "Fruits", 45))
            ProduitService.create(Produit(R.drawable.citrons,"Citrons", "Citrons frais et acides, parfaits pour les boissons.", 4.6, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.prunes,"Prunes", "Prunes juteuses et sucrées, parfaites pour un dessert.", 4.4, "Fruits", 30))
            ProduitService.create(Produit(R.drawable.peches,"Pêches", "Pêches sucrées et juteuses, idéales pour les desserts.", 4.8, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.ceries,"Cerises", "Cerises fraîches et sucrées, parfaites en salade.", 4.7, "Fruits", 35))
            ProduitService.create(Produit(R.drawable.melon,"Melon", "Melon sucré et rafraîchissant, parfait pour l'été.", 4.9, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.papaye,"Papaye", "Papaye sucrée et juteuse, idéale en salade de fruits.", 4.5, "Fruits", 55))
            ProduitService.create(Produit(R.drawable.froimboise,"Framboises", "Framboises fraîches, parfaites pour les desserts ou smoothies.", 4.6, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.myrtiles,"Myrtilles", "Myrtilles fraîches, idéales pour les céréales ou desserts.", 4.7, "Fruits", 45))
            ProduitService.create(Produit(R.drawable.groseilles, "Groseilles", "Groseilles acides, idéales pour les confitures.", 4.3, "Fruits", 25))
            ProduitService.create(Produit(R.drawable.nectarines, "Nectarines", "Nectarines sucrées et juteuses, parfaites pour les tartes.", 4.8, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.carottes_bio, "Carottes bio", "Carottes fraîches et croquantes, cultivées sans produits chimiques.", 4.5, "Légumes", 60))
            ProduitService.create(Produit(R.drawable.brocoli, "Brocoli", "Brocoli vert et riche en vitamines, parfait pour les plats sautés.", 4.6, "Légumes", 30))
            ProduitService.create(Produit(R.drawable.epinards, "Épinards", "Épinards frais, riches en fer et en vitamines.", 4.7, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.courgettes, "Courgettes", "Courgettes fraîches, idéales pour les ratatouilles.", 4.6, "Légumes", 40))
            ProduitService.create(Produit(R.drawable.poivrons, "Poivrons", "Poivrons colorés, idéals pour les salades ou les plats sautés.", 4.8, "Légumes", 45))
            ProduitService.create(Produit(R.drawable.pommes_de_terre, "Pommes de terre", "Pommes de terre fraîches, idéales pour tous les types de plats.", 4.5, "Légumes", 60))
            ProduitService.create(Produit(R.drawable.tomates, "Tomates", "Tomates fraîches et juteuses, parfaites pour les salades.", 4.7, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.cho_fleur,10, "Chou-fleur", "Chou-fleur frais, parfait pour les gratins.", 4.6, "Légumes", 55))
            ProduitService.create(Produit(R.drawable.haricots_verts, "Haricots verts", "Haricots verts frais, parfaits pour un plat d'accompagnement.", 4.5, "Légumes", 45))
            ProduitService.create(Produit(R.drawable.petits_pois, "Petits pois", "Petits pois frais, délicieux dans les soupes ou salades.", 4.6, "Légumes", 35))
            ProduitService.create(Produit(R.drawable.aubergines, "Aubergines", "Aubergines fraîches, idéales pour les plats méditerranéens.", 4.4, "Légumes", 40))
            ProduitService.create(Produit(R.drawable.oignons, "Oignons", "Oignons frais et savoureux, parfaits pour aromatiser vos plats.", 4.5, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.laitue, "Laitue", "Laitue fraîche, idéale pour les salades.", 4.7, "Légumes", 40))
            ProduitService.create(Produit(R.drawable.chou, "Chou", "Chou vert frais, parfait pour les soupes ou les salades.", 4.6, "Légumes", 45))
            ProduitService.create(Produit(R.drawable.celeri, "Céleri", "Céleri frais, idéal pour les soupes ou les salades.", 4.5, "Légumes", 35))
            ProduitService.create(Produit(R.drawable.fenouil, "Fenouil", "Fenouil frais, avec une saveur douce et anisée.", 4.6, "Légumes", 25))
            ProduitService.create(Produit(R.drawable.radis, "Radis", "Radis croquants et légèrement piquants, parfaits en salade.", 4.7, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.poireaux, "Poireaux", "Poireaux frais, parfaits pour les soupes ou les plats mijotés.", 4.8, "Légumes", 45))
            ProduitService.create(Produit(R.drawable.betteraves, "Betteraves", "Betteraves fraîches, idéales pour les salades ou jus.", 4.5, "Légumes", 40))
            ProduitService.create(Produit(R.drawable.saumon_frais, "Saumon frais", "Filets de saumon frais, riches en oméga-3.", 4.9, "Poissons", 25))
            ProduitService.create(Produit(R.drawable.thon_en_conserve, "Thon en conserve", "Thon sauvage en conserve, idéal pour les salades.", 4.3, "Poissons", 40))
            ProduitService.create(Produit(R.drawable.maquereau, "Maquereau", "Maquereau frais, idéal pour les grillades.", 4.6, "Poissons", 30))
            ProduitService.create(Produit(R.drawable.sardines, "Sardines", "Sardines en conserve, parfaites pour une recette rapide.", 4.4, "Poissons", 35))
            ProduitService.create(Produit(R.drawable.truite, "Truite", "Truite fraîche, idéale pour une cuisson en papillote.", 4.7, "Poissons", 50))
            ProduitService.create(Produit(R.drawable.sole, "Sole", "Sole fraîche, délicate et parfaite pour les plats raffinés.", 4.8, "Poissons", 45))
            ProduitService.create(Produit(R.drawable.fletan, "Flétan", "Flétan frais, parfait pour les grillades.", 4.9, "Poissons", 40))
            ProduitService.create(Produit(R.drawable.bar, "Bar", "Bar frais, idéal pour un dîner élégant.", 4.8, "Poissons", 55))
            ProduitService.create(Produit(R.drawable.rouget, "Rouget", "Rouget frais, délicieux grillé ou en bouillabaisse.", 4.7, "Poissons", 45))
            ProduitService.create(Produit(R.drawable.colin, "Colin", "Colin frais, idéal pour une cuisson rapide.", 4.6, "Poissons", 50))
            ProduitService.create(Produit(R.drawable.homard, "Homard", "Homard frais, parfait pour un plat de luxe.", 4.9, "Poissons", 25))
            ProduitService.create(Produit(R.drawable.crevettes, "Crevettes", "Crevettes fraîches, idéales pour les cocktails ou les plats asiatiques.", 4.8, "Poissons", 60))
            ProduitService.create(Produit(R.drawable.palourdes, "Palourdes", "Palourdes fraîches, parfaites pour une soupe de fruits de mer.", 4.7, "Poissons", 50))
            ProduitService.create(Produit(R.drawable.moules,"Moules", "Moules fraîches, délicieuses avec une sauce au vin blanc.", 4.5, "Poissons", 55))
            ProduitService.create(Produit(R.drawable.poulet_entier, "Poulet entier", "Poulet fermier entier, parfait pour un rôti ou un poulet au four.", 4.8, "Viandes", 40))
            ProduitService.create( Produit(R.drawable.steak_de_boeuf, "Steak de bœuf", "Steak de bœuf tendre, idéal pour les grillades.", 4.9, "Viandes", 50))
            ProduitService.create( Produit(R.drawable.cotelettes_dagneau, "Côtelettes d'agneau", "Côtelettes d'agneau savoureuses, idéales pour un barbecue.", 4.7, "Viandes", 45))
            ProduitService.create( Produit(R.drawable.filet_de_porc, "Filet de porc", "Filet de porc maigre, parfait pour les plats rôtis.", 4.6, "Viandes", 40))
            ProduitService.create( Produit(R.drawable.escalopes_de_dinde, "Escalopes de dinde", "Escalopes de dinde tendres et légères, parfaites pour les sautés.", 4.5, "Viandes", 50))
            ProduitService.create( Produit(R.drawable.saucisses_de_toulouse, "Saucisses de Toulouse", "Saucisses de Toulouse traditionnelles, idéales pour un cassoulet.", 4.6, "Viandes", 60))
            ProduitService.create( Produit(R.drawable.bacon_fume,"Bacon fumé", "Bacon fumé, parfait pour le petit déjeuner ou les salades.", 4.7, "Viandes", 55))
            ProduitService.create( Produit(R.drawable.roti_de_boeuf, "Rôti de bœuf", "Rôti de bœuf tendre, idéal pour les repas de famille.", 4.8, "Viandes", 45))
            ProduitService.create( Produit(R.drawable.jarret_de_porc,30, "Jarret de porc", "Jarret de porc, parfait pour un plat mijoté ou une choucroute.", 4.6, "Viandes", 50))
            ProduitService.create( Produit(R.drawable.poulet_roti, "Poulet rôti", "Poulet rôti prêt à déguster, idéal pour les repas rapides.", 4.7, "Viandes", 60))
            ProduitService.create( Produit(R.drawable.cotelettes_de_porc, "Côtelettes de porc", "Côtelettes de porc savoureuses, idéales pour les grillades.", 4.5, "Viandes", 45))
            ProduitService.create( Produit(R.drawable.lait_entier,10, "Lait entier", "Lait entier crémeux, parfait pour les petits déjeuners.", 4.8, "Laitiers", 60))
            ProduitService.create( Produit(R.drawable.fromage_rape, "Fromage râpé", "Fromage râpé mélangé, idéal pour les gratins ou les pizzas.", 4.7, "Laitiers", 45))
            ProduitService.create( Produit(R.drawable.yaourt_nature, "Yaourt nature", "Yaourt nature crémeux et frais, parfait pour le dessert.", 4.6, "Laitiers", 50))
            ProduitService.create( Produit(R.drawable.beurre_doux, "Beurre doux", "Beurre doux frais, idéal pour la pâtisserie ou les tartines.", 4.9, "Laitiers", 40))
            ProduitService.create( Produit(R.drawable.creme_fraiche, "Crème fraîche", "Crème fraîche épaisse, idéale pour les sauces ou les desserts.", 4.8, "Laitiers", 45))
            ProduitService.create( Produit(R.drawable.fromage_blanc, "Fromage blanc", "Fromage blanc onctueux, parfait pour un dessert léger.", 4.7, "Laitiers", 55))
            ProduitService.create( Produit(R.drawable.lait_de_chevre, "Lait de chèvre", "Lait de chèvre frais, riche en goût et en nutriments.", 4.6, "Laitiers", 50))
            ProduitService.create( Produit(R.drawable.fromage_de_chevre,10, "Fromage de chèvre", "Fromage de chèvre crémeux, parfait pour les salades.", 4.8, "Laitiers", 40))
            ProduitService.create( Produit(R.drawable.lait_de_soja, "Lait de soja", "Lait de soja nature, idéal pour les personnes intolérantes au lactose.", 4.5, "Laitiers", 60))
            ProduitService.create( Produit(R.drawable.fromage_comte,10, "Fromage Comté", "Fromage Comté affiné, riche et savoureux.", 4.9, "Laitiers", 50))
            ProduitService.create( Produit(R.drawable.croissants, "Croissants", "Croissants au beurre frais, parfaits pour le petit-déjeuner.", 4.9, "Pâtisseries", 50))
            ProduitService.create( Produit(R.drawable.eclairs_au_chocolat, "Éclairs au chocolat", "Éclairs au chocolat fondants, parfaits pour les gourmands.", 4.8, "Pâtisseries", 45))
            ProduitService.create( Produit(R.drawable.tarte_aux_pommes, "Tarte aux pommes", "Tarte aux pommes maison, délicieuse et sucrée.", 4.7, "Pâtisseries", 40))
            ProduitService.create( Produit(R.drawable.madeleines, "Madeleines", "Madeleines fraîches, légères et parfumées.", 4.6, "Pâtisseries", 50))
            ProduitService.create( Produit(R.drawable.chocolatines, "Chocolatines", "Chocolatines au chocolat fondant, idéales pour le goûter.", 4.9, "Pâtisseries", 60))
            ProduitService.create( Produit(R.drawable.macarons, "Macarons", "Macarons délicats, au goût raffiné et léger.", 4.8, "Pâtisseries", 45))
            ProduitService.create( Produit(R.drawable.tarte_au_citron, "Tarte au citron", "Tarte au citron acidulée et sucrée, un délice frais.", 4.7, "Pâtisseries", 40))
            ProduitService.create( Produit(R.drawable.brioche, "Brioche", "Brioche douce et moelleuse, idéale pour le petit déjeuner.", 4.6, "Pâtisseries", 50))
            ProduitService.create( Produit(R.drawable.pain_au_chocolat, "Pain au chocolat", "Pain au chocolat délicieux, parfait pour une pause gourmande.", 4.9, "Pâtisseries", 55))
            ProduitService.create( Produit(R.drawable.muffins, "Muffins", "Muffins aux fruits frais, parfaits pour un goûter rapide.", 4.8, "Pâtisseries", 50))
            ProduitService.create( Produit(R.drawable.cafe_moulu, "Café moulu", "Café moulu riche et aromatique, idéal pour le petit déjeuner.", 4.9, "Boissons", 60))
            ProduitService.create( Produit(R.drawable.jus_dorange, "Jus d'orange", "Jus d'orange frais, riche en vitamine C.", 4.8, "Boissons", 50))
            ProduitService.create( Produit(R.drawable.the_vert, "Thé vert", "Thé vert bio, parfait pour un moment de détente.", 4.7, "Boissons", 55))
            ProduitService.create( Produit(R.drawable.eau_petillante, "Eau pétillante", "Eau pétillante rafraîchissante, idéale pour accompagner vos repas.", 4.6, "Boissons", 60))
            ProduitService.create( Produit(R.drawable.jus_de_pomme, "Jus de pomme", "Jus de pomme frais, légèrement sucré et rafraîchissant.", 4.8, "Boissons", 50))
            ProduitService.create( Produit(R.drawable.limonade, "Limonade", "Limonade artisanale, sucrée et bien citronnée.", 4.7, "Boissons", 55))
            ProduitService.create( Produit(R.drawable.soda_cola, "Soda cola", "Soda cola classique, une boisson rafraîchissante et pétillante.", 4.5, "Boissons", 65))
            ProduitService.create( Produit(R.drawable.eau_plate, "Eau plate", "Eau minérale plate, idéale pour hydrater.", 4.6, "Boissons", 70))
            ProduitService.create( Produit(R.drawable.vin_rouge, "Vin rouge", "Vin rouge sec, parfait pour accompagner les repas.", 4.9, "Boissons", 40))
            ProduitService.create( Produit(R.drawable.vin_blanc, "Vin blanc", "Vin blanc sec, léger et fruité.", 4.8, "Boissons", 50))
            ProduitService.create( Produit(R.drawable.curcuma, "Curcuma", "Curcuma en poudre, idéal pour donner de la couleur et du goût à vos plats.", 4.8, "Épices", 50))
            ProduitService.create( Produit(R.drawable.poivre_noir, "Poivre noir", "Poivre noir en grains, pour un goût piquant et frais.", 4.9, "Épices", 60))
            ProduitService.create( Produit(R.drawable.cumin, "Cumin", "Cumin en poudre, pour ajouter une saveur chaude et épicée à vos plats.", 4.7, "Épices", 55))
            ProduitService.create( Produit(R.drawable.paprika, "Paprika", "Paprika doux, parfait pour les plats mijotés et les viandes grillées.", 4.8, "Épices", 50))
            ProduitService.create( Produit(R.drawable.curry, "Curry", "Curry en poudre, un mélange d'épices savoureux pour vos recettes exotiques.", 4.7, "Épices", 60))
            ProduitService.create( Produit(R.drawable.thym, "Thym", "Thym séché, idéal pour parfumer les viandes et les légumes.", 4.6, "Épices", 45))
            ProduitService.create( Produit(R.drawable.cannelle, "Cannelle", "Cannelle en poudre, pour une touche sucrée et épicée dans vos desserts.", 4.8, "Épices", 50))
            ProduitService.create( Produit(R.drawable.gingembre, "Gingembre", "Gingembre en poudre, pour ajouter du piquant et de la chaleur à vos plats.", 4.7, "Épices", 55))
            ProduitService.create( Produit(R.drawable.safran, "Safran", "Safran, épice précieuse pour une couleur et un parfum uniques.", 4.9, "Épices", 40))
            ProduitService.create( Produit(R.drawable.piment_de_cayenne, "Piment de Cayenne", "Piment de Cayenne en poudre, pour les amateurs de plats épicés.", 4.6, "Épices", 50))
            ProduitService.create( Produit(R.drawable.flocons_davoine, "Flocons d'avoine", "Flocons d'avoine, parfaits pour un petit déjeuner énergétique.", 4.9, "Céréales", 60))
            ProduitService.create( Produit(R.drawable.muesli, "Muesli", "Muesli aux fruits et graines, idéal pour une alimentation équilibrée.", 4.8, "Céréales", 50))
            ProduitService.create( Produit(R.drawable.cornflakes, "Cornflakes", "Cornflakes croustillants, parfaits pour un petit déjeuner rapide.", 4.7, "Céréales", 55))
            ProduitService.create( Produit(R.drawable.riz_blanc, "Riz blanc", "Riz blanc basmati, idéal pour accompagner vos repas.", 4.6, "Céréales", 60))
            ProduitService.create( Produit(R.drawable.quinoa, "Quinoa", "Quinoa bio, riche en protéines et idéal pour les plats végétariens.", 4.8, "Céréales", 50))
            ProduitService.create( Produit(R.drawable.pates_completes, "Pâtes complètes", "Pâtes complètes, pour un repas sain et nourrissant.", 4.7, "Céréales", 60))
            ProduitService.create( Produit(R.drawable.semoule, "Semoule", "Semoule fine, idéale pour les couscous et les plats mijotés.", 4.6, "Céréales", 55))
            ProduitService.create( Produit(R.drawable.pates_italiennes, "Pâtes italiennes", "Pâtes italiennes traditionnelles, parfaites pour les sauces.", 4.9, "Céréales", 50))
            ProduitService.create( Produit(R.drawable.couscous, "Couscous", "Couscous, un classique de la cuisine méditerranéenne.", 4.7, "Céréales", 60))
            ProduitService.create( Produit(R.drawable.riz_basmati, "Riz basmati", "Riz basmati parfumé, parfait pour accompagner vos plats orientaux.", 4.8, "Céréales", 55))
            ProduitService.create( Produit(R.drawable.pates_a_la_farine_de_ble_complet, "Farine de blé", "Farine de blé tout usage, idéale pour la pâtisserie et la cuisine.", 4.9, "Céréales", 60))
            ProduitService.create( Produit(R.drawable.flocons_davoine, "Avoine instantanée", "Avoine instantanée, parfaite pour un petit déjeuner rapide et nourrissant.", 4.8, "Céréales", 50))
        }

        btnArabic.setOnClickListener {
            btnArabic.setBackgroundResource(R.drawable.toggle_selected)
            btnFrancais.setBackgroundResource(R.drawable.toggle_unselected)
            changeLanguage(Locale("ar"))
            ProduitService.Clear()
            ProduitService.create(Produit(R.drawable.pomme_fuji, "تفاح فوجي", "تفاح حلو ومقرمش، مثالي للوجبات الخفيفة.", 4.8, "فواكه", 2))
            ProduitService.create(Produit(R.drawable.bananes, 10, "موز", "موز ناضج وحلو، غني بالبوتاسيوم.", 4.7, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.oranges, "برتقال", "برتقال عصاري وحلو، مثالي للعصير الطازج.", 4.9, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.raisins, "عنب", "عنب طازج، مثالي للوجبات السريعة.", 4.6, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.poires, 10, "كمثرى", "كمثرى عصارية وحلوة، مثالية للتحليات.", 4.7, "فواكه", 55))
            ProduitService.create(Produit(R.drawable.fraises, "فراولة", "فراولة طازجة وحلوة، مثالية لسلطات الفواكه.", 4.8, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.kiwi, "كيوي", "كيوي حامضي، غني بفيتامين C.", 4.5, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.ananas, "أناناس", "أناناس طازج، لذيذ وحلو.", 4.7, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.mangue, "مانجو", "مانجو عصاري وحلو، مثالي للعصير.", 4.9, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.citrons, "ليمون", "ليمون طازج وحامض، مثالي للمشروبات.", 4.6, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.prunes, "برقوق", "برقوق عصاري وحلو، مثالي للتحليات.", 4.4, "فواكه", 30))
            ProduitService.create(Produit(R.drawable.peches, "خوخ", "خوخ حلو وعصاري، مثالي للتحليات.", 4.8, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.ceries, "كرز", "كرز طازج وحلو، مثالي للسلطات.", 4.7, "فواكه", 35))
            ProduitService.create(Produit(R.drawable.melon, "بطيخ", "بطيخ حلو ومنعش، مثالي لفصل الصيف.", 4.9, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.papaye, "بابايا", "بابايا حلوة وعصارية، مثالية لسلطات الفواكه.", 4.5, "فواكه", 55))
            ProduitService.create(Produit(R.drawable.froimboise, "توت العليق", "توت العليق طازج، مثالي للتحليات أو العصائر.", 4.6, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.myrtiles, "توت بري", "توت بري طازج، مثالي للحبوب أو التحليات.", 4.7, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.groseilles, "تواريخ", "تواريخ حامضة، مثالية للمربى.", 4.3, "فواكه", 25))
            ProduitService.create(Produit(R.drawable.nectarines, "نكتارين", "نكتارين حلو وعصاري، مثالي للفطائر.", 4.8, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.carottes_bio, "جزر عضوي", "جزر طازج ومقرمش، مزروع بدون مواد كيميائية.", 4.5, "خضروات", 60))
            ProduitService.create(Produit(R.drawable.brocoli, "بروكلي", "بروكلي أخضر وغني بالفيتامينات، مثالي للأطباق المقلية.", 4.6, "خضروات", 30))
            ProduitService.create(Produit(R.drawable.epinards, "سبانخ", "سبانخ طازجة، غنية بالحديد والفيتامينات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.courgettes, "كوسة", "كوسة طازجة، مثالية للطبخ.", 4.6, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.poivrons, "فلفل", "فلفل ملون، مثالي للسلطات أو الأطباق المقلية.", 4.8, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.pommes_de_terre, "بطاطا", "بطاطا طازجة، مثالية لجميع أنواع الأطباق.", 4.5, "خضروات", 60))
            ProduitService.create(Produit(R.drawable.tomates, "طماطم", "طماطم طازجة وعصارية، مثالية للسلطات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.cho_fleur, 10, "قرنبيط", "قرنبيط طازج، مثالي للطواجن.", 4.6, "خضروات", 55))
            ProduitService.create(Produit(R.drawable.haricots_verts, "فاصوليا خضراء", "فاصوليا خضراء طازجة، مثالية للطهي.", 4.5, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.petits_pois, "بازلاء", "بازلاء طازجة، لذيذة في الحساء أو السلطات.", 4.6, "خضروات", 35))
            ProduitService.create(Produit(R.drawable.aubergines, "باذنجان", "باذنجان طازج، مثالي للأطباق المتوسطية.", 4.4, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.oignons, "بصل", "بصل طازج ولذيذ، مثالي لتحسين طعم الأطباق.", 4.5, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.laitue, "خس", "خس طازج، مثالي للسلطات.", 4.7, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.chou, "كرنب", "كرنب أخضر طازج، مثالي للحساء أو السلطات.", 4.6, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.celeri, "كرفس", "كرفس طازج، مثالي للحساء أو السلطات.", 4.5, "خضروات", 35))
            ProduitService.create(Produit(R.drawable.fenouil, "شمر", "شمر طازج، له طعم لطيف ونكهة أنيسية.", 4.6, "خضروات", 25))
            ProduitService.create(Produit(R.drawable.radis, "فجل", "فجل مقرمش وحار قليلًا، مثالي للسلطات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.poireaux, "كراث", "كراث طازج، مثالي للحساء أو الأطباق المطهوة.", 4.8, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.betteraves, "شمندر", "شمندر طازج، مثالي للسلطات أو العصير.", 4.5, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.saumon_frais, "سلمون طازج", "شرائح سلمون طازجة، غنية بأوميغا 3.", 4.9, "أسماك", 25))
            ProduitService.create(Produit(R.drawable.thon_en_conserve, "تونة معلبة", "تونة برية معلبة، مثالية للسلطات.", 4.3, "أسماك", 40))
            ProduitService.create(Produit(R.drawable.maquereau, "ماكريل", "ماكريل طازج، مثالي للشواء.", 4.6, "أسماك", 30))
            ProduitService.create(Produit(R.drawable.sardines, "سردين", "سردين معلب، مثالي للوصفات السريعة.", 4.4, "أسماك", 35))
            ProduitService.create(Produit(R.drawable.truite, "سلمون قوس قزح", "سلمون قوس قزح طازج، مثالي للطهي في ورق الألمنيوم.", 4.7, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.sole, "سول", "سول طازج، لذيذ ومثالي للأطباق الراقية.", 4.8, "أسماك", 45))
            ProduitService.create(Produit(R.drawable.fletan, "هلبوت", "هلبوت طازج، مثالي للشواء.", 4.9, "أسماك", 40))
            ProduitService.create(Produit(R.drawable.bar, "بار", "بار طازج، مثالي لعشاء أنيق.", 4.8, "أسماك", 55))
            ProduitService.create(Produit(R.drawable.rouget, "روجيه", "روجيه طازج، لذيذ مشوي أو في حساء البحر.", 4.7, "أسماك", 45))
            ProduitService.create(Produit(R.drawable.colin, "كولين", "كولين طازج، مثالي للطهي السريع.", 4.6, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.homard, "كركند", "كركند طازج، مثالي لوجبة فاخرة.", 4.9, "أسماك", 25))
            ProduitService.create(Produit(R.drawable.crevettes, "جمبري", "جمبري طازج، مثالي للكوكتيلات أو الأطباق الآسيوية.", 4.8, "أسماك", 60))
            ProduitService.create(Produit(R.drawable.palourdes, "محار", "محار طازج، مثالي لحساء المأكولات البحرية.", 4.7, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.moules, "بلح البحر", "بلح البحر الطازج، لذيذ مع صلصة النبيذ الأبيض.", 4.5, "أسماك", 55))
            ProduitService.create(Produit(R.drawable.poulet_entier, "دجاج كامل", "دجاج كامل من المزارع، مثالي للتحميص أو الدجاج المشوي.", 4.8, "لحوم", 40))
            ProduitService.create(Produit(R.drawable.steak_de_boeuf, "شريحة لحم بقر", "شريحة لحم بقر طرية، مثالية للشواء.", 4.9, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.cotelettes_dagneau, "أضلاع لحم الضأن", "أضلاع لحم الضأن لذيذة، مثالية للشواء.", 4.7, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.filet_de_porc, "فيليه لحم الخنزير", "فيليه لحم الخنزير قليل الدهن، مثالي للأطباق المشوية.", 4.6, "لحوم", 40))
            ProduitService.create(Produit(R.drawable.escalopes_de_dinde, "شرائح ديك رومي", "شرائح ديك رومي طرية وخفيفة، مثالية للطهي السريع.", 4.5, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.saucisses_de_toulouse, "سجق تولوز", "سجق تولوز التقليدي، مثالي للكاسوليه.", 4.6, "لحوم", 60))
            ProduitService.create(Produit(R.drawable.bacon_fume, "بيكون مدخن", "بيكون مدخن، مثالي للإفطار أو السلطات.", 4.7, "لحوم", 55))
            ProduitService.create(Produit(R.drawable.roti_de_boeuf, "رومي لحم بقر", "رومي لحم بقر طري، مثالي لوجبات العائلة.", 4.8, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.jarret_de_porc, "جرج لحم خنزير", "جرج لحم خنزير، مثالي للأطباق المطهية أو الشوكوت.", 4.6, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.poulet_roti, "دجاج مشوي", "دجاج مشوي جاهز للأكل، مثالي للوجبات السريعة.", 4.7, "لحوم", 60))
            ProduitService.create(Produit(R.drawable.cotelettes_de_porc, "أضلاع لحم الخنزير", "أضلاع لحم الخنزير لذيذة، مثالية للشواء.", 4.5, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.lait_entier, "حليب كامل", "حليب كامل الدسم، مثالي للإفطار.", 4.8, "منتجات الألبان", 60))
            ProduitService.create(Produit(R.drawable.fromage_rape, "جبن مبشور", "جبن مبشور مخلوط، مثالي للغراتان أو البيتزا.", 4.7, "منتجات الألبان", 45))
            ProduitService.create(Produit(R.drawable.yaourt_nature, "زبادي طبيعي", "زبادي طبيعي كريمي وطازج، مثالي للحلويات.", 4.6, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.beurre_doux, "زبدة غير مملحة", "زبدة غير مملحة طازجة، مثالية للمخبوزات أو التوست.", 4.9, "منتجات الألبان", 40))
            ProduitService.create(Produit(R.drawable.creme_fraiche, "كريمة طازجة", "كريمة طازجة سميكة، مثالية للصلصات أو الحلويات.", 4.8, "منتجات الألبان", 45))
            ProduitService.create(Produit(R.drawable.fromage_blanc, "جبن أبيض", "جبن أبيض ناعم، مثالي للحلويات الخفيفة.", 4.7, "منتجات الألبان", 55))
            ProduitService.create(Produit(R.drawable.lait_de_chevre, "حليب الماعز", "حليب الماعز الطازج، غني بالنكهة والمغذيات.", 4.6, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.fromage_de_chevre, "جبن ماعز", "جبن ماعز كريمي، مثالي للسلطات.", 4.8, "منتجات الألبان", 40))
            ProduitService.create(Produit(R.drawable.lait_de_soja, "حليب الصويا", "حليب الصويا الطبيعي، مثالي لمن يعانون من عدم تحمل اللاكتوز.", 4.5, "منتجات الألبان", 60))
            ProduitService.create(Produit(R.drawable.fromage_comte, "جبن كونتي", "جبن كونتي معتق، غني ولذيذ.", 4.9, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.croissants, "كرواسان", "كرواسان طازج بالزبدة، مثالي للإفطار.", 4.9, "حلويات", 50))
            ProduitService.create(Produit(R.drawable.eclairs_au_chocolat, "إكلير بالشوكولاتة", "إكلير بالشوكولاتة الذائبة، مثالي للذواقة.", 4.8, "حلويات", 45))
            ProduitService.create(Produit(R.drawable.tarte_aux_pommes, "تورتة التفاح", "تورتة التفاح المنزلية، لذيذة وحلوة.", 4.7, "حلويات", 40))
            ProduitService.create(Produit(R.drawable.madeleines, "مادلين", "مادلين طازج، خفيف وعطر.", 4.6, "حلويات", 50))
            ProduitService.create(Produit(R.drawable.chocolatines, "شوكولاتينات", "شوكولاتينات محشوة بالشوكولاتة الذائبة، مثالية للغداء.", 4.9, "حلويات", 60))
            ProduitService.create(Produit(R.drawable.macarons, "ماكارون", "ماكارون رقيق، بنكهة فاخرة وخفيفة.", 4.8, "حلويات", 45))
            ProduitService.create(Produit(R.drawable.tarte_au_citron, "تورتة الليمون", "تورتة الليمون الحامضة والحلوة، لذيذة ومنعشة.", 4.7, "حلويات", 40))
            ProduitService.create(Produit(R.drawable.brioche, "بريوش", "بريوش ناعم ورقيق، مثالي للإفطار.", 4.6, "حلويات", 50))
            ProduitService.create(Produit(R.drawable.pain_au_chocolat, "خبز الشوكولاتة", "خبز الشوكولاتة لذيذ، مثالي لراحة الذواقة.", 4.9, "حلويات", 55))
            ProduitService.create(Produit(R.drawable.muffins, "مافن", "مافن بالفواكه الطازجة، مثالي للوجبات الخفيفة.", 4.8, "حلويات", 50))
            ProduitService.create(Produit(R.drawable.cafe_moulu, "قهوة مطحونة", "قهوة مطحونة غنية وعطرية، مثالية للإفطار.", 4.9, "مشروبات", 60))
            ProduitService.create(Produit(R.drawable.jus_dorange, "عصير البرتقال", "عصير البرتقال الطازج، غني بفيتامين C.", 4.8, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.the_vert, "شاي أخضر", "شاي أخضر عضوي، مثالي للاسترخاء.", 4.7, "مشروبات", 55))
            ProduitService.create(Produit(R.drawable.eau_petillante, "ماء غازي", "ماء غازي منعش، مثالي مع الوجبات.", 4.6, "مشروبات", 60))
            ProduitService.create(Produit(R.drawable.jus_de_pomme, "عصير التفاح", "عصير التفاح الطازج، حلو ومنعش.", 4.8, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.limonade, "ليمونادة", "ليمونادة مصنوعة يدويا، حلوة ومليئة بنكهات الليمون.", 4.7, "مشروبات", 55))
            ProduitService.create(Produit(R.drawable.soda_cola, "صودا كولا", "صودا كولا تقليدية، مشروب منعش ومغازي.", 4.5, "مشروبات", 65))
            ProduitService.create(Produit(R.drawable.eau_plate, "ماء مسطح", "ماء معدني مسطح، مثالي للترطيب.", 4.6, "مشروبات", 70))
            ProduitService.create(Produit(R.drawable.vin_rouge, "نبيذ أحمر", "نبيذ أحمر جاف، مثالي مع الوجبات.", 4.9, "مشروبات", 40))
            ProduitService.create(Produit(R.drawable.vin_blanc, "نبيذ أبيض", "نبيذ أبيض جاف، خفيف وفواكه.", 4.8, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.curcuma, "كركم", "كركم مطحون، مثالي لإضافة اللون والطعم للأطباق.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.poivre_noir, "فلفل أسود", "فلفل أسود حب، لنكهة حارة وطازجة.", 4.9, "توابل", 60))
            ProduitService.create(Produit(R.drawable.cumin, "كمون", "كمون مطحون، لإضافة نكهة دافئة وحارة للأطباق.", 4.7, "توابل", 55))
            ProduitService.create(Produit(R.drawable.paprika, "بابريكا", "بابريكا حلوة، مثالية للأطباق المطهوة واللحوم المشوية.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.curry, "كاري", "كاري مطحون، مزيج من التوابل اللذيذة للوصفات الغريبة.", 4.7, "توابل", 60))
            ProduitService.create(Produit(R.drawable.thym, "زعتر", "زعتر مجفف، مثالي لتطييب اللحوم والخضروات.", 4.6, "توابل", 45))
            ProduitService.create(Produit(R.drawable.cannelle, "قرفة", "قرفة مطحونة، لإضافة لمسة حلوة وحارة للحلويات.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.gingembre, "زنجبيل", "زنجبيل مطحون، لإضافة حدة وحرارة للأطباق.", 4.7, "توابل", 55))
            ProduitService.create(Produit(R.drawable.safran, "زعفران", "زعفران، بهار ثمين لونه ورائحته الفريدة.", 4.9, "توابل", 40))
            ProduitService.create(Produit(R.drawable.piment_de_cayenne, "فلفل حريف كايين", "فلفل حريف كايين مطحون، لمحبي الأطباق الحارة.", 4.6, "توابل", 50))
            ProduitService.create(Produit(R.drawable.flocons_davoine, "شوفان", "شوفان، مثالي لفطور مغذي.", 4.9, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.muesli, "موسلي", "موسلي بالفواكه والبذور، مثالي لنظام غذائي متوازن.", 4.8, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.cornflakes, "كورن فليكس", "كورن فليكس هش، مثالي لفطور سريع.", 4.7, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.riz_blanc, "أرز أبيض", "أرز بسمتي أبيض، مثالي مع الوجبات.", 4.6, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.quinoa, "كينيوا", "كينيوا عضوي، غني بالبروتينات ومثالي للأطباق النباتية.", 4.8, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.pates_completes, "مكرونة كاملة", "مكرونة كاملة، لوجبة صحية ومغذية.", 4.7, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.semoule, "سميد", "سميد ناعم، مثالي للكسكس والأطباق المطهوة.", 4.6, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.pates_italiennes, "مكرونة إيطالية", "مكرونة إيطالية تقليدية، مثالية مع الصلصات.", 4.9, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.couscous, "كسكس", "كسكس، طبق تقليدي من المطبخ المتوسطي.", 4.7, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.riz_basmati, "أرز بسمتي", "أرز بسمتي معطر، مثالي مع الأطباق الشرقية.", 4.8, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.pates_a_la_farine_de_ble_complet, "دقيق القمح الكامل", "دقيق القمح الكامل متعدد الاستعمالات، مثالي للمخبوزات والطهي.", 4.9, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.flocons_davoine, "شوفان سريع التحضير", "شوفان سريع التحضير، مثالي لفطور سريع ومغذي.", 4.8, "حبوب", 50))
        }

        val currentLocale = resources.configuration.locale

        if (currentLocale.language == "fr") {
            btnFrancais.setBackgroundResource(R.drawable.toggle_selected)
            btnArabic.setBackgroundResource(R.drawable.toggle_unselected)
        } else {
            btnFrancais.setBackgroundResource(R.drawable.toggle_unselected)
            btnArabic.setBackgroundResource(R.drawable.toggle_selected)
        }
    }
}
