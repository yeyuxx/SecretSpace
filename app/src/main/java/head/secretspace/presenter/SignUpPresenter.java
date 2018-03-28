package head.secretspace.presenter;


import head.secretspace.bean.User;
import head.secretspace.interfaces.SignUpView;
import head.secretspace.model.BmobModel;
import rx.functions.Action1;

/**
 * Created on 17/7/5 11:06
 */

public class SignUpPresenter {
    private SignUpView mSignUpView;

    private BmobModel mBmobModel;

    public SignUpPresenter(SignUpView oneKeyView) {
        mSignUpView = oneKeyView;
        mBmobModel = new BmobModel();
    }

    public void oneKeyRegister(final String account, final String password) {
        mSignUpView.showDialog();
        mBmobModel.oneKeyRegister(account, password)
                .subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {

                        mSignUpView.hideDialog();
                        mSignUpView.registerSuccess(account, password);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mSignUpView.hideDialog();
                        mSignUpView.showError(throwable);
                    }
                });
    }
}
