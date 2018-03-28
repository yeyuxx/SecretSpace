package head.secretspace.db;

import android.content.Context;

import head.secretspace.entity.Secretspace;
import head.secretspace.entity.SecretspaceUser;

/**
 * Created by HEAD on 2017/6/16.
 */

public class SecretSpacePasswordManager extends BaseDao<SecretspaceUser> {

    public SecretSpacePasswordManager(Context context) {
        super(context);
    }


}
