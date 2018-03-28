package head.secretspace.config;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import head.secretspace.db.DaoManager;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.utils.Constant;

/**
 * Created by HEAD on 2017/7/3.
 */

public class MyApplication extends Application {
    private List<AddMainValue> addMainValues=new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,"d541d1d1969533c4115a37187a54b8b0");
        File file=new File(Environment.getExternalStorageDirectory()+File.separator+"Secretspace");
        file.mkdir();

        DaoUtils.init(this);
        if (DaoUtils.getAddMainValueManager().QueryAll(AddMainValue.class).size()<9) {
            for (int i = 0; i < Constant.PROJECTNAME.length; i++) {
                AddMainValue addMainValue = new AddMainValue();
                addMainValue.setSqlName(Constant.PROJECTNAME[i]);
                addMainValue.setValue(0);
                addMainValues.add(addMainValue);
            }
        }
                    DaoUtils.getAddMainValueManager().insertMultObject(addMainValues);
            }



}
