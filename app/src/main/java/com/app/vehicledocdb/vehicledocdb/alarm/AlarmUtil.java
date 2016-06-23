package com.app.vehicledocdb.vehicledocdb.alarm;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.app.vehicledocdb.vehicledocdb.MainActivity;
import com.app.vehicledocdb.vehicledocdb.R;
import com.app.vehicledocdb.vehicledocdb.model.Requirement;

import java.util.Calendar;

public class AlarmUtil extends BroadcastReceiver {
    public AlarmUtil() {
    }


    public static void setAlarm(Context context, Requirement requirement) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = createPendingIntent(context, requirement);

        //For testing purposes it's going to use calendar instead requirement.getEndDate value
        Calendar calendar = Calendar.getInstance();
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() + 3000, pendingIntent);
    }

    public static void cancelAlarm(Context context, Requirement requirement) {
        PendingIntent pIntent = createPendingIntent(context, requirement);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pIntent);
    }

    public static PendingIntent createPendingIntent(Context context, Requirement requirement) {
        Intent alarmIntent = new Intent(context, AlarmUtil.class);
        return PendingIntent.getBroadcast(context, requirement.getId().intValue(), alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.wtf("ALARM", "alarma recibida");

        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        notificationIntent.putExtras(intent);

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);


        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("REQUIREMENT ALARM")
                .setContentText("Texto a mostrar")
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});
        notification.build();

        notificationManager.notify(0, notification.build());
    }
}
