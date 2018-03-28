package head.secretspace.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import head.secretspace.utils.PastePlate;

/**
 * Created by HEAD on 2017/11/26.
 */

public class MyTimeBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PastePlate.copy("", context);

    }
}
