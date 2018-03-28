package head.secretspace.ui.activity.bankcarditem;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.animation.Rotate3dAnimation;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.base.SetBaseActivity;
import head.secretspace.ui.activity.login.VerifyLoginActivity;

/**
 * Created by HEAD on 2017/7/5.
 */

public class BankCardActivity extends SetBaseActivity implements View.OnTouchListener{

    private EditText edBankAccount;
    private EditText edCardholderName;
    private EditText edRegistrationDate;
    private EditText edTelPhoneNum;
    private EditText edRemarks;
    private RelativeLayout rl3dAnimation;
    private CardView lyOneBg;
    private CardView lyTwobg;
    private TextView tvBankCardAccount;
    private TextView tvBankCardName;
    private TextView tvBankCardDate;

    private int centerX;
    private int centerY;
    private int depthZ = 400;
    private int duration = 600;
    private Rotate3dAnimation openAnimation;
    private Rotate3dAnimation closeAnimation;

    private int isClose=1;


    public static List<Integer> getPositive(){
        List<Integer> data=new ArrayList<>();
        data.add(R.drawable.bank_nonghang_1);
        data.add(R.drawable.bank_jianhang_1);
        data.add(R.drawable.bank_youzheng_1);
        data.add(R.drawable.bank_gonghang_1);
        data.add(R.drawable.bank_jiaotong_1);
        data.add(R.drawable.bank_zhongguo_1);
        return data;
    }

    public static List<Integer> getOtherSide(){
        List<Integer> data=new ArrayList<>();
        data.add(R.drawable.bank_nonghang_2);
        data.add(R.drawable.bank_jianhang_2);
        data.add(R.drawable.bank_youzheng_2);
        data.add(R.drawable.bank_gonghang_2);
        data.add(R.drawable.bank_jiaotong_2);
        data.add(R.drawable.bank_zhongguo_2);
        return data;
    }

    public static List<String> getIssuingBank(){
        List<String> data=new ArrayList<>();
        data.add("农业银行");
        data.add("建设银行");
        data.add("邮政储蓄");
        data.add("工商银行");
        data.add("交通银行");
        data.add("中国银行");
        return data;
    }

    @Override
    protected void getBackLogin() {
        startActivity(new Intent(BankCardActivity.this,VerifyLoginActivity.class));

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
        addBankCard.setBankAccount(edBankAccount.getText().toString());
        addBankCard.setCardholderName(edCardholderName.getText().toString());
        addBankCard.setRegistrationDate(edRegistrationDate.getText().toString());
        addBankCard.setTelPhoneNum(edTelPhoneNum.getText().toString());
        addBankCard.setRemarks(edRemarks.getText().toString());
        addBankCard.setIssuingBank(getIssuingBank().get(BankTypeActivity.TYPE));
        addBankCard.setTime(date);
        DaoUtils.getBankCardManager().insertObject(addBankCard);

        AddMainValue addMainValue=new AddMainValue();
        addMainValue.setId(2l);
        addMainValue.setValue(DaoUtils.getBankCardManager().QueryAll(AddBankCard.class).size());
        DaoUtils.getAddMainValueManager().updateObject(addMainValue);

        finish();

    }

    @Override
    protected String getLayoutTitle() {
        return "银行账户";
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        tvBankCardName= (TextView) findViewById(R.id.tv_bank_card_name);
        rl3dAnimation= (RelativeLayout) findViewById(R.id.ly_3d_card);
        lyOneBg= (CardView) findViewById(R.id.ly_one_bg_bank);
        lyTwobg= (CardView) findViewById(R.id.ly_two_bg_bank);
        edBankAccount= (EditText) findViewById(R.id.bankAccount);
        edCardholderName= (EditText) findViewById(R.id.cardholderName);
        edRegistrationDate= (EditText) findViewById(R.id.registrationDate);
        edTelPhoneNum= (EditText) findViewById(R.id.bankCardTelPhoneNum);
        edRemarks= (EditText) findViewById(R.id.bankCardRemarks);
        tvBankCardAccount= (TextView) findViewById(R.id.tv_bank_card_account);
        tvBankCardDate= (TextView) findViewById(R.id.tv_bank_card_data);

        DaoUtils.init(BankCardActivity.this);

        edBankAccount.setOnTouchListener(this);
        edCardholderName.setOnTouchListener(this);
        edRegistrationDate.setOnTouchListener(this);
        edTelPhoneNum.setOnTouchListener(this);
        edRemarks.setOnTouchListener(this);


        lyTwobg.setBackgroundResource(getPositive().get(BankTypeActivity.TYPE));
        lyOneBg.setBackgroundResource(getOtherSide().get(BankTypeActivity.TYPE));

        edRegistrationDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvBankCardDate.setText(s);
            }
        });

        edCardholderName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                tvBankCardName.setText(s);

            }
        });

        edBankAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                tvBankCardAccount.setText(s);

            }
        });
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_card;
    }

    public void initOpenAnim() {
        //从0到90度，顺时针旋转视图，此时reverse参数为true，达到90度时动画结束时视图变得不可见，
        openAnimation = new Rotate3dAnimation(0, 90,
                centerX, centerY, depthZ, true);
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
                lyOneBg.setVisibility(View.GONE);
                lyTwobg.setVisibility(View.VISIBLE);
                //从270到360度，顺时针旋转视图，此时reverse参数为false，达到360度动画结束时视图变得可见
                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(270,
                        360, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rl3dAnimation.startAnimation(rotateAnimation);
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
                lyOneBg.setVisibility(View.VISIBLE);
                lyTwobg.setVisibility(View.GONE);

                Rotate3dAnimation rotateAnimation = new Rotate3dAnimation(90, 0, centerX, centerY, depthZ, false);
                rotateAnimation.setDuration(duration);
                rotateAnimation.setFillAfter(true);
                rotateAnimation.setInterpolator(new DecelerateInterpolator());
                rl3dAnimation.startAnimation(rotateAnimation);
            }
        });
    }

    public void onOpenView(View v) {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = rl3dAnimation.getWidth()/2;
        centerY = rl3dAnimation.getHeight()/2;
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
        rl3dAnimation.startAnimation(openAnimation);
        }

    public void onCloseView(View v) {
        //以旋转对象的中心点为旋转中心点，这里主要不要再onCreate方法中获取，因为视图初始绘制时，获取的宽高为0
        centerX = rl3dAnimation.getWidth()/2;
        centerY = rl3dAnimation.getHeight()/2;
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
        rl3dAnimation.startAnimation(closeAnimation);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()){
            case R.id.bankAccount:
                if (isClose==0){
                    onOpenView(v);
                    isClose=1;
                }
                break;

            case R.id.cardholderName:
                if (isClose==1){
                    onCloseView(v);
                    isClose=0;
                }
                break;

            case R.id.bankCardTelPhoneNum:
                break;

            case R.id.registrationDate:
                if (isClose==0){
                    onOpenView(v);
                    isClose=1;
                }
                break;

            case R.id.bankCardRemarks:
                break;
        }
        return false;
    }
}
