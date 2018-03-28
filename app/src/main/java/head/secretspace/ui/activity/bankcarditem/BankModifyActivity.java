package head.secretspace.ui.activity.bankcarditem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import head.secretspace.R;
import head.secretspace.animation.Rotate3dAnimation;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.ui.activity.base.SetBaseActivity;

/**
 * Created by HEAD on 2017/10/18.
 */

public class BankModifyActivity extends SetBaseActivity implements View.OnTouchListener {
    private EditText ed_bank_modify_account;
    private EditText ed_bank_modify_registrationDate;
    private EditText ed_bank_modify_cardholderName;
    private EditText ed_bank_modify_bankCardTelPhoneNum;
    private EditText ed_bank_modify_bankCardRemarks;

    private TextView tv_bank_modify_data;
    private TextView tv_bank_modify_account;
    private TextView tv_bank_modify_name;

    private CardView ly_bank_modify_two_bg_bank;
    private CardView ly_bank_modify_one_bg_bank;

    private RelativeLayout rl_bank_modify_3d_card;

    private int centerX;
    private int centerY;
    private int depthZ = 400;
    private int duration = 600;
    private Rotate3dAnimation openAnimation;
    private Rotate3dAnimation closeAnimation;

    private int isClose=1;



    @Override
    protected void getBackLogin() {

    }

    @Override
    protected void getFinish() {
        finish();
    }

