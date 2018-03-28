package head.secretspace.db;

import android.content.Context;

import head.secretspace.entity.AddPwItem;
import head.secretspace.entity.Secretspace;

/**
 * Created by HEAD on 2017/6/20.
 */

public class AddPwItemManager extends BaseDao<AddPwItem> {
    public AddPwItemManager(Context context) {
        super(context);
    }


    private long getID(AddPwItem addPwItem){

        return daoSession.getAddPwItemDao().getKey(addPwItem);
    }
}
