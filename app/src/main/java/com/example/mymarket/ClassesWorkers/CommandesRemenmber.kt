package com.example.mymarket.ClassesWorkers

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.mymarket.R


class CommandesRemenmber (context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {
    override fun doWork(): Result {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("commandeLivre", "commandeLivre", NotificationManager.IMPORTANCE_HIGH)
            val manager = applicationContext.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
        val notification = NotificationCompat.Builder(applicationContext, "commandeLivre")
            .setContentTitle("Gestion de commande worker")
            .setContentText("commande Livre")
            .setSmallIcon(R.drawable.livre)
            .build()
        if (ActivityCompat.checkSelfPermission(applicationContext,Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return Result.failure()
        }
        NotificationManagerCompat.from(applicationContext).notify(1, notification)
        return Result.success()
    }
}