    @Override
    protected void onSaveData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new java.util.Date());
        AddBankCard addBankCard=new AddBankCard();
        addBankCard.setId(BankCopyActivity.BANKCOPYID);
        addBankCard.setBankAccount(ed_bank_modify_account.getText().toString());
        addBankCard.setCardholderName(ed_bank_modify_cardholderName.getText().toString());
        addBankCard.setRegistrationDate(ed_bank_modify_registrationDate.getText().toString());
        addBankCard.setTelPhoneNum(ed_bank_modify_bankCardTelPhoneNum.getText().toString());
        addBankCard.setRemarks(ed_bank_modify_bankCardRemarks.getText().toString());
        addBankCard.setIssuingBank(BankCardActivity.getIssuingBank().get(BankTypeActivity.TYPE));
        addBankCard.setTime(date);
        addBankCard.setLike(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().load(BankCopyActivity.BANKCOPYID).getLike());
        DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().update(addBankCard);

        startActivity(new Intent(BankModifyActivity.this,BankDetailedActivity.class));
        finish();
    }

    @Override
    protected String getLayoutTitle() {
        return "";
    }

    @Override
    protected void initData() {
        String bankName=DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().load(BankCopyActivity.BANKCOPYID).getIssuingBank();
        switch (bankName){
            case "农业银行":
                setBack(R.drawable.bank_nonghang_1,R.drawable.bank_nonghang_2);
                break;

            case "建设银行":
                setBack(R.drawable.bank_jianhang_1,R.drawable.bank_jianhang_2);
                break;

            case "邮政储蓄":
                setBack(R.drawable.bank_youzheng_1,R.drawable.bank_youzheng_2);
                break;

            case "工商银行":
                setBack(R.drawable.bank_gonghang_1,R.drawable.bank_gonghang_2);
                break;

            case "交通银行":
                setBack(R.drawable.bank_jiaotong_1,R.drawable.bank_jiaotong_2);
                break;

            case "中国银行":
                setBack(R.drawable.bank_zhongguo_1,R.drawable.bank_zhongguo_2);
                break;

        }


        ed_bank_modify_account.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankCopyActivity.BANKCOPYID).getBankAccount());
        ed_bank_modify_registrationDate.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankCopyActivity.BANKCOPYID).getRegistrationDate());
        ed_bank_modify_cardholderName.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankCopyActivity.BANKCOPYID).getCardholderName());
        ed_bank_modify_bankCardTelPhoneNum.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankCopyActivity.BANKCOPYID).getTelPhoneNum());
        ed_bank_modify_bankCardRemarks.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankCopyActivity.BANKCOPYID).getRemarks());

        tv_bank_modify_account.setText(ed_bank_modify_account.getText().toString());
        tv_bank_modify_data.setText(ed_bank_modify_registrationDate.getText().toString());
        tv_bank_modify_name.setText(ed_bank_modify_cardholderName.getText().toString());

        ed_bank_modify_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_bank_modify_account.setText(ed_bank_modify_account.getText().toString());
            }
        });

        ed_bank_modify_registrationDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_bank_modify_data.setText(ed_bank_modify_registrationDate.getText().toString());

            }
        });

        ed_bank_modify_cardholderName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tv_bank_modify_name.setText(ed_bank_modify_cardholderName.getText().toString());

            }

        });
    }

    private void setBack(int a,int e){
        ly_bank_modify_two_bg_bank.setBackgroundResource
                (a);
        ly_bank_modify_one_bg_bank.setBackgroundResource
                (e);
    }
    @Override
    protected void initView() {
        DaoUtils.init(this);
        ed_bank_modify_account= (EditText) findViewById(R.id.ed_bank_modify_account);
        ed_bank_modify_registrationDate= (EditText) findViewById(R.id.ed_bank_modify_registrationDate);
        ed_bank_modify_cardholderName= (EditText) findViewById(R.id.ed_bank_modify_cardholderName);
        ed_bank_modify_bankCardTelPhoneNum= (EditText) findViewById(R.id.ed_bank_modify_bankCardTelPhoneNum);
        ed_bank_modify_bankCardRemarks= (EditText) findViewById(R.id.ed_bank_modify_bankCardRemarks);

        ly_bank_modify_two_bg_bank= (CardView) findViewById(R.id.ly_bank_modify_two_bg_bank);
        ly_bank_modify_one_bg_bank= (CardView) findViewById(R.id.ly_bank_modify_one_bg_bank);
        rl_bank_modify_3d_card= (RelativeLayout) findViewById(R.id.rl_bank_modify_3d_card);

        tv_bank_modify_data= (TextView) findViewById(R.id.tv_bank_modify_data);
        tv_bank_modify_account= (TextView) findViewById(R.id.tv_bank_modify_account);
        tv_bank_modify_name= (TextView) findViewById(R.id.tv_bank_modify_name);

        ed_bank_modify_account.setOnTouchListener(this);
        ed_bank_modify_registrationDate.setOnTouchListener(this);
        ed_bank_modify_cardholderName.setOnTouchListener(this);



    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_modify;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.ed_bank_modify_account:
                if (isClose==0){
                    onOpenView(v);
                    isClose=1;
                }
                break;
            case R.id.ed_bank_modify_registrationDate:
                if (isClose==0){
                    onOpenView(v);
                    isClose=1;
                }
                break;
            case R.id.ed_bank_modify_cardholderName:
                if (isClose==1){
                    onCloseView(v);
                    isClose=0;
                }
                break;

        }
        return false;
    }


    public void initOpenAnim() {
        //从0到90度，顺时针旋转视图，此时reverse参数为true，达到90度时动画结束时视图变得不可见，
        openAnimation = new Rotate3dAnimation(0, 90, centerX, centerY, depthZ, true);
        openAnimation.setDuration(duration);
        openAnimation.setFillAfter(true);
        openAnimation.setInterpolator(new AccelerateInterpolator());
        openAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ly_bank_modify_one_bg_bank.setVisibility(View.GONE);
                ly_bank_modify_two_bg_bank.setVisibility(View.VISIBLE);

                //从270到360度，顺时针旋转视图，此时reverse参数为false，达到360度动画结束时视图变得可见
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(270, 360, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rl_bank_modify_3d_card.startAnimation(rotateAnimation);
            }
        });
    }

    public void initCloseAnim() {
        closeAnimation = new Rotate3dAnimation(360, 270, centerX, centerY, depthZ, true);
        closeAnimation.setDuration(duration);
        closeAnimation.setFillAfter(true);
        closeAnimation.setInterpolator(new AccelerateInterpolator());
        closeAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ly_bank_modify_one_bg_bank.setVisibility(View.VISIBLE);
                ly_bank_modify_two_bg_bank.setVisibility(View.GONE);

                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(90, 0, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rl_bank_modify_3d_card.startAnimation(rotateAnimation);
            }
        });
    }

    public void onOpenView(View v) {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = rl_bank_modify_3d_card.getWidth()/2;
        centerY = rl_bank_modify_3d_card.getHeight()/2;
        if (openAnimation == null) {
            initOpenAnim();
            initCloseAnim();
        }

        //用作判断当前点击事件发生时动画是否正在执行
        if (openAnimation.hasStarted() && !openAnimation.hasEnded()) {
            return;
        }
        if (closeAnimation.hasStarted() && !closeAnimation.hasEnded()) {
            return;
        }
        //判断动画执行
        rl_bank_modify_3d_card.startAnimation(openAnimation);
    }

    public void onCloseView(View v) {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = rl_bank_modify_3d_card.getWidth()/2;
        centerY = rl_bank_modify_3d_card.getHeight()/2;
        if (openAnimation == null) {
            initOpenAnim();
            initCloseAnim();
        }

        //用作判断当前点击事件发生时动画是否正在执行
        if (openAnimation.hasStarted() && !openAnimation.hasEnded()) {
            return;
        }
        if (closeAnimation.hasStarted() && !closeAnimation.hasEnded()) {
            return;
        }
        //判断动画执行
        rl_bank_modify_3d_card.startAnimation(closeAnimation);

    }


}
