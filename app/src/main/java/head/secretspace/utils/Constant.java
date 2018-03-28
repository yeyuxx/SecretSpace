package head.secretspace.utils;

import android.graphics.drawable.Drawable;
import android.os.Environment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import head.secretspace.R;

/**
 * Created by HEAD on 2017/5/31.
 */

public class Constant {
    public static final String ALL_NUM_SYMBOL_LETTER = "0123456789,<.>/?:;'|[{]}`~!@#$%^&*()_-=+abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALl_NUM_SYMBOL = "0123456789,<.>/?:;'|[{]}`~!@#$%^&*()_-=+";
    public static final String ALL_NUN_LETTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_SYMBOL_LETTER = ",<.>/?:;'|[{]}`~!@#$%^&*()_-=+abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ALL_NUM = "0123456789";
    public static final String ALL_SYMBOL = ",<.>/?:;'|[{]}`~!@#$%^&*()_-=+";
    public static final String ALL_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String[] PROJECTNAME = new String[]{"密码", "银行账户", "身份证", "电子邮箱", "信用卡",
            "数据库", "服务器", "路由器", "驾照", "护照"};
    public static int[] PROJECTIMG = new int[]{R.drawable.keyone, R.drawable.yinhnagka, R.drawable.shengfenzheng,
            R.drawable.email, R.drawable.xinyongka, R.drawable.sql,
            R.drawable.fuwuqi, R.drawable.luyouqi, R.drawable.jiazhao, R.drawable.passporteasyicon};
    public static final String PATH1 = "data/data/head.secretspace/databases/SecretspaceDao.db";
    public static final String PATH2 =  Environment.getExternalStorageDirectory() +"/Secretspace/SecretspaceDao.db";
}