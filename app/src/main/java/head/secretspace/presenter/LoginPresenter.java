package head.secretspace.presenter;



import head.secretspace.bean.User;
import head.secretspace.interfaces.LoginView;
import head.secretspace.model.BmobModel;
import rx.functions.Action1;

/**
 * Created on 17/7/5 09:22
 */

public class LoginPresenter {


    private LoginView mLoginView;
    private BmobModel mBmobModel;


    public LoginPresenter(LoginView loginView) {
        mLoginView = loginView;
        mBmobModel = new BmobModel();
    }

    public void login(String account, final String password) {
        mLoginView.showDialog();
        mBmobModel.login(account, password)
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        mLoginView.hideDialog();
                        mLoginView.loginSuccess(user,password);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mLoginView.hideDialog();
                        mLoginView.showError(throwable);
                    }
                });
    }
}
