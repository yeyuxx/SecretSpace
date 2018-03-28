package head.secretspace.ui.activity.Identity;

import android.content.Intent;
import android.support.annotation.IdRes;
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

import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.entity.AddMainValue;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.base.SetBaseActivity;
import head.secretspace.ui.activity.login.VerifyLoginActivity;
import head.secretspace.ui.dialog.IdentityCardDialog;

/**
 * Created by HEAD on 2017/7/16.
 */

public class IdentityCardActivity extends SetBaseActivity {

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
    @InjectView(R.id.ed_idcard_name)
    EditText edIdcardName;
    @InjectView(R.id.rb_idcard_man)
    RadioButton rbIdcardMan;
    @InjectView(R.id.rb_idcard_woman)
    RadioButton rbIdcardWoman;
    @InjectView(R.id.rg_gender)
    RadioGroup rgGender;
    @InjectView(R.id.sp_idcard_family_name)
    Spinner spIdcardFamilyName;

    @InjectView(R.id.ed_idcard_address)
    EditText edIdcardAddress;
    @InjectView(R.id.ed_idcard_id)
    EditText edIdcardId;
    @InjectView(R.id.ed_idcard_mechanism)
    EditText edIdcardMechanism;
    @InjectView(R.id.ed_idcard_start)
    EditText edIdcardStart;
    @InjectView(R.id.bt_idcard_start)
    Button btIdcardStart;
    @InjectView(R.id.ed_idcard_end)
    EditText edIdcardEnd;
    @InjectView(R.id.bt_idcard_end)
    Button btIdcardEnd;

    private String familyName;
    private String gender;


    @Override
    protected void getBackLogin() {
        startActivity(new Intent(IdentityCardActivity.this, VerifyLoginActivity.class));

    }

    @Override
    protected void getFinish() {
        startActivity(new Intent(IdentityCardActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onSaveData() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = sDateFormat.format(new Date());
        AddIdentityCard identityCard = new AddIdentityCard();
        identityCard.setEndTime(edIdcardEnd.getText().toString());
        identityCard.setFamilyName(familyName);
        identityCard.setGender(gender);
        identityCard.setHomeAddress(edIdcardAddress.getText().toString());
        identityCard.setIdCard(edIdcardId.getText().toString());
        identityCard.setName(edIdcardName.getText().toString());
        identityCard.setStartTime(edIdcardStart.getText().toString());
        identityCard.setMechanism(edIdcardMechanism.getText().toString());
        identityCard.setTime(date);
        DaoUtils.getIdentityCardManager().insertObject(identityCard);

        AddMainValue addMainValue = new AddMainValue();
        addMainValue.setId(3l);
        addMainValue.setValue(DaoUtils.getIdentityCardManager().QueryAll(AddIdentityCard.class).size());
        DaoUtils.getAddMainValueManager().updateObject(addMainValue);

        startActivity(new Intent(IdentityCardActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected String getLayoutTitle() {
        return "身份证";
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {


        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == rbIdcardMan.getId()) {
                    gender = "男";
                } else {
                    gender = "女";
                }
            }
        });

        spIdcardFamilyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getFamilyName(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        DaoUtils.init(IdentityCardActivity.this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identity_card;
    }


    private void getFamilyName(int i) {
        familyName = (this.getResources().getStringArray(R.array.famous_families))[i];
    }



    @OnClick({ R.id.bt_idcard_start, R.id.bt_idcard_end})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.bt_idcard_start:
                IdentityCardDialog identityCardDialogTwo = new IdentityCardDialog(IdentityCardActivity.this, new IdentityCardDialog.DialogCallBackListener() {
                    @Override
                    public void CallBack(String msg) {
                        edIdcardStart.setText(msg + "");
                    }
                });
                identityCardDialogTwo.show();
                break;
            case R.id.bt_idcard_end:
                IdentityCardDialog identityCardDialogThree = new IdentityCardDialog(IdentityCardActivity.this, new IdentityCardDialog.DialogCallBackListener() {
                    @Override
                    public void CallBack(String msg) {
                        edIdcardEnd.setText(msg + "");
                    }
                });
                identityCardDialogThree.show();
                break;
        }
    }
}
