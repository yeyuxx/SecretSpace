package head.secretspace.ui.activity.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.SecretspaceUser;

/**
 * Created by HEAD on 2017/4/30.
 */

public class SetPasswordActivity extends Activity {
    @InjectView(R.id.one_password)
    EditText onePassword;
    @InjectView(R.id.two_password)
    EditText twoPassword;
    @InjectView(R.id.query_password)
    Button queryPassword;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        ButterKnife.inject(this);
        DaoUtils.init(this);
        preferences = getSharedPreferences("start", Context.MODE_PRIVATE);
        initState();
    }


    private void initState() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    private void setJudge() {
        String password1 = onePassword.getText().toString();
        String password2 = twoPassword.getText().toString();
        if (password1.equals("") || password2.equals("")) {
            Toast.makeText(SetPasswordActivity.this, "请设置密码", Toast.LENGTH_SHORT).show();
        } else if (password1.equals(password2) && !password1.equals("") && !password2.equals("")) {
            editor = preferences.edit();
            editor.putBoolean("first", false);
            editor.commit();
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String date = sDateFormat.format(new Date());
            SecretspaceUser secretspaceUser = new SecretspaceUser();
            secretspaceUser.setPassword(password2);
            secretspaceUser.setTime(date);
            DaoUtils.getSecretSpacePasswordManager().insertObject(secretspaceUser);
            startActivity(new Intent(SetPasswordActivity.this, VerifyLoginActivity.class));
            finish();
        } else {
            Toast.makeText(this, "密码不一致", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick({R.id.one_password, R.id.two_password, R.id.query_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.one_password:
                break;
            case R.id.two_password:
                break;
            case R.id.query_password:
                setJudge();
                break;
        }
    }
}
