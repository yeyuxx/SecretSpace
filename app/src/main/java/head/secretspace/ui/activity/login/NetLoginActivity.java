package head.secretspace.ui.activity.login;

import android.app.ActivityOptions;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.transition.Explode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.bean.User;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.interfaces.LoginView;
import head.secretspace.presenter.LoginPresenter;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.fragment.FavoritesFragment;
import head.secretspace.utils.Constant;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by HEAD on 2017/11/26.
 */

public class NetLoginActivity extends AppCompatActivity implements LoginView{

    @InjectView(R.id.et_username)
    EditText etUsername;
    @InjectView(R.id.et_password)
    EditText etPassword;
    @InjectView(R.id.bt_go)
    Button btGo;
    @InjectView(R.id.cv)
    CardView cv;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    private LoginPresenter mLoginPresenter;

    OkHttpClient okHttpClient=new OkHttpClient();
    private String mBaseUrl="http://192.168.43.55:8080/SecretSpace/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_login);
        ButterKnife.inject(this);
        mLoginPresenter=new LoginPresenter(this);
    }

    @OnClick({R.id.bt_go, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                getWindow().setExitTransition(null);
                getWindow().setEnterTransition(null);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options =
                            ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
                    startActivity(new Intent(this, NetRegisterActivity.class), options.toBundle());
                } else {
                    startActivity(new Intent(this, NetRegisterActivity.class));
                }
                break;
            case R.id.bt_go:
                Explode explode = new Explode();
                explode.setDuration(500);

                getWindow().setExitTransition(explode);
                getWindow().setEnterTransition(explode);
                String account = etUsername.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mLoginPresenter.login(account, password);
                break;

//                ActivityOptionsCompat oc2 = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
//                Intent i2 = new Intent(this,LoginSuccessActivity.class);
//                startActivity(i2, oc2.toBundle());
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
        Toast.makeText(this, "没有此用户请去注册", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loginSuccess(User user, String password) {
//        getCreateFile();
        if (FavoritesFragment.IOI==1){
            getUpload();
        }
        else if (FavoritesFragment.IOI==2){
            getDownFile();
        }

        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }

    private void getDownFile() {
        final String urldb=etUsername.getText().toString()+etPassword.getText().toString()+".db";
        Request.Builder builder=new Request.Builder();
        Request request = builder
                .get()
                .url(mBaseUrl+"files/"+urldb)
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is=response.body().byteStream();
                int len=0;
                File file1=new File(Environment.getExternalStorageDirectory(),urldb);
                byte[] buf=new byte[1024*5];
                FileOutputStream fos=new FileOutputStream(file1);

                while ((len=is.read(buf))!=-1){
                    fos.write(buf,0,len);
                }
                fos.flush();
                fos.close();
                is.close();
                final Runnable runnable=new Runnable() {
                    @Override
                    public void run() {
                        getDialog();
                    }
                };
                new Thread(){
                    @Override
                    public void run() {
                        new Handler(Looper.getMainLooper()).post(runnable);
                    }
                }.start();
            }
        });



    }

    private void getDialog(){
        AlertDialog.Builder builde1= new AlertDialog.Builder(NetLoginActivity.this);
        builde1.setTitle(R.string.F_fragment_title2);
        builde1.setMessage(R.string.F_fragment_message2);
        builde1.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builde1.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getDownDB();

            }
        });
        builde1.create().show();

    }

    private void getDownDB() {
        String urldb =Environment.getExternalStorageDirectory() + "/"+ etUsername.getText().toString() + etPassword.getText().toString() + ".db";
        File file=new File(urldb);
        if (file.exists()) {
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(urldb, null);
            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(Constant.PATH1, null);
            Cursor cursor1 = db.rawQuery("select * from ADD_MAIN_VALUE", null);
            Cursor cursor2 = db.rawQuery("select * from ADD_PW_ITEM", null);
            Cursor cursor3 = db.rawQuery("select * from ADD_BANK_CARD", null);
            Cursor cursor4 = db.rawQuery("select * from ADD_IDENTITY_CARD", null);
            database.execSQL("DELETE FROM ADD_MAIN_VALUE");//清除表中所有记录：
            database.execSQL("DELETE FROM ADD_PW_ITEM");
            database.execSQL("DELETE FROM ADD_BANK_CARD");
            database.execSQL("DELETE FROM ADD_IDENTITY_CARD");
            if (cursor1 != null) {
                while (cursor1.moveToNext()) {
                    AddMainValue addMainValue = new AddMainValue(cursor1.getLong(0),
                            cursor1.getString(1),
                            cursor1.getInt(2),
                            cursor1.getString(3),
                            cursor1.getInt(4));
                    addMainValue.setLike(cursor1.getInt(3));
                    DaoUtils.getAddMainValueManager().insertObject(addMainValue);
                }
            }
            if (cursor2 != null) {
                while (cursor2.moveToNext()) {
                    AddPwItem addPwItem = new AddPwItem(cursor2.getLong(0),
                            cursor2.getString(1),
                            cursor2.getString(2),
                            cursor2.getString(3),
                            cursor2.getString(4),
                            cursor2.getInt(5));
                    DaoUtils.getAddPwItemManager().insertObject(addPwItem);
                }
            }
            if (cursor3 != null) {
                while (cursor3.moveToNext()) {
                    AddBankCard addBankCard = new AddBankCard(cursor3.getLong(0),
                            cursor3.getString(1),
                            cursor3.getString(2),
                            cursor3.getString(3),
                            cursor3.getString(4),
                            cursor3.getString(5),
                            cursor3.getString(6),
                            cursor3.getString(7),
                            cursor3.getInt(8));

                    DaoUtils.getBankCardManager().insertObject(addBankCard);
                }
                if (cursor4 != null) {
                    while (cursor4.moveToNext()) {
                        AddIdentityCard addIdentityCard = new AddIdentityCard(cursor4.getLong(0),
                                cursor4.getString(1),
                                cursor4.getString(2),
                                cursor4.getString(3),
                                cursor4.getString(4),
                                cursor4.getString(5),
                                cursor4.getString(6),
                                cursor4.getString(7),
                                cursor4.getString(8),
                                cursor4.getString(9),
                                cursor4.getInt(10));

                        DaoUtils.getIdentityCardManager().insertObject(addIdentityCard);
                    }
                }

                cursor4.close();
                cursor3.close();
                cursor2.close();
                cursor1.close();


                db.close();
            }
        }else {
            Toast.makeText(this, "恢复失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void getCreateFile() {

        OkHttpClient okHttpClient=new OkHttpClient();

        //构造request
        Request.Builder builder=new Request.Builder();
        Request request= builder.get().url(mBaseUrl+"login?username="+etUsername.getText().toString()+"&password="+etPassword.getText().toString()).build();
        Call call=okHttpClient.newCall(request);

        //将request封装为Call
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("onFailure",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res=response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        });
    }

    public void getUpload() {
        String urldb=etPassword.getText().toString()+etUsername.getText().toString();
       File file= new File("data/data/head.secretspace/databases","SecretspaceDao.db");
       if (!file.exists()){
           Toast.makeText(this, "-=-", Toast.LENGTH_SHORT).show();
           return;
       }
       MultipartBody.Builder multipartbody=new MultipartBody.Builder();
       RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
       RequestBody requestBody = multipartbody
               .setType(MultipartBody.FORM)
               .addFormDataPart("shuwen",urldb+".db", fileBody)
               .build();

        Request.Builder builder=new Request.Builder();
        Request request = builder
                .url(mBaseUrl+"uploadInfo")
                .post(requestBody)
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }

}
