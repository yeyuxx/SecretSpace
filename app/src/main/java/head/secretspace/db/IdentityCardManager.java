package head.secretspace.db;

import android.content.Context;

import head.secretspace.entity.AddIdentityCard;

/**
 * Created by HEAD on 2017/7/26.
 */

public class IdentityCardManager extends BaseDao<AddIdentityCard> {
    public IdentityCardManager(Context context) {
        super(context);
    }
}
