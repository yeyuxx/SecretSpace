package head.secretspace.db;

import android.content.Context;

import head.secretspace.entity.AddBankCard;

/**
 * Created by HEAD on 2017/7/10.
 */

public class BankCardManager extends BaseDao<AddBankCard> {

    public BankCardManager(Context context) {
        super(context);
    }
}
