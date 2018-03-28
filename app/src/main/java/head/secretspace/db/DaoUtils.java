package head.secretspace.db;

import android.content.Context;

import head.secretspace.entity.AddBankCard;

/**
 * Created by HEAD on 2017/6/16.
 */

public class DaoUtils {
    private  static SecretSpaceManager secretSpaceManager;
    private static SecretSpacePasswordManager secretSpacePasswordManager;
    private static AddPwItemManager addPwItemManager;
    private static AddMianValueManager addMainValueManager;
    private static BankCardManager bankCardManager;
    private static IdentityCardManager identityCardManager;

    public  static Context context;

    public static void init(Context context){
        DaoUtils.context = context.getApplicationContext();
    }
    /**
     * 单列模式获取StudentManager对象
     * @return
     */
    public static SecretSpaceManager getStudentInstance(){
        if (secretSpaceManager == null) {
            secretSpaceManager = new SecretSpaceManager(context);
        }
        return secretSpaceManager;
    }

    public static SecretSpacePasswordManager getSecretSpacePasswordManager(){
        if (secretSpacePasswordManager==null){
            secretSpacePasswordManager=new SecretSpacePasswordManager(context);
        }
        return secretSpacePasswordManager;
    }

    public static AddPwItemManager getAddPwItemManager(){
        if (addPwItemManager==null){
            addPwItemManager=new AddPwItemManager(context);
        }
        return addPwItemManager;
    }


    public static AddMianValueManager getAddMainValueManager(){
        if (addMainValueManager==null){
            addMainValueManager=new AddMianValueManager(context);
        }
        return addMainValueManager;
    }

    public static BankCardManager getBankCardManager(){
        if (bankCardManager==null){
            bankCardManager=new BankCardManager(context);
        }
        return bankCardManager;
    }

    public static IdentityCardManager getIdentityCardManager(){
        if (identityCardManager==null){
            identityCardManager=new IdentityCardManager(context);
        }
        return identityCardManager;
    }
}
