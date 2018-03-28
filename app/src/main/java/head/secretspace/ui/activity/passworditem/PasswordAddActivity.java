package head.secretspace.ui.activity.passworditem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.base.SetBaseActivity;
import head.secretspace.ui.activity.login.VerifyLoginActivity;
import head.secretspace.ui.dialog.AddPasswordDialog;

/**
 * Created by HEAD on 2017/6/5.
 */

public class PasswordAddActivity extends SetBaseActivity implements View.OnTouchListener {
    @InjectView(R.id.im_title_close)
    ImageView imTitleClose;
    @InjectView(R.id.bt_title_close)
    Button btTitleClose;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.im_title_save)
    ImageView imTitleSave;
    @InjectView(R.id.bt_title_save)
    Button btTitleSave;
    @InjectView(R.id.im_add_dialog)
    Button imAddDialog;
    @InjectView(R.id.ed_add_pw_name)
    EditText edAddPwName;
    @InjectView(R.id.ed_add_pw_password)
    EditText edAddPwPassword;
    @InjectView(R.id.ed_add_pw_emarks)
    EditText edAddPwEmarks;

    private Context context;

    private int colorValue = 0x00000081;

    @Override
    protected void getBackLogin() {
        startActivity(new Intent(PasswordAddActivity.this, VerifyLoginActivity.class));
        finish();
    }

    @Override
    protected void getFinish() {
        finish();
    }

    @Override
    protected void onSaveData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new Date());
        AddPwItem addPwItem = new AddPwItem();
        addPwItem.setName(edAddPwName.getText().toString());
        addPwItem.setPassword(edAddPwPassword.getText().toString());
        addPwItem.setRemarks(edAddPwEmarks.getText().toString());
        addPwItem.setTime(date);
        DaoUtils.getAddPwItemManager().insertObject(addPwItem);

        AddMainValue addMainValue = new AddMainValue();
        addMainValue.setId(1l);
        addMainValue.setValue(DaoUtils.getAddPwItemManager().QueryAll(AddPwItem.class).size());
        DaoUtils.getAddMainValueManager().updateObject(addMainValue);
        finish();
    }

    @Override
    protected String getLayoutTitle() {
        return "密码";
    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initView() {
        edAddPwName.setOnTouchListener(this);
        edAddPwEmarks.setOnTouchListener(this);
        edAddPwPassword.setOnTouchListener(this);
        DaoUtils.init(PasswordAddActivity.this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_add;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getFinish();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.ed_add_pw_emarks:
                edAddPwPassword.setInputType(colorValue);
                break;
            case R.id.ed_add_pw_name:
                edAddPwPassword.setInputType(colorValue);
                break;
            case R.id.ed_add_pw_password:
                edAddPwPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.im_add_dialog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_add_dialog:
                edAddPwPassword.setInputType(colorValue);
                AddPasswordDialog apd = new AddPasswordDialog(this, new AddPasswordDialog.DialogCallBackListener() {
                    @Override
                    public void CallBack(String msg) {
                        if (!TextUtils.isEmpty(msg))
                            edAddPwPassword.setText(msg);
                    }
                });
                apd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                apd.setCanceledOnTouchOutside(false);
                apd.show();
                break;
        }
    }

}
