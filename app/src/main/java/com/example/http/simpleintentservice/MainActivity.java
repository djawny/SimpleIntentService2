package com.example.http.simpleintentservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO odbierz odpowiedź i wyświetl wynik na ekranie
            // double result = intent.getDoubleExtra("NAZWA_POLA", 0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        IntentFilter filter = new IntentFilter();
        // filter.addAction("NAZWA_AKCJI"); TODO: wstaw nazwę akcji którą chcesz odebrać

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    /** Metoda do wystartowania alarmu. */
    private void startAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = createServiceIntent();
        PendingIntent pendingIntent = null; // TODO
        long startTime = 0; //TODO
        long interval = 0; //TODO

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, interval, pendingIntent);
    }

    /** Metoda do zatrzymania alarmu. */
    private void stopAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = createServiceIntent();
        PendingIntent pendingIntent = null; //TODO
        alarmManager.cancel(pendingIntent);
    }

    /** Stwórz intent do uruchomienia serwisu. */
    private Intent createServiceIntent() {
        Intent intent = new Intent(this, SimpleIntentService.class);
        intent.setAction("action.GET_TIME");

        return intent;
    }
}
