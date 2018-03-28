package head.secretspace.ui.activity.login;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.interfaces.SignUpView;
import head.secretspace.presenter.SignUpPresenter;

/**
 * Created by HEAD on 2017/11/26.
 */

public class NetRegisterActivity extends AppCompatActivity implements SignUpView{


    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.et_repeatpassword)
    EditText etRepeatpassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv_add)
    CardView cvAdd;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_register);
        ButterKnife.inject(this);
        mSignUpPresenter = new SignUpPresenter(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRevealClose();
            }
        });
    }

    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                cvAdd.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }


        });
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth() / 2, 0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.plus);
                NetRegisterActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    @Override
    public void onBackPressed() {
        animateRevealClose();
    }


    @OnClick(R.id.bt_go)
    public void onViewClicked() {
        String name=etUsername.getText().toString();
        String password=etPassword.getText().toString();
        String repeatname=etRepeatpassword.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "账户不能为空", Toast.LENGTH_SHORT).show();
            return;

        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;

        }
        else if (TextUtils.isEmpty(repeatname)){
            Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
            return;

        }

        else if (password.length()<6){
            Toast.makeText(this, "密码最短为6位", Toast.LENGTH_SHORT).show();
            return;

        }else if (!password.equals(repeatname)){
            Toast.makeText(this, "两次密码不一样", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.equals(repeatname)&&password.length()>5){
            mSignUpPresenter.oneKeyRegister(name, password);
        }

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "注册失败"+throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registerSuccess(String account, String password) {
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        animateRevealClose();
    }
}
