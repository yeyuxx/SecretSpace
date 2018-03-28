package head.secretspace.ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import head.secretspace.utils.FontCustom;

/**
 * Created by HEAD on 2017/10/7.
 */

public class MyTextView extends TextView {
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    /**
     * 初始化字体
     * @param context
     */
    private void init(Context context) {
        //设置字体样式
        setTypeface(FontCustom.setFont(context));
    }
}
