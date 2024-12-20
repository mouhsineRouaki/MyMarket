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
import com.example.mymarket.activity.MainActivity
import com.example.mymarket.activity.SplashActivity
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
            val phoneNumber = "tel:0614291145"
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse(phoneNumber)
            }
            startActivity(intent)
        }
        partager.setOnClickListener {
            val supportNumber = "0614291145"
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
                utilisateurService.Clear()
                startActivity(Intent(requireContext(), SplashActivity::class.java))
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
            ProduitService.create(Produit(R.drawable.headsholders,15,"Head & Shoulders", "Shampooing 2-en-1 Classic", 37.0, "Cosmétiques", 20))
            ProduitService.create(Produit(R.drawable.dedourant,"Adidas Stan Smith Déodorant", "Adidas Originals Chaussure, frais et cool, parfum        ", 25.0, "Cosmétiques", 99))
            ProduitService.create(Produit(R.drawable.ace_1l,"Ace 1L", "Nettoyant Manzili Sol Sans Javel Rose Ace 1L        ", 15.0, "nettoyage", 50))
            ProduitService.create( Produit(R.drawable.the_vert, "Thé vert", "Thé vert bio, parfait pour un moment de détente.", 4.7, "Boissons", 55))
            ProduitService.create(Produit(R.drawable.yves_rocher,15,"Yves Rocher Maroc", "Shampooing fortifiant aux plantes", 40.0, "Cosmétiques", 30));
            ProduitService.create( Produit(R.drawable.soda_cola, "Soda cola", "Soda cola classique, une boisson rafraîchissante et pétillante.", 4.5, "Boissons", 65))
            ProduitService.create(Produit(R.drawable.dalaa,5,"Dalaa Couches", "Dalaa Couches bébé Taille 4 - 16 Unités", 25.0, "Cosmétiques", 20))
            ProduitService.create( Produit(R.drawable.lait_de_chevre, "Lait de chèvre", "Lait de chèvre frais, riche en goût et en nutriments.", 4.6, "Laitiers", 50))
            ProduitService.create(Produit(R.drawable.pomme_fuji,"Pommes", "Pommes sucrées et croquantes, parfaites pour les collations.", 4.8, "Fruits", 2))
            ProduitService.create(Produit(R.drawable.bananes,10,"Bananes", "Bananes mûres et sucrées, riches en potassium.", 4.7, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.florame,15,"Florame Maroc", "Crème hydratante aux huiles essentielles", 45.0, "Cosmétiques", 35));
            ProduitService.create(Produit(R.drawable.kiwi,15,"Kiwi", "Kiwi acidulé, riche en vitamine C.", 15.0, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.achifae,"Achifae Argan Oil", "Huile d'argan pure pour soin capillaire", 60.0, "Cosmétiques", 25));
            ProduitService.create(Produit(R.drawable.khmiss,"Masque Ghassoul", "Masque naturel au ghassoul marocain", 70.0, "Cosmétiques", 60));
            ProduitService.create(Produit(R.drawable.oranges,"Oranges", "Oranges juteuses et sucrées, idéales pour un jus frais.", 4.9, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.papier,5,"papier hygiénique", "papier hygiénique Pandoo  4 - 16 Unités", 25.0, "Cosmétiques", 20))
            ProduitService.create(Produit(R.drawable.raisins,"Raisins", "Raisins frais, parfaits pour une collation rapide.", 4.6, "Fruits", 45))
            ProduitService.create( Produit(R.drawable.cornflakes, "Cornflakes", "Cornflakes croustillants, parfaits pour un petit déjeuner rapide.", 4.7, "Céréales", 55))
            ProduitService.create( Produit(R.drawable.eau_plate, "Eau plate", "Eau minérale plate, idéale pour hydrater.", 4.6, "Boissons", 70))

            ProduitService.create( Produit(R.drawable.cafe_moulu, "Café moulu", "Café moulu riche et aromatique, idéal pour le petit déjeuner.", 4.9, "Boissons", 60))
            ProduitService.create( Produit(R.drawable.yaourt_nature, "Yaourt nature", "Yaourt nature crémeux et frais, parfait pour le dessert.", 4.6, "Laitiers", 50))
            ProduitService.create( Produit(R.drawable.creme_fraiche, "Crème fraîche", "Crème fraîche épaisse, idéale pour les sauces ou les desserts.", 4.8, "Laitiers", 45))
            ProduitService.create( Produit(R.drawable.eau_petillante, "Eau pétillante", "Eau pétillante rafraîchissante, idéale pour accompagner vos repas.", 4.6, "Boissons", 60))
            ProduitService.create(Produit(R.drawable.tomates, "Tomates", "Tomates fraîches et juteuses, parfaites pour les salades.", 4.7, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.pommes_de_terre,10, "Pommes de terre", "Pommes de terre fraîches, idéales pour tous les types de plats.", 4.5, "Légumes", 60))
            ProduitService.create(Produit(R.drawable.cho_fleur, "Chou-fleur", "Chou-fleur frais, parfait pour les gratins.", 4.6, "Légumes", 55))
            ProduitService.create( Produit(R.drawable.jus_dorange, "Jus d'orange Marakkech", "Jus d'orange frais, riche en vitamine C.", 10.0, "Boissons", 50))
            ProduitService.create(Produit(R.drawable.fraises,"Fraises", "Fraises fraîches et sucrées, parfaites pour les salades de fruits.", 4.8, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.ananas,"Ananas", "Ananas frais, délicieux et sucré.", 4.7, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.mangue,"Mangue", "Mangue juteuse et sucrée, parfaite en smoothie.", 4.9, "Fruits", 45))
            ProduitService.create(Produit(R.drawable.citrons,"Citrons", "Citrons frais et acides, parfaits pour les boissons.", 4.6, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.prunes,"Prunes", "Prunes juteuses et sucrées, parfaites pour un dessert.", 4.4, "Fruits", 30))
            ProduitService.create(Produit(R.drawable.peches,"Pêches", "Pêches sucrées et juteuses, idéales pour les desserts.", 4.8, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.ceries,"Cerises", "Cerises fraîches et sucrées, parfaites en salade.", 4.7, "Fruits", 35))
            ProduitService.create(Produit(R.drawable.melon,"Melon", "Melon sucré et rafraîchissant, parfait pour l'été.", 4.9, "Fruits", 60))
            ProduitService.create(Produit(R.drawable.papaye,"Papaye", "Papaye sucrée et juteuse, idéale en salade de fruits.", 4.5, "Fruits", 55))

            ProduitService.create( Produit(R.drawable.roti_de_boeuf, "Rôti de bœuf", "Rôti de bœuf tendre, idéal pour les repas de famille.", 4.8, "Viandes", 45))
            ProduitService.create( Produit(R.drawable.jarret_de_porc,30, "Jarret de porc", "Jarret de porc, parfait pour un plat mijoté ou une choucroute.", 4.6, "Viandes", 50))
            ProduitService.create(Produit(R.drawable.froimboise,"Framboises", "Framboises fraîches, parfaites pour les desserts ou smoothies.", 4.6, "Fruits", 50))
            ProduitService.create(Produit(R.drawable.myrtiles,"Myrtilles", "Myrtilles fraîches, idéales pour les céréales ou desserts.", 4.7, "Fruits", 45))
            ProduitService.create(Produit(R.drawable.groseilles, "Groseilles", "Groseilles acides, idéales pour les confitures.", 4.3, "Fruits", 25))
            ProduitService.create(Produit(R.drawable.nectarines, "Nectarines", "Nectarines sucrées et juteuses, parfaites pour les tartes.", 4.8, "Fruits", 40))
            ProduitService.create(Produit(R.drawable.carottes_bio, "Carottes bio", "Carottes fraîches et croquantes, cultivées sans produits chimiques.", 4.5, "Légumes", 60))
            ProduitService.create(Produit(R.drawable.brocoli, "Brocoli", "Brocoli vert et riche en vitamines, parfait pour les plats sautés.", 4.6, "Légumes", 30))
            ProduitService.create(Produit(R.drawable.epinards, "Épinards", "Épinards frais, riches en fer et en vitamines.", 4.7, "Légumes", 50))
            ProduitService.create(Produit(R.drawable.courgettes, "Courgettes", "Courgettes fraîches, idéales pour les ratatouilles.", 4.6, "Légumes", 40))
            ProduitService.create(Produit(R.drawable.poivrons, "Poivrons", "Poivrons colorés, idéals pour les salades ou les plats sautés.", 4.8, "Légumes", 45))
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
            ProduitService.create(Produit(R.drawable.thon_en_conserve, "Thon Isabel", "THON À LA SAUCE TOMATE, idéal pour les salades.", 4.3, "Poissons", 40))
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
            ProduitService.create(Produit(R.drawable.poires,"Poires", "Poires juteuses et sucrées, parfaites en dessert.", 4.7, "Fruits", 55))
            ProduitService.create( Produit(R.drawable.filet_de_porc, "Filet de porc", "Filet de porc maigre, parfait pour les plats rôtis.", 4.6, "Viandes", 40))
            ProduitService.create( Produit(R.drawable.escalopes_de_dinde, "Escalopes de dinde", "Escalopes de dinde tendres et légères, parfaites pour les sautés.", 4.5, "Viandes", 50))
            ProduitService.create( Produit(R.drawable.saucisses_de_toulouse, "Saucisses de Toulouse", "Saucisses de Toulouse traditionnelles, idéales pour un cassoulet.", 4.6, "Viandes", 60))
            ProduitService.create( Produit(R.drawable.bacon_fume,"Bacon fumé", "Bacon fumé, parfait pour le petit déjeuner ou les salades.", 4.7, "Viandes", 55))
            ProduitService.create( Produit(R.drawable.poulet_roti, "Poulet rôti", "Poulet rôti prêt à déguster, idéal pour les repas rapides.", 4.7, "Viandes", 60))
            ProduitService.create( Produit(R.drawable.cotelettes_de_porc, "Côtelettes de porc", "Côtelettes de porc savoureuses, idéales pour les grillades.", 4.5, "Viandes", 45))
            ProduitService.create( Produit(R.drawable.lait_entier,10, "Lait Salim", "Lait entier crémeux, 1L.", 12.0, "Laitiers", 60))
            ProduitService.create( Produit(R.drawable.fromage_rape, "Fromage râpé", "Fromage râpé mélangé, idéal pour les gratins ou les pizzas.", 4.7, "Laitiers", 45))
            ProduitService.create( Produit(R.drawable.beurre_doux, "Beurre doux", "Beurre doux frais, idéal pour la pâtisserie ou les tartines.", 4.9, "Laitiers", 40))
            ProduitService.create( Produit(R.drawable.fromage_blanc,5, "Fromage blanc", "Fromage blanc onctueux, parfait pour un dessert léger.", 4.7, "Laitiers", 55))
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
            ProduitService.create( Produit(R.drawable.jus_de_pomme, "Jus de pomme", "Jus de pomme frais, légèrement sucré et rafraîchissant.", 4.8, "Boissons", 50))
            ProduitService.create( Produit(R.drawable.limonade, "PEPSI", "Limonade artisanale, sucrée et bien citronnée.", 4.7, "Boissons", 55))
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

            ProduitService.create(Produit(R.drawable.headsholders,15,"هيد آند شولدرز", "شامبو 2 في 1 كلاسيك", 37.0, "تجميل", 20))
            ProduitService.create(Produit(R.drawable.dedourant,"أديداس ستان سميث مزيل عرق", "حذاء أديداس أورجينال، منعش وبارد، عطر        ", 25.0, "تجميل", 99))
            ProduitService.create(Produit(R.drawable.ace_1l,"آيس 1 لتر", "منظف منزلي بدون مبيض وردي آيس 1 لتر        ", 15.0, "تنظيف", 50))
            ProduitService.create(Produit(R.drawable.the_vert, "الشاي الأخضر", "شاي أخضر بيولوجي، مثالي للحظات الاسترخاء.", 4.7, "مشروبات", 55))
            ProduitService.create(Produit(R.drawable.yves_rocher,15,"إيف روشيه المغرب", "شامبو مقوي بالنباتات", 40.0, "تجميل", 30))
            ProduitService.create(Produit(R.drawable.soda_cola, "صودا كولا", "صودا كولا كلاسيكية، مشروب منعش وفوار.", 4.5, "مشروبات", 65))
            ProduitService.create(Produit(R.drawable.dalaa,5,"دلع حفاضات", "حفاضات دلع للأطفال حجم 4 - 16 وحدة", 25.0, "تجميل", 20))
            ProduitService.create(Produit(R.drawable.lait_de_chevre, "حليب الماعز", "حليب ماعز طازج، غني بالنكهة والعناصر الغذائية.", 4.6, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.pomme_fuji,"تفاح", "تفاح حلو ومقرمش، مثالي للوجبات الخفيفة.", 4.8, "فواكه", 2))
            ProduitService.create(Produit(R.drawable.bananes,10,"موز", "موز ناضج وحلو، غني بالبوتاسيوم.", 4.7, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.florame,15,"فلورام المغرب", "كريم مرطب بالزيوت الأساسية", 45.0, "تجميل", 35))
            ProduitService.create(Produit(R.drawable.kiwi,15,"كيوي", "كيوي منعش وغني بفيتامين C.", 15.0, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.achifae,"زيت أرغان الشفاء", "زيت أرغان نقي للعناية بالشعر", 60.0, "تجميل", 25))
            ProduitService.create(Produit(R.drawable.khmiss,"قناع غاسول", "قناع طبيعي بالغاسول المغربي", 70.0, "تجميل", 60))
            ProduitService.create(Produit(R.drawable.oranges,"برتقال", "برتقال عصيري وحلو، مثالي لعصير طازج.", 4.9, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.papier,5,"ورق صحي", "ورق صحي باندو 4 - 16 وحدة", 25.0, "تجميل", 20))
            ProduitService.create(Produit(R.drawable.raisins,"عنب", "عنب طازج، مثالي لوجبة خفيفة سريعة.", 4.6, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.cornflakes, "رقائق الذرة", "رقائق ذرة مقرمشة، مثالية لفطور سريع.", 4.7, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.eau_plate, "ماء معدني", "ماء معدني مسطح، مثالي للترطيب.", 4.6, "مشروبات", 70))
            ProduitService.create(Produit(R.drawable.cafe_moulu, "قهوة مطحونة", "قهوة مطحونة غنية وعطرية، مثالية للفطور.", 4.9, "مشروبات", 60))
            ProduitService.create(Produit(R.drawable.yaourt_nature, "زبادي طبيعي", "زبادي طبيعي كريمي ومنعش، مثالي للتحلية.", 4.6, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.creme_fraiche, "كريمة طازجة", "كريمة طازجة كثيفة، مثالية للصوص أو الحلويات.", 4.8, "منتجات الألبان", 45))
            ProduitService.create(Produit(R.drawable.eau_petillante, "ماء غازي", "ماء غازي منعش، مثالي لمرافقة وجباتكم.", 4.6, "مشروبات", 60))
            ProduitService.create(Produit(R.drawable.tomates, "طماطم", "طماطم طازجة وعصيرية، مثالية للسلطات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.pommes_de_terre,10, "بطاطس", "بطاطس طازجة، مثالية لجميع أنواع الأطباق.", 4.5, "خضروات", 60))
            ProduitService.create(Produit(R.drawable.cho_fleur, "قرنبيط", "قرنبيط طازج، مثالي للطواجن.", 4.6, "خضروات", 55))
            ProduitService.create(Produit(R.drawable.jus_dorange, "عصير برتقال مراكش", "عصير برتقال طازج، غني بفيتامين C.", 10.0, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.fraises, "فراولة", "فراولة طازجة وحلوة، مثالية لسلطة الفواكه.", 4.8, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.ananas, "أناناس", "أناناس طازج، لذيذ وحلو.", 4.7, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.mangue, "مانجو", "مانجو عصيري وحلو، مثالي في السموذي.", 4.9, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.citrons, "ليمون", "ليمون طازج وحامض، مثالي للمشروبات.", 4.6, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.prunes, "برقوق", "برقوق عصيري وحلو، مثالي للتحلية.", 4.4, "فواكه", 30))
            ProduitService.create(Produit(R.drawable.peches, "خوخ", "خوخ حلو وعصيري، مثالي للحلويات.", 4.8, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.ceries, "كرز", "كرز طازج وحلو، مثالي في السلطات.", 4.7, "فواكه", 35))
            ProduitService.create(Produit(R.drawable.melon, "شمام", "شمام حلو ومنعش، مثالي للصيف.", 4.9, "فواكه", 60))
            ProduitService.create(Produit(R.drawable.papaye, "بابايا", "بابايا حلوة وعصيرية، مثالية في سلطة الفواكه.", 4.5, "فواكه", 55))
            ProduitService.create(Produit(R.drawable.roti_de_boeuf, "لحم مشوي", "لحم بقر مشوي طري، مثالي للوجبات العائلية.", 4.8, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.jarret_de_porc, 30, "سيقان لحم الخنزير", "سيقان لحم الخنزير، مثالية للطهي البطيء أو الكسكروت.", 4.6, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.froimboise, "توت العليق", "توت العليق الطازج، مثالي للحلويات أو السموذي.", 4.6, "فواكه", 50))
            ProduitService.create(Produit(R.drawable.myrtiles, "توت أزرق", "توت أزرق طازج، مثالي مع الحبوب أو الحلويات.", 4.7, "فواكه", 45))
            ProduitService.create(Produit(R.drawable.groseilles, "عنب الثعلب", "عنب الثعلب الحامض، مثالي لصنع المربى.", 4.3, "فواكه", 25))
            ProduitService.create(Produit(R.drawable.nectarines, "نيكتارين", "نيكتارين حلو وعصيري، مثالي للفطائر.", 4.8, "فواكه", 40))
            ProduitService.create(Produit(R.drawable.carottes_bio, "جزر عضوي", "جزر طازج ومقرمش، مزروع بدون مواد كيميائية.", 4.5, "خضروات", 60))
            ProduitService.create(Produit(R.drawable.brocoli, "بروكلي", "بروكلي غني بالفيتامينات، مثالي للأطباق المقلية.", 4.6, "خضروات", 30))
            ProduitService.create(Produit(R.drawable.epinards, "سبانخ", "سبانخ طازج، غني بالحديد والفيتامينات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.courgettes, "كوسة", "كوسة طازجة، مثالية لتحضير راتاتوي.", 4.6, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.poivrons, "فلفل", "فلفل ملون، مثالي للسلطات أو الأطباق المقلية.", 4.8, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.haricots_verts, "فاصوليا خضراء", "فاصوليا خضراء طازجة، مثالية كطبق جانبي.", 4.5, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.petits_pois, "بازلاء", "بازلاء طازجة، لذيذة في الشوربات أو السلطات.", 4.6, "خضروات", 35))
            ProduitService.create(Produit(R.drawable.aubergines, "باذنجان", "باذنجان طازج، مثالي للأطباق المتوسطية.", 4.4, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.oignons, "بصل", "بصل طازج ولذيذ، مثالي لتتبيل الأطباق.", 4.5, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.laitue, "خس", "خس طازج، مثالي للسلطات.", 4.7, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.chou, "ملفوف", "ملفوف أخضر طازج، مثالي للشوربات أو السلطات.", 4.6, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.celeri, "كرفس", "كرفس طازج، مثالي للشوربات أو السلطات.", 4.5, "خضروات", 35))
            ProduitService.create(Produit(R.drawable.fenouil, "شمر", "شمر طازج، بطعم لطيف ومميز.", 4.6, "خضروات", 25))
            ProduitService.create(Produit(R.drawable.radis, "فجل", "فجل مقرمش ولطيف الحدة، مثالي في السلطات.", 4.7, "خضروات", 50))
            ProduitService.create(Produit(R.drawable.poireaux, "كراث", "كراث طازج، مثالي للشوربات أو الأطباق البطيئة.", 4.8, "خضروات", 45))
            ProduitService.create(Produit(R.drawable.betteraves, "شمندر", "شمندر طازج، مثالي للسلطات أو العصائر.", 4.5, "خضروات", 40))
            ProduitService.create(Produit(R.drawable.saumon_frais, "سلمون طازج", "شرائح سلمون طازج، غنية بأوميغا-3.", 4.9, "أسماك", 25))
            ProduitService.create(Produit(R.drawable.thon_en_conserve, "تونة إيزابيل", "تونة بصلصة الطماطم، مثالية للسلطات.", 4.3, "أسماك", 40))
            ProduitService.create(Produit(R.drawable.maquereau, "ماكريل", "ماكريل طازج، مثالي للشوي.", 4.6, "أسماك", 30))
            ProduitService.create(Produit(R.drawable.sardines, "سردين", "سردين معلب، مثالي لوصفة سريعة.", 4.4, "أسماك", 35))
            ProduitService.create(Produit(R.drawable.truite, "سمك السلمون المرقط", "سمك السلمون المرقط الطازج، مثالي للطهي بالفرن.", 4.7, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.sole, "سمك موسى", "سمك موسى الطازج، دقيق ومثالي للأطباق الراقية.", 4.8, "أسماك", 45))
            ProduitService.create(Produit(R.drawable.fletan, "هلبوت", "هلبوت طازج، مثالي للشوي.", 4.9, "أسماك", 40))
            ProduitService.create(Produit(R.drawable.bar, "سمك القاروص", "سمك قاروص طازج، مثالي لعشاء فاخر.", 4.8, "أسماك", 55))
            ProduitService.create(Produit(R.drawable.rouget, "سمك الروجي", "سمك الروجي الطازج، لذيذ عند الشوي أو في البويابيس.", 4.7, "أسماك", 45))
            ProduitService.create(Produit(R.drawable.colin, "سمك الكولن", "سمك الكولن الطازج، مثالي للطهي السريع.", 4.6, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.homard, "كركند", "كركند طازج، مثالي لوجبة فاخرة.", 4.9, "أسماك", 25))
            ProduitService.create(Produit(R.drawable.crevettes, "جمبري", "جمبري طازج، مثالي للكوكتيلات أو الأطباق الآسيوية.", 4.8, "أسماك", 60))
            ProduitService.create(Produit(R.drawable.palourdes, "محار", "محار طازج، مثالي لحساء المأكولات البحرية.", 4.7, "أسماك", 50))
            ProduitService.create(Produit(R.drawable.moules, "بلح البحر", "بلح البحر الطازج، لذيذ مع صلصة النبيذ الأبيض.", 4.5, "أسماك", 55))
            ProduitService.create(Produit(R.drawable.poulet_entier, "دجاجة كاملة", "دجاجة كاملة من المزرعة، مثالية للشوي أو الطبخ في الفرن.", 4.8, "لحوم", 40))
            ProduitService.create(Produit(R.drawable.steak_de_boeuf, "ستيك لحم بقر", "ستيك لحم بقر طري، مثالي للشوي.", 4.9, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.cotelettes_dagneau, "قطع لحم الضأن", "قطع لحم الضأن اللذيذة، مثالية للشواء.", 4.7, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.poires, "كمثرى", "كمثرى عصارية وحلوة، مثالية للتحلية.", 4.7, "فواكه", 55))
            ProduitService.create(Produit(R.drawable.filet_de_porc, "فيليه لحم الخنزير", "فيليه لحم الخنزير قليل الدهن، مثالي للأطباق المشوية.", 4.6, "لحوم", 40))
            ProduitService.create(Produit(R.drawable.escalopes_de_dinde, "شرائح ديك رومي", "شرائح ديك رومي طرية وخفيفة، مثالية للقلي السريع.", 4.5, "لحوم", 50))
            ProduitService.create(Produit(R.drawable.saucisses_de_toulouse, "نقانق تولوز", "نقانق تولوز التقليدية، مثالية لكاسوليه.", 4.6, "لحوم", 60))
            ProduitService.create(Produit(R.drawable.bacon_fume, "لحم مقدد مدخن", "لحم مقدد مدخن، مثالي للفطور أو السلطات.", 4.7, "لحوم", 55))
            ProduitService.create(Produit(R.drawable.poulet_roti, "دجاج مشوي", "دجاج مشوي جاهز للتقديم، مثالي للوجبات السريعة.", 4.7, "لحوم", 60))
            ProduitService.create(Produit(R.drawable.cotelettes_de_porc, "قطع لحم الخنزير", "قطع لحم الخنزير اللذيذة، مثالية للشوي.", 4.5, "لحوم", 45))
            ProduitService.create(Produit(R.drawable.lait_entier, 10, "حليب سليم", "حليب كامل الدسم، 1 لتر.", 12.0, "منتجات الألبان", 60))
            ProduitService.create(Produit(R.drawable.fromage_rape, "جبن مبشور", "جبن مبشور مخلوط، مثالي للجرتان أو البيتزا.", 4.7, "منتجات الألبان", 45))
            ProduitService.create(Produit(R.drawable.beurre_doux, "زبدة غير مملحة", "زبدة غير مملحة طازجة، مثالية للحلويات أو التوست.", 4.9, "منتجات الألبان", 40))
            ProduitService.create(Produit(R.drawable.fromage_blanc, 5, "جبن أبيض", "جبن أبيض كريمي، مثالي للتحلية الخفيفة.", 4.7, "منتجات الألبان", 55))
            ProduitService.create(Produit(R.drawable.fromage_de_chevre, 10, "جبن الماعز", "جبن الماعز الكريمي، مثالي للسلطات.", 4.8, "منتجات الألبان", 40))
            ProduitService.create(Produit(R.drawable.lait_de_soja, "حليب الصويا", "حليب الصويا الطبيعي، مثالي للأشخاص الذين يعانون من عدم تحمل اللاكتوز.", 4.5, "منتجات الألبان", 60))
            ProduitService.create(Produit(R.drawable.fromage_comte, 10, "جبن كومتي", "جبن كومتي المعتق، غني ولذيذ.", 4.9, "منتجات الألبان", 50))
            ProduitService.create(Produit(R.drawable.croissants, "كرواسون", "كرواسون بالزبدة الطازجة، مثالي للفطور.", 4.9, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.eclairs_au_chocolat, "إكلير بالشوكولاتة", "إكلير بالشوكولاتة الذائبة، مثالي لعشاق الحلوى.", 4.8, "حبوب", 45))
            ProduitService.create(Produit(R.drawable.tarte_aux_pommes, "فطيرة التفاح", "فطيرة التفاح المنزلية، لذيذة وحلوة.", 4.7, "حبوب", 40))
            ProduitService.create(Produit(R.drawable.madeleines, "مدلين", "مدلين طازجة، خفيفة وعطرية.", 4.6, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.chocolatines, "شوكولاتين", "شوكولاتين بالشوكولاتة الذائبة، مثالية للوجبات الخفيفة.", 4.9, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.macarons, "ماكارون", "ماكارون دقيق، بنكهة خفيفة ومكررة.", 4.8, "حبوب", 45))
            ProduitService.create(Produit(R.drawable.tarte_au_citron, "فطيرة الليمون", "فطيرة الليمون الحامضة والحلوة، لذة منعشة.", 4.7, "حبوب", 40))
            ProduitService.create(Produit(R.drawable.brioche, "بريوش", "بريوش ناعم وهش، مثالي للفطور.", 4.6, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.pain_au_chocolat, "خبز بالشوكولاتة", "خبز بالشوكولاتة اللذيذ، مثالي لوجبة خفيفة.", 4.9, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.jus_de_pomme, "عصير التفاح", "عصير التفاح الطازج، حلو ومنعش.", 4.8, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.limonade, "بيبسي", "ليموناضة مصنوعة يدويًا، حلوة ومنعشة بنكهة الليمون.", 4.7, "مشروبات", 55))
            ProduitService.create(Produit(R.drawable.vin_rouge, "نبيذ أحمر", "نبيذ أحمر جاف، مثالي لمرافقة الوجبات.", 4.9, "مشروبات", 40))
            ProduitService.create(Produit(R.drawable.vin_blanc, "نبيذ أبيض", "نبيذ أبيض جاف، خفيف وفاكهي.", 4.8, "مشروبات", 50))
            ProduitService.create(Produit(R.drawable.curcuma, "كركم", "كركم مطحون، مثالي لإضافة اللون والنكهة إلى أطباقك.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.poivre_noir, "فلفل أسود", "فلفل أسود حب، لمذاق لاذع ومنعش.", 4.9, "توابل", 60))
            ProduitService.create(Produit(R.drawable.cumin, "كمون", "كمون مطحون، لإضافة نكهة دافئة وحارة إلى أطباقك.", 4.7, "توابل", 55))
            ProduitService.create(Produit(R.drawable.paprika, "بابريكا", "بابريكا حلوة، مثالية للأطباق المطهية ببطء واللحوم المشوية.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.curry, "كاري", "كاري مطحون، مزيج لذيذ من التوابل لوصفاتك الغريبة.", 4.7, "توابل", 60))
            ProduitService.create(Produit(R.drawable.thym, "زعتر", "زعتر مجفف، مثالي لتعطير اللحوم والخضروات.", 4.6, "توابل", 45))
            ProduitService.create(Produit(R.drawable.cannelle, "قرفة", "قرفة مطحونة، لمسة حلوة وحارة في الحلويات.", 4.8, "توابل", 50))
            ProduitService.create(Produit(R.drawable.gingembre, "زنجبيل", "زنجبيل مطحون، لإضافة حرارة ونكهة حارة إلى أطباقك.", 4.7, "توابل", 55))
            ProduitService.create(Produit(R.drawable.safran, "زعفران", "زعفران، بهار ثمين لإضافة لون ورائحة فريدة.", 4.9, "توابل", 40))
            ProduitService.create(Produit(R.drawable.piment_de_cayenne, "فلفل كايين", "فلفل كايين مطحون، لمحبي الأطباق الحارة.", 4.6, "توابل", 50))
            ProduitService.create(Produit(R.drawable.flocons_davoine, "رقائق الشوفان", "رقائق الشوفان، مثالية لفطور غني بالطاقة.", 4.9, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.muesli, "موسلي", "موسلي بالفواكه والبذور، مثالي لنظام غذائي متوازن.", 4.8, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.riz_blanc, "أرز أبيض", "أرز أبيض بسمتي، مثالي لمرافقة وجباتك.", 4.6, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.quinoa, "كينوا", "كينوا عضوية، غنية بالبروتين ومثالية للأطباق النباتية.", 4.8, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.pates_completes, "مكرونة كاملة", "مكرونة كاملة، لوجبة صحية ومغذية.", 4.7, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.semoule, "سميد", "سميد ناعم، مثالي للكسكس والأطباق المطهية.", 4.6, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.pates_italiennes, "مكرونة إيطالية", "مكرونة إيطالية تقليدية، مثالية مع الصلصات.", 4.9, "حبوب", 50))
            ProduitService.create(Produit(R.drawable.couscous, "كسكس", "كسكس، طبق كلاسيكي من المطبخ المتوسطي.", 4.7, "حبوب", 60))
            ProduitService.create(Produit(R.drawable.riz_basmati, "أرز بسمتي", "أرز بسمتي عطري، مثالي لمرافقة أطباقك الشرقية.", 4.8, "حبوب", 55))
            ProduitService.create(Produit(R.drawable.pates_a_la_farine_de_ble_complet, "طحين القمح", "طحين قمح متعدد الاستخدامات، مثالي للحبوب والطبخ.", 4.9, "حبوب", 60))
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
