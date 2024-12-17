package com.example.mymarket.Fragements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.DATA.ville
import com.example.mymarket.DATA.villeType
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.ProduitService
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


        CategoryService.createArabe(Category(R.drawable.fruits, "فواكه"))
        CategoryService.createArabe(Category(R.drawable.legumes, "خضروات"))
        CategoryService.createArabe(Category(R.drawable.poisson, "أسماك"))
        CategoryService.createArabe(Category(R.drawable.viandes, "لحوم"))
        CategoryService.createArabe(Category(R.drawable.litairs, "منتجات الألبان"))
        CategoryService.createArabe(Category(R.drawable.patisserie, "حلويات"))
        CategoryService.createArabe(Category(R.drawable.boissons, "مشروبات"))
        CategoryService.createArabe(Category(R.drawable.epicess, "توابل"))
        CategoryService.createArabe(Category(R.drawable.cereables, "حبوب"))

        CategoryService.create(Category(R.drawable.fruits, "Fruits"))
        CategoryService.create(Category(R.drawable.legumes, "Légumes"))
        CategoryService.create(Category(R.drawable.poisson, "Poissons"))
        CategoryService.create(Category(R.drawable.viandes, "Viandes"))
        CategoryService.create(Category(R.drawable.litairs, "Laitiers"))
        CategoryService.create(Category(R.drawable.patisserie, "Pâtisseries"))
        CategoryService.create(Category(R.drawable.boissons, "Boissons"))
        CategoryService.create(Category(R.drawable.epicess, "Épices"))
        CategoryService.create(Category(R.drawable.cereables, "Céréales"))


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
    override fun onResume() {
        super.onResume()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    override fun onPause() {
        super.onPause()
        requireActivity().window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}
