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
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.clock_text)
    TextView mClockText;

    LocalBroadcastManager localBroadcastManager;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            double result = intent.getDoubleExtra("extra.TIME", 0);
            mClockText.setText(String.valueOf(result));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction("action.TIMER_RESULT");
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        localBroadcastManager.unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }

    @OnClick({R.id.button_start, R.id.button_stop})
    public void getButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.button_start:

                break;
            case R.id.button_stop:

                break;
        }
    }

    /**
     * Metoda do wystartowania alarmu.
     */
    private void startAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = createServiceIntent();
        PendingIntent pendingIntent = null; // TODO
        long startTime = 0; //TODO
        long interval = 0; //TODO

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, interval, pendingIntent);
    }

    /**
     * Metoda do zatrzymania alarmu.
     */
    private void stopAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent intent = createServiceIntent();
        PendingIntent pendingIntent = null; //TODO
        alarmManager.cancel(pendingIntent);
    }

    /**
     * Stwórz intent do uruchomienia serwisu.
     */
    private Intent createServiceIntent() {
        Intent intent = new Intent(this, SimpleIntentService.class);
        intent.setAction("action.GET_TIME");

        return intent;
    }
}
