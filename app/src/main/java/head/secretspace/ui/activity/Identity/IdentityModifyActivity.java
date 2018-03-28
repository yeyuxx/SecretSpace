package head.secretspace.ui.activity.Identity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.bankcarditem.BankCopyActivity;
import head.secretspace.ui.activity.bankcarditem.BankDetailedActivity;
import head.secretspace.ui.activity.bankcarditem.BankModifyActivity;
import head.secretspace.ui.activity.bankcarditem.BankTypeActivity;
import head.secretspace.ui.activity.base.SetBaseActivity;
import head.secretspace.ui.activity.login.VerifyLoginActivity;
import head.secretspace.ui.dialog.IdentityCardDialog;

/**
 * Created by HEAD on 2017/11/25.
 */

public class IdentityModifyActivity extends SetBaseActivity {


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
    @InjectView(R.id.ed_modify_name)
    EditText edModifyName;
    @InjectView(R.id.rb_modify_man)
    RadioButton rbModifyMan;
    @InjectView(R.id.rb_modify_woman)
    RadioButton rbModifyWoman;
    @InjectView(R.id.rg_modify_gender)
    RadioGroup rgModifyGender;
    @InjectView(R.id.sp_modify_family_name)
    Spinner spModifyFamilyName;
    @InjectView(R.id.ed_modify_address)
    EditText edModifyAddress;
    @InjectView(R.id.ed_modify_id)
    EditText edModifyId;
    @InjectView(R.id.ed_modify_mechanism)
    EditText edModifyMechanism;
    @InjectView(R.id.ed_modify_start)
    EditText edModifyStart;
    @InjectView(R.id.bt_modify_start)
    Button btModifyStart;
    @InjectView(R.id.ed_modify_end)
    EditText edModifyEnd;
    @InjectView(R.id.bt_modify_end)
    Button btModifyEnd;


    private String mechanism;
    private String gender;

    @Override
    protected void getBackLogin() {
        startActivity(new Intent(IdentityModifyActivity.this, VerifyLoginActivity.class));

    }

    @Override
    protected void getFinish() {
        startActivity(new Intent(IdentityModifyActivity.this, MainActivity.class));
        finish();

    }

    @Override
    protected void onSaveData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new Date());
        AddIdentityCard identityCard = new AddIdentityCard();
        identityCard.setId(IdentityCopyActivity.IDCOPYID);
        identityCard.setEndTime(edModifyEnd.getText().toString());
        identityCard.setFamilyName(mechanism);
        identityCard.setGender(gender);
        identityCard.setHomeAddress(edModifyAddress.getText().toString());
        identityCard.setIdCard(edModifyId.getText().toString());
        identityCard.setName(edModifyName.getText().toString());
        identityCard.setStartTime(edModifyStart.getText().toString());
        identityCard.setMechanism(edModifyMechanism.getText().toString());
        identityCard.setTime(date);
        DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().update(identityCard);
        startActivity(new Intent(IdentityModifyActivity.this,IdentityDetailedActivity.class));
        finish();
    }

    @Override
    protected String getLayoutTitle() {
        return "身份证";
    }

    @Override
    protected void initData() {
        switch(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityCopyActivity.IDCOPYID).getGender()+""){
            case "男":
                rbModifyMan.setChecked(true);
                break;
            case "女":
                rbModifyWoman.setChecked(true);
                break;
        }
//
        spModifyFamilyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getFamilyName(position);
            }

            private void getFamilyName(int position) {
                mechanism = (IdentityModifyActivity.this.getResources().getStringArray(R.array.famous_families))[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DaoUtils.init(IdentityModifyActivity.this);
    }

    @Override
    protected void initView() {

        rgModifyGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == rgModifyGender.getId()) {
                    gender = "男";
                } else {
                    gender = "女";
                }
            }
        });


    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_modify_card;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
    }

    @OnClick({R.id.bt_modify_start, R.id.bt_modify_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_modify_start:
                IdentityCardDialog identityCardDialogTwo = new IdentityCardDialog(IdentityModifyActivity.this, new IdentityCardDialog.DialogCallBackListener() {
                    @Override
                    public void CallBack(String msg) {
                        edModifyStart.setText(msg + "");
                    }
                });
                identityCardDialogTwo.show();
                break;
            case R.id.bt_modify_end:
                IdentityCardDialog identityCardDialogThree = new IdentityCardDialog(IdentityModifyActivity.this, new IdentityCardDialog.DialogCallBackListener() {
                    @Override
                    public void CallBack(String msg) {
                        edModifyEnd.setText(msg + "");
                    }
                });
                identityCardDialogThree.show();
                break;
        }

    }
}
