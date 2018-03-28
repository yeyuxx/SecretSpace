package head.secretspace.ui.activity.login;

import android.Manifest;
import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.v4.os.CancellationSignal;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import head.secretspace.R;
import head.secretspace.adapter.LoginFragmentAdapter;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.SecretspaceUser;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.fragment.FingerprintFragment;
import head.secretspace.ui.fragment.NumLoginFragment;
import head.secretspace.utils.fingerprint.AuthCallback;
import head.secretspace.utils.fingerprint.CryptoObjectHelper;

/**
 * Created by HEAD on 2017/10/14.
 */

public class VerifyLoginActivity extends FragmentActivity {
    @InjectView(R.id.vp_avl)
    ViewPager vpAvl;


    private final String TAG = "MainActivity";
    private String pwOne;

    private SharedPreferences preferences,preferences1;
    private SharedPreferences.Editor editor1;


    private int id;
    private FingerprintManagerCompat fingerprintManager = null;

    private KeyguardManager keyguardManager = null;

    private AuthCallback authCallback = null;
    private CancellationSignal cancellationSignal = null;

    private Handler handler = null;
    public static final int MSG_AUTH_SUCCESS = 100;
    public static final int MSG_AUTH_FAILED = 101;
    public static final int MSG_AUTH_ERROR = 102;
    public static final int MSG_AUTH_HELP = 103;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_login);
        ButterKnife.inject(this);
        preferences = getSharedPreferences("start", Context.MODE_PRIVATE);
        //禁止截屏
        preferences1=getSharedPreferences("ZW", Context.MODE_PRIVATE);


        editor1=preferences1.edit();
        initView();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_AUTH_SUCCESS:
                        startActivity(new Intent(VerifyLoginActivity.this, MainActivity.class));
                        finish();
                        cancellationSignal = null;
                        break;
                    case MSG_AUTH_FAILED:
                        Toast.makeText(VerifyLoginActivity.this, "指纹错误", Toast.LENGTH_SHORT).show();
                        break;
                    case MSG_AUTH_ERROR:
                        break;
                    case MSG_AUTH_HELP:
                        break;
                }
            }
        };

        /**
         *1.AndroidManifest.xml中添加权限;
         *  add jurisdiction in AndroidManifest.xml;
         */


        /**
         * 2.获得FingerprintManager对象引用
         *  通过V4支持包获得兼容的对象引用，这是google推行的做法(目的是为了让6.0以上版本也能使用指纹识别)；
         *  init fingerprint.  get FingerprintManager obj
         */
        fingerprintManager = FingerprintManagerCompat.from(this);
        /*
         FingerprintManager fingerprintManager =this.getSystemService(FINGERPRINT_SERVICE);
         也可以采用这样方式来获取,但是 API 文档写了.这个只能在6.0以上版本玩.
         */


        /*
        *  3.运行条件检查
        *      3.1 系统版本,在 android 6.0 以上版本(用了v4包得到FingerprintManager对象,变成非必要条件)
        *         It must run API level 23 or higher
        *      3.2 硬件条件
        *      3.3 系统是否有指纹
        *      3.4 当前设备是不是处于安全保护中的,说白了就是,是否需要锁屏密码(没写,待完善...)
        *
        * */

        // 获取当前安全保护信息
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);

        check();
        Boolean isFirstIn = preferences.getBoolean("first", true);
        if (!isFirstIn&&preferences1.getBoolean("zhiwen",true)==true) {
            fingerprint();
        }

    }

    private void initView() {
        if (preferences1.getBoolean("jie",true)==false){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
        }

        Boolean isFirstIn = preferences.getBoolean("first", true);
        if (isFirstIn) {
            startActivity(new Intent(VerifyLoginActivity.this,
                    SetPasswordActivity.class));
            finish();
        }
        else {
            pwOne = DaoUtils.getSecretSpacePasswordManager().
                    QueryAll(SecretspaceUser.class).get(0).getPassword();

        }

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FingerprintFragment());
        fragments.add(new NumLoginFragment());
        LoginFragmentAdapter adapter = new LoginFragmentAdapter(fragments,
                getSupportFragmentManager());
        vpAvl.setAdapter(adapter);
        vpAvl.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0&&preferences1.getBoolean("zhiwen",true)==true) {
                    fingerprint();
                    id = position;
                } else {
                    id = 1;
                    if (preferences1.getBoolean("zhiwen",true)==true) {
                        cancellationSignal.cancel();
                        cancellationSignal = null;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (id == 0&&preferences1.getBoolean("zhiwen",true)==true)
            fingerprint();

    }

    public void fingerprint() {
        try {
                if (cancellationSignal == null) {
                    cancellationSignal = new CancellationSignal();
                }

                     /* 4.1
                      * 自定义 CryptoObjectHelper类 ,
                      * 通过 buildCryptoObject()方法得到CryptoObject这个对象
                     * */
                CryptoObjectHelper cryptoObjectHelper = new CryptoObjectHelper();
                fingerprintManager.authenticate(cryptoObjectHelper.buildCryptoObject(), 0,
                        cancellationSignal, authCallback, null);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // 检查
    public void check() {
        if (!fingerprintManager.isHardwareDetected()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.no_sensor_dialog_title);
            builder.setMessage(R.string.no_sensor_dialog_message);
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else if (!fingerprintManager.hasEnrolledFingerprints()) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.no_fingerprint_enrolled_dialog_title);
            builder.setMessage(R.string.no_fingerprint_enrolled_dialog_message);
            builder.setIcon(android.R.drawable.stat_sys_warning);
            builder.setCancelable(false);
            builder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        } else if (!keyguardManager.isKeyguardSecure()) {
            System.out.println("你的设备处于非安全状态");
            // 如果是非安全状态,需要提醒一下用户
            // 待完善.....
        } else {
            try {
                // 如果以上都成功的 为第四步整备参数了
                //用户的指纹认证结果
                authCallback = new AuthCallback(handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
