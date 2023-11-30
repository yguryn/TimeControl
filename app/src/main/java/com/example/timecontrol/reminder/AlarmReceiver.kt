package com.example.timecontrol.reminder

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.timecontrol.ui.main.MainActivity
import com.example.timecontrol.R
import java.util.Calendar
import javax.inject.Inject

class AlarmReceiver : BroadcastReceiver() {

    @Inject
    lateinit var remindersManager: RemindersManager

    override fun onReceive(context: Context, intent: Intent) {
        if (isWeekend()) return

        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            channelId = context.getString(R.string.reminders_notification_channel_id)
        )

        remindersManager.startReminder()
    }

    private fun isWeekend(): Boolean {
        val today = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        return today == Calendar.SATURDAY || today == Calendar.SUNDAY
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String,
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java).apply {
        putExtra("opened_from_notification", true)
    }

    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_REQUEST_CODE,
        contentIntent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(applicationContext, channelId).apply {
        setContentTitle("Time to work")
        setContentText("Tap to set some time")
        setSmallIcon(R.drawable.baseline_home_24)
        setContentIntent(pendingIntent)
        setAutoCancel(true)
    }

    notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1
const val NOTIFICATION_REQUEST_CODE = 1
