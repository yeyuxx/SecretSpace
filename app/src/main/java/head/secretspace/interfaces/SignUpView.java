package head.secretspace.interfaces;

/**
 * Created on 17/7/5 14:17
 */

public interface SignUpView extends BmobView{
    void registerSuccess(String account, String password);
}
