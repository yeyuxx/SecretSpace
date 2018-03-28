package head.secretspace.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HEAD on 2017/11/26.
 */

public class DelService extends Service {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        preferences =getSharedPreferences("ZW", Context.MODE_PRIVATE);
        int i=preferences.getInt("Time",100);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Intent timeIntent = new Intent();
                timeIntent.setAction("TIME_CHANGED_ACTION");//自定义Action
                sendBroadcast(timeIntent);
            }
        },1000,i);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
