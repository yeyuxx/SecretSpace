package head.secretspace.ui.activity.passworditem;

import android.content.Intent;
import android.os.Bundle;
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
import head.secretspace.entity.AddPwItem;
import head.secretspace.ui.activity.base.SetBaseActivity;
import head.secretspace.ui.dialog.AddPasswordDialog;

/**
 * Created by HEAD on 2017/8/26.
 */

public class PasswordModifyActivity extends SetBaseActivity implements View.OnTouchListener{
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
    @InjectView(R.id.im_modified_dialog)
    Button imModifiedDialog;
    @InjectView(R.id.ed_modified_name)
    EditText edModifiedName;
    @InjectView(R.id.ed_modified_password)
    EditText edModifiedPassword;
    @InjectView(R.id.ed_modified_emarks)
    EditText edModifiedEmarks;
    private Button btDialog;
    private int colorValue = 0x00000081;

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
        String date = sDateFormat.format(new Date());
        AddPwItem addPwItem = new AddPwItem();
        addPwItem.setId(CopyActivity.MOID);
        addPwItem.setTime(date);
        addPwItem.setPassword(edModifiedPassword.getText().toString());
        addPwItem.setName(edModifiedName.getText().toString());
        addPwItem.setRemarks(edModifiedEmarks.getText().toString());
        addPwItem.setLike(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(CopyActivity.MOID).getLike());
        DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().update(addPwItem);

        startActivity(new Intent(PasswordModifyActivity.this, PasswordDetailedActivity.class));
        finish();
    }

    @Override
    protected String getLayoutTitle() {
        return "密码";
    }

    @Override
    protected void initData() {
        edModifiedName.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().
                load(CopyActivity.MOID).getName());
        edModifiedEmarks.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().
                load(CopyActivity.MOID).getRemarks());
        edModifiedPassword.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().
                load(CopyActivity.MOID).getPassword());


    }

    @Override
    protected void initView() {
        DaoUtils.init(this);
        edModifiedName.setOnTouchListener(this);
        edModifiedEmarks.setOnTouchListener(this);
        edModifiedPassword.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.ed_modified_emarks:
                edModifiedPassword.setInputType(colorValue);
                break;
            case R.id.ed_modified_name:
                edModifiedPassword.setInputType(colorValue);
                break;
            case R.id.ed_modified_password:
                edModifiedPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                break;

        }
        return false;
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_password_modified;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick(R.id.im_modified_dialog)
    public void onViewClicked() {
        edModifiedPassword.setInputType(colorValue);
        AddPasswordDialog apd = new AddPasswordDialog(this, new AddPasswordDialog.DialogCallBackListener() {
            @Override
            public void CallBack(String msg) {
                if (!TextUtils.isEmpty(msg))
                    edModifiedPassword.setText(msg);
            }
        });
        apd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        apd.setCanceledOnTouchOutside(false);
        apd.show();
    }
}
