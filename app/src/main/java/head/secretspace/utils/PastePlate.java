package head.secretspace.utils;

import android.content.ClipboardManager;
import android.content.Context;

/**
 * Created by HEAD on 2017/10/18.
 */

public class PastePlate {
    public static void copy(String content, Context context){
        ClipboardManager cmb= (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content);
    }
}
