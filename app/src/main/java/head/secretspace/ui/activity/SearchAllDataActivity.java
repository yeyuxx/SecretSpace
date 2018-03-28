package head.secretspace.ui.activity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.utils.Constant;

/**
 * Created by HEAD on 2017/8/15.
 */

public class SearchAllDataActivity extends Activity {
    private SQLiteDatabase database= SQLiteDatabase.openOrCreateDatabase( Constant.PATH1, null);

    Cursor cursor1 = database.rawQuery("select * from ADD_MAIN_VALUE", null);
    Cursor cursor2 = database.rawQuery("select * from ADD_PW_ITEM", null);
    Cursor cursor3 = database.rawQuery("select * from ADD_BANK_CARD", null);



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all_data);
        DaoUtils.init(this);

    }

}
