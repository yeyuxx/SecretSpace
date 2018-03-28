package head.secretspace.ui.activity.bankcarditem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.ui.activity.base.SetCoordinatorActivity;
import head.secretspace.utils.PastePlate;

/**
 * Created by HEAD on 2017/10/16.
 */

public class BankCopyActivity extends SetCoordinatorActivity implements View.OnClickListener {
    public static long BANKCOPYID;
    protected Toolbar tbBankCopy;
    protected CollapsingToolbarLayout ctlBankCopy;
    protected AppBarLayout ablBankCopy;
    protected TextView tvBankYhkzh;
    protected LinearLayout lyBankYhkzh;
    protected TextView tvBankCkrxm;
    protected LinearLayout lyBankCkrxm;
    protected TextView tvBankZcsjh;
    protected LinearLayout lyBankZcsjh;
    protected TextView tvBankZcrq;
    protected LinearLayout lyBankZcrq;
    protected TextView tvBankBz;
    protected LinearLayout lyBankBz;
    protected FloatingActionButton fabBankCopy;


    @Override
    protected CharSequence getName() {
        return DaoUtils.getBankCardManager().daoSession.
                getAddBankCardDao().load(BankDetailedActivity.BANKID).getIssuingBank();
    }

    @Override
    protected void initData() {
        tvBankYhkzh.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankDetailedActivity.BANKID).getBankAccount() + "");
        tvBankCkrxm.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankDetailedActivity.BANKID).getCardholderName() + "");
        tvBankZcsjh.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankDetailedActivity.BANKID).getTelPhoneNum() + "");
        tvBankZcrq.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankDetailedActivity.BANKID).getRegistrationDate() + "");
        tvBankBz.setText(DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().
                load(BankDetailedActivity.BANKID).getRemarks() + "");
    }

    @Override
    protected void initView() {
        tbBankCopy = (Toolbar) findViewById(R.id.tb_bank_copy);
        ctlBankCopy = (CollapsingToolbarLayout) findViewById(R.id.ctl_bank_copy);
        ablBankCopy = (AppBarLayout) findViewById(R.id.abl_bank_copy);
        tvBankYhkzh = (TextView) findViewById(R.id.tv_bank_yhkzh);
        lyBankYhkzh = (LinearLayout) findViewById(R.id.ly_bank_yhkzh);
        lyBankYhkzh.setOnClickListener(BankCopyActivity.this);
        tvBankCkrxm = (TextView) findViewById(R.id.tv_bank_ckrxm);
        lyBankCkrxm = (LinearLayout) findViewById(R.id.ly_bank_ckrxm);
        lyBankCkrxm.setOnClickListener(BankCopyActivity.this);
        tvBankZcsjh = (TextView) findViewById(R.id.tv_bank_zcsjh);
        lyBankZcsjh = (LinearLayout) findViewById(R.id.ly_bank_zcsjh);
        lyBankZcsjh.setOnClickListener(BankCopyActivity.this);
        tvBankZcrq = (TextView) findViewById(R.id.tv_bank_zcrq);
        lyBankZcrq = (LinearLayout) findViewById(R.id.ly_bank_zcrq);
        lyBankZcrq.setOnClickListener(BankCopyActivity.this);
        tvBankBz = (TextView) findViewById(R.id.tv_bank_bz);
        lyBankBz = (LinearLayout) findViewById(R.id.ly_bank_bz);
        lyBankBz.setOnClickListener(BankCopyActivity.this);
        fabBankCopy = (FloatingActionButton) findViewById(R.id.fab_bank_copy);
        fabBankCopy.setOnClickListener(BankCopyActivity.this);

    }

    @Override
    protected int getViewId3() {
        return R.id.ctl_bank_copy;
    }

    @Override
    protected int getViewId2() {
        return R.id.abl_bank_copy;
    }

    @Override
    protected int getViewId1() {
        return R.id.tb_bank_copy;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bank_copy;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ly_bank_yhkzh) {
            PastePlate.copy(tvBankYhkzh.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_bank_ckrxm) {
            PastePlate.copy(tvBankCkrxm.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_bank_zcsjh) {
            PastePlate.copy(tvBankZcsjh.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_bank_zcrq) {
            PastePlate.copy(tvBankZcrq.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_bank_bz) {
            PastePlate.copy(tvBankBz.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.fab_bank_copy) {
            BANKCOPYID = DaoUtils.getBankCardManager().daoSession.getAddBankCardDao().load(BankDetailedActivity.BANKID).getId();
            startActivity(new Intent(BankCopyActivity.this, BankModifyActivity.class));
            finish();
        }
    }
}
