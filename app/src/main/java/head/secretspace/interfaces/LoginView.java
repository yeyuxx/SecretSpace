package head.secretspace.interfaces;


import head.secretspace.bean.User;

/**
 * Created on 17/7/5 09:52
 */

public interface LoginView extends BmobView {
    void  loginSuccess(User user, String password);
}
