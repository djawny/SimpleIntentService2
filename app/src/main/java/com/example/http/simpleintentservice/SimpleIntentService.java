package com.example.http.simpleintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SimpleIntentService extends IntentService {

    public SimpleIntentService() {
        super("SimpleIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getAction();

            // TODO ustaw pasującą nazwę akcji
            if ("NAZWA_AKCJI".equals(action)) {

                // TODO wykonaj operację i zwróć wynik
            }
        }
    }

    /** Metoda do wysyłania odpowiedzi. */
    private void broadcastResult(String time) {
        Intent intent = new Intent();
        intent.setAction("action.TIMER_RESULT");
        intent.putExtra("extra.VALUE", time);

        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.sendBroadcast(intent);
    }

    /** Metoda do pobierania czasu. */
    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return format.format(new Date(System.currentTimeMillis()));
    }
}
