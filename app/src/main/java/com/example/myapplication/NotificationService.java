package com.example.myapplication;

import static java.security.AccessController.getContext;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {

    private final String channelID = "waterNotificationChannel";
    private Handler handler = new Handler();
    private Runnable notificationRunnable;

    private boolean isNotificationScheduled = false;
    private Timer timer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals("start_notification") && !isNotificationScheduled) {
                startNotificationScheduler();
                isNotificationScheduled = true;
            } else if (intent.getAction().equals("stop_notification")) {
                stopNotificationScheduler();
                isNotificationScheduled = false;
                // Pare o serviço se a ação for para parar as notificações
                stopSelf();
            }
        }

        return START_STICKY;
    }

    private void startNotificationScheduler() {
        notificationRunnable = new Runnable() {
            @Override
            public void run() {
                showNotification();
                handler.postDelayed(this, 10000);
            }
        };
        handler.postDelayed(notificationRunnable, 10000);
    }

    private void stopNotificationScheduler() {
        if (notificationRunnable != null) {
            handler.removeCallbacks(notificationRunnable);
        }
    }

    public void showNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Aguinha Já! Hidrate-se")
                .setContentText("Sua Saúde Importa!!")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("É hora de se hidratar. Não importa o quão ocupado você esteja, tire um momento agora para" +
                                " beber um copo de água. Sua saúde e bem-estar agradecem. A água é a fonte da vida, vital para a " +
                                "digestão, circulação, absorção de nutrientes e manutenção da temperatura corporal adequada."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel(channelID, "description", importance);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0, builder.build());
    }
}
