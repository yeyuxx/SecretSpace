package head.secretspace.ui.activity.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.SecretspaceUser;
import head.secretspace.ui.activity.MainActivity;

/**
 * Created by HEAD on 2017/11/25.
 */

public class ModifyPasswordActivity extends Activity {

    private String password;

    @InjectView(R.id.ed_new_password)
    EditText edNewPassword;
    @InjectView(R.id.ed_start_password)
    EditText edStartPassword;
    @InjectView(R.id.bt_add_password)
    Button btAddPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modify_password);
        ButterKnife.inject(this);
        DaoUtils.init(this);
        initState();
        initData();
    }

    private void initData() {

        if (edNewPassword.getText().toString().equals("")){
            enableView(edStartPassword,false);
        }

        edNewPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()==0){
                    enableView(edStartPassword,false);
                }else if (!s.equals("")){
                    enableView(edStartPassword,true);
                    edStartPassword.setText("");
                }

            }
        });
    }



    private void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                enableView(child, enabled);
            }
        }
    }

    private void initState() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @OnClick({R.id.ed_new_password, R.id.ed_start_password, R.id.bt_add_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ed_new_password:
                break;
            case R.id.ed_start_password:
                break;
            case R.id.bt_add_password:

                if (edStartPassword.getText().length()==0){
                    Toast.makeText(ModifyPasswordActivity.this, "请填写原始密码", Toast.LENGTH_SHORT).show();
                }
               else if (edStartPassword.getText().length() != 0 && !edStartPassword.getText().toString().equals(DaoUtils.getSecretSpacePasswordManager().
                        QueryAll(SecretspaceUser.class).get(0).getPassword())){
                    Toast.makeText(ModifyPasswordActivity.this, "原始密码错误", Toast.LENGTH_SHORT).show();
                    edStartPassword.setText("");
                }
                else {
                   SecretspaceUser secretspaceUser=new SecretspaceUser();
                   secretspaceUser.setId(1l);
                   secretspaceUser.setPassword(edNewPassword.getText().toString());
                   DaoUtils.getSecretSpacePasswordManager().daoSession.getSecretspaceUserDao().update(secretspaceUser);
                   startActivity(new Intent(ModifyPasswordActivity.this, VerifyLoginActivity.class));
                   finish();
                }
                break;
        }
    }
}
