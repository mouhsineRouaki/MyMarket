package com.example.mymarket.Fragements

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
import com.example.mymarket.DATA.Category
import com.example.mymarket.DATA.Produit
import com.example.mymarket.R
import com.example.mymarket.Service.CategoryService
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import com.example.mymarket.adapters.adapterCartProduit
import com.example.mymarket.adapters.adapterCartProduit2
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeFragement: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: adapterCartProduit
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var currentPosition = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        val recyclerPlusVente: RecyclerView = view.findViewById(R.id.recycle_plusVente)
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
                .replace(R.id.fragment_container, CategoryFragment())
                .commit()
            bottomNavigation?.selectedItemId = R.id.category
        }

        buttonUser.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ProfilFragment())
                .commit()
        }

        ProduitService.create(Produit("Pommes Fuji", "Pommes sucrées et croquantes, parfaites pour les collations.", 4.8, "Fruits", 40))
        ProduitService.create(Produit("Bananes", "Bananes mûres et sucrées, riches en potassium.", 4.7, "Fruits", 50))
        ProduitService.create(Produit("Oranges", "Oranges juteuses et sucrées, idéales pour un jus frais.", 4.9, "Fruits", 60))
        ProduitService.create(Produit("Raisins", "Raisins frais, parfaits pour une collation rapide.", 4.6, "Fruits", 45))
        ProduitService.create(Produit("Poires", "Poires juteuses et sucrées, parfaites en dessert.", 4.7, "Fruits", 55))
        ProduitService.create(Produit("Fraises", "Fraises fraîches et sucrées, parfaites pour les salades de fruits.", 4.8, "Fruits", 60))
        ProduitService.create(Produit("Kiwi", "Kiwi acidulé, riche en vitamine C.", 4.5, "Fruits", 40))
        ProduitService.create(Produit("Ananas", "Ananas frais, délicieux et sucré.", 4.7, "Fruits", 50))
        ProduitService.create(Produit("Mangue", "Mangue juteuse et sucrée, parfaite en smoothie.", 4.9, "Fruits", 45))
        ProduitService.create(Produit("Citrons", "Citrons frais et acides, parfaits pour les boissons.", 4.6, "Fruits", 50))
        ProduitService.create(Produit("Prunes", "Prunes juteuses et sucrées, parfaites pour un dessert.", 4.4, "Fruits", 30))
        ProduitService.create(Produit("Pêches", "Pêches sucrées et juteuses, idéales pour les desserts.", 4.8, "Fruits", 40))
        ProduitService.create(Produit("Cerises", "Cerises fraîches et sucrées, parfaites en salade.", 4.7, "Fruits", 35))
        ProduitService.create(Produit("Melon", "Melon sucré et rafraîchissant, parfait pour l'été.", 4.9, "Fruits", 60))
        ProduitService.create(Produit("Papaye", "Papaye sucrée et juteuse, idéale en salade de fruits.", 4.5, "Fruits", 55))
        ProduitService.create(Produit("Framboises", "Framboises fraîches, parfaites pour les desserts ou smoothies.", 4.6, "Fruits", 50))
        ProduitService.create(Produit("Myrtilles", "Myrtilles fraîches, idéales pour les céréales ou desserts.", 4.7, "Fruits", 45))
        ProduitService.create(Produit("Groseilles", "Groseilles acides, idéales pour les confitures.", 4.3, "Fruits", 25))
        ProduitService.create(Produit("Nectarines", "Nectarines sucrées et juteuses, parfaites pour les tartes.", 4.8, "Fruits", 40))
        ProduitService.create(Produit("Carottes bio", "Carottes fraîches et croquantes, cultivées sans produits chimiques.", 4.5, "Légumes", 60))
        ProduitService.create(Produit("Brocoli", "Brocoli vert et riche en vitamines, parfait pour les plats sautés.", 4.6, "Légumes", 30))
        ProduitService.create(Produit("Épinards", "Épinards frais, riches en fer et en vitamines.", 4.7, "Légumes", 50))
        ProduitService.create(Produit("Courgettes", "Courgettes fraîches, idéales pour les ratatouilles.", 4.6, "Légumes", 40))
        ProduitService.create(Produit("Poivrons", "Poivrons colorés, idéals pour les salades ou les plats sautés.", 4.8, "Légumes", 45))
        ProduitService.create(Produit("Pommes de terre", "Pommes de terre fraîches, idéales pour tous les types de plats.", 4.5, "Légumes", 60))
        ProduitService.create(Produit("Tomates", "Tomates fraîches et juteuses, parfaites pour les salades.", 4.7, "Légumes", 50))
        ProduitService.create(Produit("Chou-fleur", "Chou-fleur frais, parfait pour les gratins.", 4.6, "Légumes", 55))
        ProduitService.create(Produit("Haricots verts", "Haricots verts frais, parfaits pour un plat d'accompagnement.", 4.5, "Légumes", 45))
        ProduitService.create(Produit("Petits pois", "Petits pois frais, délicieux dans les soupes ou salades.", 4.6, "Légumes", 35))
        ProduitService.create(Produit("Aubergines", "Aubergines fraîches, idéales pour les plats méditerranéens.", 4.4, "Légumes", 40))
        ProduitService.create(Produit("Oignons", "Oignons frais et savoureux, parfaits pour aromatiser vos plats.", 4.5, "Légumes", 50))
        ProduitService.create(Produit("Laitue", "Laitue fraîche, idéale pour les salades.", 4.7, "Légumes", 40))
        ProduitService.create(Produit("Chou", "Chou vert frais, parfait pour les soupes ou les salades.", 4.6, "Légumes", 45))
        ProduitService.create(Produit("Céleri", "Céleri frais, idéal pour les soupes ou les salades.", 4.5, "Légumes", 35))
        ProduitService.create(Produit("Fenouil", "Fenouil frais, avec une saveur douce et anisée.", 4.6, "Légumes", 25))
        ProduitService.create(Produit("Radis", "Radis croquants et légèrement piquants, parfaits en salade.", 4.7, "Légumes", 50))
        ProduitService.create(Produit("Poireaux", "Poireaux frais, parfaits pour les soupes ou les plats mijotés.", 4.8, "Légumes", 45))
        ProduitService.create(Produit("Betteraves", "Betteraves fraîches, idéales pour les salades ou jus.", 4.5, "Légumes", 40))
        ProduitService.create(Produit("Saumon frais", "Filets de saumon frais, riches en oméga-3.", 4.9, "Poissons", 25))
        ProduitService.create(Produit("Thon en conserve", "Thon sauvage en conserve, idéal pour les salades.", 4.3, "Poissons", 40))
        ProduitService.create(Produit("Maquereau", "Maquereau frais, idéal pour les grillades.", 4.6, "Poissons", 30))
        ProduitService.create(Produit("Sardines", "Sardines en conserve, parfaites pour une recette rapide.", 4.4, "Poissons", 35))
        ProduitService.create(Produit("Truite", "Truite fraîche, idéale pour une cuisson en papillote.", 4.7, "Poissons", 50))
        ProduitService.create(Produit("Sole", "Sole fraîche, délicate et parfaite pour les plats raffinés.", 4.8, "Poissons", 45))
        ProduitService.create(Produit("Flétan", "Flétan frais, parfait pour les grillades.", 4.9, "Poissons", 40))
        ProduitService.create(Produit("Bar", "Bar frais, idéal pour un dîner élégant.", 4.8, "Poissons", 55))
        ProduitService.create(Produit("Rouget", "Rouget frais, délicieux grillé ou en bouillabaisse.", 4.7, "Poissons", 45))
        ProduitService.create(Produit("Colin", "Colin frais, idéal pour une cuisson rapide.", 4.6, "Poissons", 50))
        ProduitService.create(Produit("Homard", "Homard frais, parfait pour un plat de luxe.", 4.9, "Poissons", 25))
        ProduitService.create(Produit("Crevettes", "Crevettes fraîches, idéales pour les cocktails ou les plats asiatiques.", 4.8, "Poissons", 60))
        ProduitService.create(Produit("Palourdes", "Palourdes fraîches, parfaites pour une soupe de fruits de mer.", 4.7, "Poissons", 50))
        ProduitService.create(Produit("Moules", "Moules fraîches, délicieuses avec une sauce au vin blanc.", 4.5, "Poissons", 55))
        ProduitService.create(Produit("Coquilles Saint-Jacques", "Coquilles Saint-Jacques fraîches, parfaites pour les plats de fête.", 4.9, "Poissons", 30))
        ProduitService.create(Produit("Calmars", "Calmars frais, délicieux en friture ou en risotto.", 4.6, "Poissons", 40))
        ProduitService.create(Produit("Anguille", "Anguille fraîche, idéale pour un plat traditionnel.", 4.7, "Poissons", 35))
        ProduitService.create(Produit("Dorade", "Dorade fraîche, idéale pour la cuisson en papillote.", 4.8, "Poissons", 45))
        ProduitService.create(Produit("Lottes", "Lottes fraîches, parfaites pour les plats de fruits de mer.", 4.5, "Poissons", 50))
        ProduitService.create(Produit("Poulet entier", "Poulet fermier entier, parfait pour un rôti ou un poulet au four.", 4.8, "Viandes", 40))
        ProduitService.create(Produit("Steak de bœuf", "Steak de bœuf tendre, idéal pour les grillades.", 4.9, "Viandes", 50))
        ProduitService.create(Produit("Côtelettes d'agneau", "Côtelettes d'agneau savoureuses, idéales pour un barbecue.", 4.7, "Viandes", 45))
        ProduitService.create(Produit("Filet de porc", "Filet de porc maigre, parfait pour les plats rôtis.", 4.6, "Viandes", 40))
        ProduitService.create(Produit("Escalopes de dinde", "Escalopes de dinde tendres et légères, parfaites pour les sautés.", 4.5, "Viandes", 50))
        ProduitService.create(Produit("Saucisses de Toulouse", "Saucisses de Toulouse traditionnelles, idéales pour un cassoulet.", 4.6, "Viandes", 60))
        ProduitService.create(Produit("Bacon fumé", "Bacon fumé, parfait pour le petit déjeuner ou les salades.", 4.7, "Viandes", 55))
        ProduitService.create(Produit("Rôti de bœuf", "Rôti de bœuf tendre, idéal pour les repas de famille.", 4.8, "Viandes", 45))
        ProduitService.create(Produit("Jarret de porc", "Jarret de porc, parfait pour un plat mijoté ou une choucroute.", 4.6, "Viandes", 50))
        ProduitService.create(Produit("Poulet rôti", "Poulet rôti prêt à déguster, idéal pour les repas rapides.", 4.7, "Viandes", 60))
        ProduitService.create(Produit("Côtelettes de porc", "Côtelettes de porc savoureuses, idéales pour les grillades.", 4.5, "Viandes", 45))
        ProduitService.create(Produit("Canard entier", "Canard entier, parfait pour un repas de fête ou un plat mijoté.", 4.9, "Viandes", 30))
        ProduitService.create(Produit("Viande hachée de bœuf", "Viande hachée de bœuf, idéale pour les hamburgers ou les pâtes.", 4.7, "Viandes", 55))
        ProduitService.create(Produit("Magret de canard", "Magret de canard tendre, parfait pour un dîner raffiné.", 4.8, "Viandes", 40))
        ProduitService.create(Produit("Gigot d'agneau", "Gigot d'agneau, idéal pour un rôti de famille.", 4.9, "Viandes", 45))
        ProduitService.create(Produit("Côtes de veau", "Côtes de veau, parfaites pour une cuisson grillée ou à la poêle.", 4.6, "Viandes", 50))
        ProduitService.create(Produit("Tendrons de veau", "Tendrons de veau, idéals pour les plats mijotés.", 4.7, "Viandes", 60))
        ProduitService.create(Produit("Filet de canard", "Filet de canard tendre, idéal pour les repas gourmets.", 4.8, "Viandes", 35))
        ProduitService.create(Produit("Poulet fermier", "Poulet fermier élevé en plein air, riche en saveur.", 4.6, "Viandes", 50))
        ProduitService.create(Produit("Lait entier", "Lait entier crémeux, parfait pour les petits déjeuners.", 4.8, "Laitiers", 60))
        ProduitService.create(Produit("Fromage râpé", "Fromage râpé mélangé, idéal pour les gratins ou les pizzas.", 4.7, "Laitiers", 45))
        ProduitService.create(Produit("Yaourt nature", "Yaourt nature crémeux et frais, parfait pour le dessert.", 4.6, "Laitiers", 50))
        ProduitService.create(Produit("Beurre doux", "Beurre doux frais, idéal pour la pâtisserie ou les tartines.", 4.9, "Laitiers", 40))
        ProduitService.create(Produit("Crème fraîche", "Crème fraîche épaisse, idéale pour les sauces ou les desserts.", 4.8, "Laitiers", 45))
        ProduitService.create(Produit("Fromage blanc", "Fromage blanc onctueux, parfait pour un dessert léger.", 4.7, "Laitiers", 55))
        ProduitService.create(Produit("Lait de chèvre", "Lait de chèvre frais, riche en goût et en nutriments.", 4.6, "Laitiers", 50))
        ProduitService.create(Produit("Fromage de chèvre", "Fromage de chèvre crémeux, parfait pour les salades.", 4.8, "Laitiers", 40))
        ProduitService.create(Produit("Lait de soja", "Lait de soja nature, idéal pour les personnes intolérantes au lactose.", 4.5, "Laitiers", 60))
        ProduitService.create(Produit("Fromage Comté", "Fromage Comté affiné, riche et savoureux.", 4.9, "Laitiers", 50))
        ProduitService.create(Produit("Lait de coco", "Lait de coco crémeux, parfait pour les plats exotiques.", 4.7, "Laitiers", 55))
        ProduitService.create(Produit("Fromage mozzarella", "Fromage mozzarella frais, idéal pour les pizzas.", 4.8, "Laitiers", 60))
        ProduitService.create(Produit("Ricotta", "Ricotta fraîche, parfaite pour les pâtes ou les desserts.", 4.6, "Laitiers", 45))
        ProduitService.create(Produit("Cottage cheese", "Fromage cottage frais et léger, parfait pour les salades.", 4.7, "Laitiers", 50))
        ProduitService.create(Produit("Fromage bleu", "Fromage bleu piquant, idéal pour les sauces ou les salades.", 4.5, "Laitiers", 40))
        ProduitService.create(Produit("Crème chantilly", "Crème chantilly légère et sucrée, idéale pour les desserts.", 4.8, "Laitiers", 30))
        ProduitService.create(Produit("Yaourt aux fruits", "Yaourt aux fruits frais, parfait pour les goûters.", 4.7, "Laitiers", 50))
        ProduitService.create(Produit("Lait écrémé", "Lait écrémé léger, parfait pour les boissons et les recettes.", 4.6, "Laitiers", 40))
        ProduitService.create(Produit("Beurre salé", "Beurre salé crémeux, parfait pour assaisonner vos plats.", 4.8, "Laitiers", 35))
        ProduitService.create(Produit("Croissants", "Croissants au beurre frais, parfaits pour le petit-déjeuner.", 4.9, "Pâtisseries", 50))
        ProduitService.create(Produit("Éclairs au chocolat", "Éclairs au chocolat fondants, parfaits pour les gourmands.", 4.8, "Pâtisseries", 45))
        ProduitService.create(Produit("Tarte aux pommes", "Tarte aux pommes maison, délicieuse et sucrée.", 4.7, "Pâtisseries", 40))
        ProduitService.create(Produit("Madeleines", "Madeleines fraîches, légères et parfumées.", 4.6, "Pâtisseries", 50))
        ProduitService.create(Produit("Chocolatines", "Chocolatines au chocolat fondant, idéales pour le goûter.", 4.9, "Pâtisseries", 60))
        ProduitService.create(Produit("Macarons", "Macarons délicats, au goût raffiné et léger.", 4.8, "Pâtisseries", 45))
        ProduitService.create(Produit("Tarte au citron", "Tarte au citron acidulée et sucrée, un délice frais.", 4.7, "Pâtisseries", 40))
        ProduitService.create(Produit("Brioche", "Brioche douce et moelleuse, idéale pour le petit déjeuner.", 4.6, "Pâtisseries", 50))
        ProduitService.create(Produit("Pain au chocolat", "Pain au chocolat délicieux, parfait pour une pause gourmande.", 4.9, "Pâtisseries", 55))
        ProduitService.create(Produit("Muffins", "Muffins aux fruits frais, parfaits pour un goûter rapide.", 4.8, "Pâtisseries", 50))
        ProduitService.create(Produit("Gâteau au yaourt", "Gâteau au yaourt moelleux, facile à réaliser à la maison.", 4.7, "Pâtisseries", 45))
        ProduitService.create(Produit("Cookies", "Cookies aux pépites de chocolat, délicieux et croquants.", 4.6, "Pâtisseries", 60))
        ProduitService.create(Produit("Cakes", "Cakes moelleux, à base de fruits secs et de noix.", 4.7, "Pâtisseries", 40))
        ProduitService.create(Produit("Paris-Brest", "Paris-Brest, une pâtisserie classique à base de pâte à choux et crème pralinée.", 4.8, "Pâtisseries", 35))
        ProduitService.create(Produit("Pain d'épices", "Pain d'épices parfumé, idéal pour les fêtes de fin d'année.", 4.5, "Pâtisseries", 45))
        ProduitService.create(Produit("Tarte aux framboises", "Tarte aux framboises sucrée et délicieuse.", 4.9, "Pâtisseries", 50))
        ProduitService.create(Produit("Chouquettes", "Chouquettes légères et sucrées, parfaites pour une collation.", 4.6, "Pâtisseries", 55))
        ProduitService.create(Produit("Baba au rhum", "Baba au rhum moelleux et imbibé, pour une expérience sucrée.", 4.7, "Pâtisseries", 40))
        ProduitService.create(Produit("Tarte aux fraises", "Tarte aux fraises, sucrée et parfumée, idéale pour l'été.", 4.8, "Pâtisseries", 45))
        ProduitService.create(Produit("Café moulu", "Café moulu riche et aromatique, idéal pour le petit déjeuner.", 4.9, "Boissons", 60))
        ProduitService.create(Produit("Jus d'orange", "Jus d'orange frais, riche en vitamine C.", 4.8, "Boissons", 50))
        ProduitService.create(Produit("Thé vert", "Thé vert bio, parfait pour un moment de détente.", 4.7, "Boissons", 55))
        ProduitService.create(Produit("Eau pétillante", "Eau pétillante rafraîchissante, idéale pour accompagner vos repas.", 4.6, "Boissons", 60))
        ProduitService.create(Produit("Jus de pomme", "Jus de pomme frais, légèrement sucré et rafraîchissant.", 4.8, "Boissons", 50))
        ProduitService.create(Produit("Limonade", "Limonade artisanale, sucrée et bien citronnée.", 4.7, "Boissons", 55))
        ProduitService.create(Produit("Soda cola", "Soda cola classique, une boisson rafraîchissante et pétillante.", 4.5, "Boissons", 65))
        ProduitService.create(Produit("Eau plate", "Eau minérale plate, idéale pour hydrater.", 4.6, "Boissons", 70))
        ProduitService.create(Produit("Vin rouge", "Vin rouge sec, parfait pour accompagner les repas.", 4.9, "Boissons", 40))
        ProduitService.create(Produit("Vin blanc", "Vin blanc sec, léger et fruité.", 4.8, "Boissons", 50))
        ProduitService.create(Produit("Bière artisanale", "Bière artisanale blonde, rafraîchissante et légèrement amère.", 4.7, "Boissons", 60))
        ProduitService.create(Produit("Café en grains", "Café en grains frais, pour un goût intense et une saveur authentique.", 4.8, "Boissons", 45))
        ProduitService.create(Produit("Soda orange", "Soda orange sucré, avec un goût rafraîchissant.", 4.6, "Boissons", 55))
        ProduitService.create(Produit("Lait de vache", "Lait de vache entier, riche et crémeux, idéal pour les boissons chaudes.", 4.9, "Boissons", 50))
        ProduitService.create(Produit("Thé noir", "Thé noir classique, corsé et riche en saveurs.", 4.7, "Boissons", 60))
        ProduitService.create(Produit("Eau de coco", "Eau de coco pure et hydratante, idéale après l'exercice.", 4.6, "Boissons", 55))
        ProduitService.create(Produit("Jus de carotte", "Jus de carotte frais, riche en bêta-carotène et en vitamines.", 4.5, "Boissons", 50))
        ProduitService.create(Produit("Boisson énergisante", "Boisson énergisante, idéale pour booster votre énergie.", 4.8, "Boissons", 45))
        ProduitService.create(Produit("Café glacé", "Café glacé sucré, parfait pour une pause fraîche et revigorante.", 4.7, "Boissons", 60))
        ProduitService.create(Produit("Curcuma", "Curcuma en poudre, idéal pour donner de la couleur et du goût à vos plats.", 4.8, "Épices", 50))
        ProduitService.create(Produit("Poivre noir", "Poivre noir en grains, pour un goût piquant et frais.", 4.9, "Épices", 60))
        ProduitService.create(Produit("Cumin", "Cumin en poudre, pour ajouter une saveur chaude et épicée à vos plats.", 4.7, "Épices", 55))
        ProduitService.create(Produit("Paprika", "Paprika doux, parfait pour les plats mijotés et les viandes grillées.", 4.8, "Épices", 50))
        ProduitService.create(Produit("Curry", "Curry en poudre, un mélange d'épices savoureux pour vos recettes exotiques.", 4.7, "Épices", 60))
        ProduitService.create(Produit("Thym", "Thym séché, idéal pour parfumer les viandes et les légumes.", 4.6, "Épices", 45))
        ProduitService.create(Produit("Cannelle", "Cannelle en poudre, pour une touche sucrée et épicée dans vos desserts.", 4.8, "Épices", 50))
        ProduitService.create(Produit("Gingembre", "Gingembre en poudre, pour ajouter du piquant et de la chaleur à vos plats.", 4.7, "Épices", 55))
        ProduitService.create(Produit("Safran", "Safran, épice précieuse pour une couleur et un parfum uniques.", 4.9, "Épices", 40))
        ProduitService.create(Produit("Piment de Cayenne", "Piment de Cayenne en poudre, pour les amateurs de plats épicés.", 4.6, "Épices", 50))
        ProduitService.create(Produit("Romarin", "Romarin séché, idéal pour les viandes grillées et les rôtis.", 4.7, "Épices", 45))
        ProduitService.create(Produit("Ail en poudre", "Ail en poudre, pour un goût savoureux sans l'épluchage.", 4.8, "Épices", 50))
        ProduitService.create(Produit("Origan", "Origan séché, parfait pour les pizzas et les plats méditerranéens.", 4.7, "Épices", 55))
        ProduitService.create(Produit("Muscade", "Muscade râpée, idéale pour les desserts et les plats crémeux.", 4.6, "Épices", 60))
        ProduitService.create(Produit("Cardamome", "Cardamome en poudre, pour une saveur subtile et parfumée.", 4.8, "Épices", 40))
        ProduitService.create(Produit("Fenouil", "Fenouil en graines, pour parfumer vos plats et infusions.", 4.6, "Épices", 50))
        ProduitService.create(Produit("Sel rose de l'Himalaya", "Sel rose de l'Himalaya, pur et riche en minéraux.", 4.9, "Épices", 55))
        ProduitService.create(Produit("Anis", "Anis en graines, pour parfumer les pâtisseries et les infusions.", 4.7, "Épices", 60))
        ProduitService.create(Produit("Flocons d'avoine", "Flocons d'avoine, parfaits pour un petit déjeuner énergétique.", 4.9, "Céréales", 60))
        ProduitService.create(Produit("Muesli", "Muesli aux fruits et graines, idéal pour une alimentation équilibrée.", 4.8, "Céréales", 50))
        ProduitService.create(Produit("Cornflakes", "Cornflakes croustillants, parfaits pour un petit déjeuner rapide.", 4.7, "Céréales", 55))
        ProduitService.create(Produit("Riz blanc", "Riz blanc basmati, idéal pour accompagner vos repas.", 4.6, "Céréales", 60))
        ProduitService.create(Produit("Quinoa", "Quinoa bio, riche en protéines et idéal pour les plats végétariens.", 4.8, "Céréales", 50))
        ProduitService.create(Produit("Pâtes complètes", "Pâtes complètes, pour un repas sain et nourrissant.", 4.7, "Céréales", 60))
        ProduitService.create(Produit("Semoule", "Semoule fine, idéale pour les couscous et les plats mijotés.", 4.6, "Céréales", 55))
        ProduitService.create(Produit("Pâtes italiennes", "Pâtes italiennes traditionnelles, parfaites pour les sauces.", 4.9, "Céréales", 50))
        ProduitService.create(Produit("Couscous", "Couscous, un classique de la cuisine méditerranéenne.", 4.7, "Céréales", 60))
        ProduitService.create(Produit("Riz basmati", "Riz basmati parfumé, parfait pour accompagner vos plats orientaux.", 4.8, "Céréales", 55))
        ProduitService.create(Produit("Boulgour", "Boulgour fin, idéal pour accompagner vos salades et plats chauds.", 4.7, "Céréales", 50))
        ProduitService.create(Produit("Maïs soufflé", "Maïs soufflé, un snack léger et croustillant.", 4.6, "Céréales", 55))
        ProduitService.create(Produit("Farine de blé", "Farine de blé, idéale pour la pâtisserie et la cuisine.", 4.8, "Céréales", 60))
        ProduitService.create(Produit("Steak haché", "Steak haché, pratique et savoureux pour des repas rapides.", 4.6, "Viandes", 65))
        ProduitService.create(Produit("Foie de veau", "Foie de veau tendre, idéal pour un plat riche en goût.", 4.8, "Viandes", 30))
        ProduitService.create(Produit("Viande de bison", "Viande de bison maigre, idéale pour un plat original et savoureux.", 4.9, "Viandes", 25))
        ProduitService.create(Produit("Steak de thon", "Steak de thon frais, parfait pour les grillades.", 4.7, "Poissons", 40))
        ProduitService.create(Produit("Saumon", "Saumon frais, délicieux pour les plats légers ou en sushi.", 4.8, "Poissons", 45))
        ProduitService.create(Produit("Truite", "Truite fraîche, idéale pour une cuisson en papillote.", 4.6, "Poissons", 50))
        ProduitService.create(Produit("Cabillaud", "Cabillaud, parfait pour un plat léger et savoureux.", 4.7, "Poissons", 55))
        ProduitService.create(Produit("Maquereau", "Maquereau frais, idéal pour les grillades et les salades.", 4.5, "Poissons", 60))
        ProduitService.create(Produit("Sole", "Sole, poisson délicat et léger, parfait pour un repas raffiné.", 4.9, "Poissons", 30))
        ProduitService.create(Produit("Haddock", "Haddock fumé, délicieux dans des plats de poisson ou des salades.", 4.8, "Poissons", 35))
        ProduitService.create(Produit("Crevettes", "Crevettes fraîches, idéales pour les plats exotiques ou les cocktails.", 4.7, "Poissons", 50))
        ProduitService.create(Produit("Homard", "Homard frais, un luxe pour vos repas de fête.", 4.9, "Poissons", 25))
        ProduitService.create(Produit("Huîtres", "Huîtres fraîches, idéales pour un repas de fruits de mer.", 4.8, "Poissons", 40))
        ProduitService.create(Produit("Palourdes", "Palourdes fraîches, parfaites pour des pâtes ou un risotto.", 4.6, "Poissons", 45))
        ProduitService.create(Produit("Moules", "Moules fraîches, idéales pour un plat à la marinière.", 4.7, "Poissons", 60))
        ProduitService.create(Produit("Thon en conserve", "Thon en conserve, pratique et savoureux pour des salades.", 4.5, "Poissons", 65))
        ProduitService.create(Produit("Escargots de Bourgogne", "Escargots de Bourgogne, traditionnels et savoureux avec du beurre à l'ail.", 4.9, "Poissons", 30))
        ProduitService.create(Produit("Caviar", "Caviar, une spécialité de luxe pour vos occasions spéciales.", 4.9, "Poissons", 20))
        ProduitService.create(Produit("Pâtes fraîches", "Pâtes fraîches, idéales pour toutes vos recettes italiennes.", 4.8, "Pâtes", 50))
        ProduitService.create(Produit("Spaghetti", "Spaghetti traditionnels, parfaits pour accompagner vos sauces.", 4.7, "Pâtes", 60))
        ProduitService.create(Produit("Ravioli", "Ravioli fourrés à la viande, pour un repas rapide et savoureux.", 4.6, "Pâtes", 55))
        ProduitService.create(Produit("Penne rigate", "Penne rigate, idéales pour les sauces épaisses.", 4.7, "Pâtes", 40))
        ProduitService.create(Produit("Tagliatelles", "Tagliatelles, parfaites pour accompagner des sauces crémeuses.", 4.8, "Pâtes", 45))
        ProduitService.create(Produit("Fusilli", "Fusilli, idéal pour les salades de pâtes.", 4.6, "Pâtes", 50))
        ProduitService.create(Produit("Lasagnes", "Lasagnes à la viande, pour un repas copieux et convivial.", 4.8, "Pâtes", 30))
        ProduitService.create(Produit("Conchiglie", "Conchiglie, coques parfaites pour les sauces aux légumes.", 4.7, "Pâtes", 40))
        ProduitService.create(Produit("Pâtes à la farine de blé complet", "Pâtes complètes, idéales pour un repas équilibré.", 4.5, "Pâtes", 60))
        ProduitService.create(Produit("Pâtes sans gluten", "Pâtes sans gluten, idéales pour les personnes intolérantes.", 4.6, "Pâtes", 50))
        ProduitService.create(Produit("Sauce tomate", "Sauce tomate épicée, parfaite pour accompagner vos pâtes.", 4.7, "Sauces", 55))
        ProduitService.create(Produit("Sauce bolognaise", "Sauce bolognaise, savoureuse et idéale pour vos pâtes.", 4.8, "Sauces", 50))
        ProduitService.create(Produit("Pesto", "Pesto au basilic, idéal pour assaisonner vos pâtes et plats méditerranéens.", 4.9, "Sauces", 30))
        ProduitService.create(Produit("Sauce au fromage", "Sauce crémeuse au fromage, parfaite pour les pâtes et les gratins.", 4.7, "Sauces", 40))
        ProduitService.create(Produit("Sauce Alfredo", "Sauce Alfredo crémeuse, parfaite pour vos pâtes ou viandes.", 4.6, "Sauces", 45))
        ProduitService.create(Produit("Mayonnaise", "Mayonnaise maison, crémeuse et savoureuse.", 4.5, "Sauces", 60))
        ProduitService.create(Produit("Moutarde", "Moutarde de Dijon, piquante et parfaite pour les viandes.", 4.7, "Sauces", 55))
        ProduitService.create(Produit("Ketchup", "Ketchup sucré et épicé, parfait pour accompagner vos frites.", 4.6, "Sauces", 65))
        ProduitService.create(Produit("Vinaigrette", "Vinaigrette maison, parfaite pour vos salades fraîches.", 4.7, "Sauces", 50))
        ProduitService.create(Produit("Sauce soja", "Sauce soja, idéale pour les plats asiatiques.", 4.8, "Sauces", 40))
        ProduitService.create(Produit("Miel", "Miel pur, idéal pour sucrer vos boissons ou vos desserts.", 4.9, "Miels", 30))
        ProduitService.create(Produit("Miel de lavande", "Miel de lavande, doux et parfumé, idéal pour les tisanes.", 4.8, "Miels", 35))
        ProduitService.create(Produit("Miel d'acacia", "Miel d'acacia, léger et délicat, parfait pour vos recettes sucrées.", 4.7, "Miels", 40))
        ProduitService.create(Produit("Miel de thym", "Miel de thym, aux arômes puissants et savoureux.", 4.6, "Miels", 45))
        ProduitService.create(Produit("Miel de châtaignier", "Miel de châtaignier, intense et idéal pour les fromages.", 4.9, "Miels", 25))
        ProduitService.create(Produit("Miel de montagne", "Miel de montagne, complexe et riche en saveurs.", 4.7, "Miels", 30))
        ProduitService.create(Produit("Céréales", "Céréales complètes, parfaites pour un petit-déjeuner équilibré.", 4.8, "Céréales", 50))
        ProduitService.create(Produit("Flocons d'avoine", "Flocons d'avoine, parfaits pour un porridge sain.", 4.7, "Céréales", 60))
        ProduitService.create(Produit("Muesli", "Muesli aux fruits et noix, un petit-déjeuner complet et savoureux.", 4.6, "Céréales", 55))
        ProduitService.create(Produit("Cornflakes", "Cornflakes croustillants, idéals pour un petit-déjeuner rapide.", 4.5, "Céréales", 65))
        ProduitService.create(Produit("Granola", "Granola maison, un mélange de céréales, fruits et noix.", 4.7, "Céréales", 40))
        ProduitService.create(Produit("Barres de céréales", "Barres de céréales aux fruits, idéales pour un snack rapide et sain.", 4.6, "Céréales", 50))


        CategoryService.create(Category("Fruits"))
        CategoryService.create(Category("Légumes"))
        CategoryService.create(Category("Poissons"))
        CategoryService.create(Category("Viandes"))
        CategoryService.create(Category("Laitiers"))
        CategoryService.create(Category("Pâtisseries"))
        CategoryService.create(Category("Boissons"))
        CategoryService.create(Category("Épices"))
        CategoryService.create(Category("Céréales"))
        CategoryService.create(Category("Snacks"))

        val listProduitPromotions= listOf(
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10),
            Produit("produit","hgdhed",45.0,"udgj",45,10)
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerPlusVente.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)


        adapter = adapterCartProduit(listProduitPromotions)
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
                    // Quand nous arrivons au dernier élément, faire défiler au premier élément
                    recyclerView.smoothScrollToPosition(0)
                    currentPosition = 1
                } else {
                    recyclerView.smoothScrollToPosition(currentPosition)
                    currentPosition++
                }

                handler?.postDelayed(this, 2000)
            }
        }
        handler?.postDelayed(runnable!!, 3000)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler?.removeCallbacks(runnable!!)
    }
}