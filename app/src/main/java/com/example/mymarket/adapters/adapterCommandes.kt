package com.example.mymarket.adapters

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings.Secure.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.mymarket.ClassesWorkers.CommandesRemenmber
import com.example.mymarket.DATA.Commandes
import com.example.mymarket.DATA.Produit
import com.example.mymarket.Fragements.DetailsHisoriqueCommandes
import com.example.mymarket.R
import com.example.mymarket.Service.PanierService
import com.example.mymarket.Service.ProduitService
import java.util.concurrent.TimeUnit

class adapterCommandes(
    private var CommandesList: List<Commandes>,
    val fragement:FragmentManager,
) : RecyclerView.Adapter<adapterCommandes.ProductViewHolder>() {
    var refreshRunnable: Runnable? = null
    var handler: Handler? = null

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemid : LinearLayout = itemView.findViewById(R.id.itemcmd)
        val num: TextView = itemView.findViewById(R.id.commande_num)
        val status: TextView = itemView.findViewById(R.id.commande_status)
        val date = itemView.findViewById<TextView>(R.id.commande_date)
        val prix: TextView = itemView.findViewById(R.id.commande_prix_total)
        val TotalCategory: TextView = itemView.findViewById(R.id.commande_total_category)
        val iconCommande = itemView.findViewById<ImageView>(R.id.iconCommande)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_commandes, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val commande = CommandesList[position]
        var b=true

        holder.num.text = "N°${commande.Num}"
        holder.status.text ="${commande.status}"
        holder.date.text = "${commande.dateCmd}"
        holder.prix.text = String.format("%.2f %s", commande.prixTotal,holder.itemView.context.getString(R.string.DH))
        holder.TotalCategory.text = "articles(${commande.TotalCategory})"
        startAutoRefresh(10000L)
        if(commande.status == "En cours"){
            holder.iconCommande.setImageResource(R.drawable.en_cours)
            holder.itemid.setBackgroundResource(R.drawable.item_command_encours)
            holder.status.setTextColor(Color.parseColor("#7A6A44"))
        }else if(commande.status == "En attente"){
            holder.iconCommande.setImageResource(R.drawable.en_attente)
            holder.itemid.setBackgroundResource(R.drawable.item_commande_enattent)
            holder.status.setTextColor(Color.parseColor("#7A4F33"))
            if(commande.showNotificationEnAttente) {
                showNotification(
                    holder.itemView.context,
                    "EnAttente",
                    "Commande Numero ${commande.Num} en attente de  livre",
                    R.drawable.en_attente
                )
                commande.showNotificationEnAttente = false
            }
        }else{
            holder.iconCommande.setImageResource(R.drawable.accepte)
            holder.itemid.setBackgroundResource(R.drawable.item_commandes_bg)
            holder.status.setTextColor(Color.parseColor("#389975"))
            if(commande.showNotificationLivre) {
                showNotification(
                    holder.itemView.context,
                    "Livre",
                    "Commande Numero ${commande.Num} est Bien livre",
                    R.drawable.accepte
                )
                commande.showNotificationLivre = false
            }
        }

        holder.itemView.setOnClickListener{
            val fragmentDestination = DetailsHisoriqueCommandes()
            val bundle = Bundle()
            bundle.putInt("num",commande.Num)
            fragmentDestination.arguments = bundle
            fragmentDestination.show(fragement, fragmentDestination.tag)

        }
        val workManager = WorkManager.getInstance(holder.itemView.context)
        val workRequest = PeriodicWorkRequest.Builder(CommandesRemenmber::class.java,commande.ville.timeLaivrison, TimeUnit.MINUTES).build()
        if (commande.showNotificationWorker) {
            workManager.enqueue(workRequest)
            commande.showNotificationWorker = false
        }


    }
    fun startAutoRefresh(refreshInterval: Long) {
        handler = Handler(Looper.getMainLooper())
        refreshRunnable = object : Runnable {
            override fun run() {
                CommandesList = CommandesList
                notifyDataSetChanged()
                handler?.postDelayed(this, refreshInterval)
            }
        }
        handler?.postDelayed(refreshRunnable!!, refreshInterval)
    }
    fun showNotification(context: Context,id:String,description:String,icon:Int) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    id,
                    "Alert Notifications",
                    NotificationManager.IMPORTANCE_HIGH
                )
                val manager = context.getSystemService(NotificationManager::class.java)
                manager.createNotificationChannel(channel)
            }

            val notification = NotificationCompat.Builder(context, id)
                .setSmallIcon(icon)
                .setContentTitle("Gestion de Commandes")
                .setContentText(description)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .build()
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        NotificationManagerCompat.from(context).notify(1, notification)
        }



    override fun getItemCount(): Int {
        return CommandesList.size
    }
}
