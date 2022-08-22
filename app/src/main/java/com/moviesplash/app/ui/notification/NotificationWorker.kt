package com.moviesplash.app.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.moviesplash.app.HomeActivity
import com.moviesplash.app.R
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.Constants.NOTIFICATION_CHANNEL_ID
import com.moviesplash.app.core.utils.Constants.NOTIF_UNIQUE_WORK
//
//class NotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {
//
//    private val movieTitle = inputData.getString(Constants.movieTitle)
//    private val movieId = inputData.getInt(Constants.movieId, 0)
//
//    private fun getPendingIntent(): PendingIntent? {
//        val intent = Intent(applicationContext, HomeActivity::class.java)
//        return TaskStackBuilder.create(applicationContext).run {
//            addNextIntentWithParentStack(intent)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                getPendingIntent(0,   PendingIntent.FLAG_IMMUTABLE)
//            } else {
//                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//            }
//        }
//    }
//
//    override fun doWork(): Result {
//        val prefManager =
//            androidx.preference.PreferenceManager.getDefaultSharedPreferences(applicationContext)
//        val shouldNotify =
//            prefManager.getBoolean(applicationContext.getString(R.string.pref_key_notify), false)
//        if (shouldNotify) {
//            showNotification()
//        }
//        return Result.success()
//    }
//
//    private fun showNotification() {
//        val notificationManager =
//            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notification: NotificationCompat.Builder =
//            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_notifications)
//                .setContentIntent(getPendingIntent())
//                .setContentTitle(movieTitle)
//                .setContentText(applicationContext.getString(R.string.notify_content))
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIF_UNIQUE_WORK, NotificationManager.IMPORTANCE_HIGH)
//            notification.setChannelId(NOTIFICATION_CHANNEL_ID)
//            notificationManager.createNotificationChannel(channel)
//        }
//        notificationManager.notify(movieId, notification.build())
//    }
//}