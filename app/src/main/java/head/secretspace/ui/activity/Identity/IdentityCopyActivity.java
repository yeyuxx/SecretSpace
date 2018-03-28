package head.secretspace.ui.activity.Identity;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.ui.activity.bankcarditem.BankCopyActivity;
import head.secretspace.ui.activity.bankcarditem.BankDetailedActivity;
import head.secretspace.ui.activity.bankcarditem.BankModifyActivity;
import head.secretspace.ui.activity.base.SetCoordinatorActivity;
import head.secretspace.utils.PastePlate;

/**
 * Created by HEAD on 2017/11/25.
 */

public class IdentityCopyActivity extends SetCoordinatorActivity implements View.OnClickListener {
    protected Toolbar tbIdCopy;
    protected CollapsingToolbarLayout ctlIdCopy;
    protected AppBarLayout ablIdCopy;
    protected TextView tvIdXm;
    protected LinearLayout lyIdXm;
    protected TextView tvIdXb;
    protected LinearLayout lyIdXb;
    protected TextView tvIdMz;
    protected LinearLayout lyIdMz;
    protected TextView tvIdSfzh;
    protected LinearLayout lyIdSfzh;
    protected TextView tvIdJtzz;
    protected LinearLayout lyIdJtzz;
    protected TextView tvIdQfjg;
    protected LinearLayout lyIdQfjg;
    protected TextView tvIdQssj;
    protected LinearLayout lyIdQssj;
    protected TextView tvIdZzs;
    protected LinearLayout lyIdZzs;
    protected FloatingActionButton fabIdCopy;

    public static long IDCOPYID;


    @Override
    protected CharSequence getName() {
        return "身份证";
    }

    @Override
    protected void initData() {
        tvIdJtzz.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getHomeAddress());
        tvIdMz.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getFamilyName());
        tvIdQfjg.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getMechanism());
        tvIdSfzh.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getIdCard());
        tvIdQssj.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getStartTime());
        tvIdXb.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getGender());
        tvIdXm.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getName());
        tvIdZzs.setText(DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getEndTime());

    }

    @Override
    protected void initView() {
        tbIdCopy = (Toolbar) findViewById(R.id.tb_id_copy);
        ctlIdCopy = (CollapsingToolbarLayout) findViewById(R.id.ctl_id_copy);
        ablIdCopy = (AppBarLayout) findViewById(R.id.abl_id_copy);
        tvIdXm = (TextView) findViewById(R.id.tv_id_xm);
        lyIdXm = (LinearLayout) findViewById(R.id.ly_id_xm);
        lyIdXm.setOnClickListener(IdentityCopyActivity.this);
        tvIdXb = (TextView) findViewById(R.id.tv_id_xb);
        lyIdXb = (LinearLayout) findViewById(R.id.ly_id_xb);
        lyIdXb.setOnClickListener(IdentityCopyActivity.this);
        tvIdMz = (TextView) findViewById(R.id.tv_id_mz);
        lyIdMz = (LinearLayout) findViewById(R.id.ly_id_mz);
        lyIdMz.setOnClickListener(IdentityCopyActivity.this);
        tvIdSfzh = (TextView) findViewById(R.id.tv_id_sfzh);
        lyIdSfzh = (LinearLayout) findViewById(R.id.ly_id_sfzh);
        lyIdSfzh.setOnClickListener(IdentityCopyActivity.this);
        tvIdJtzz = (TextView) findViewById(R.id.tv_id_jtzz);
        lyIdJtzz = (LinearLayout) findViewById(R.id.ly_id_jtzz);
        lyIdJtzz.setOnClickListener(IdentityCopyActivity.this);
        tvIdQfjg = (TextView) findViewById(R.id.tv_id_qfjg);
        lyIdQfjg = (LinearLayout) findViewById(R.id.ly_id_qfjg);
        lyIdQfjg.setOnClickListener(IdentityCopyActivity.this);
        tvIdQssj = (TextView) findViewById(R.id.tv_id_qssj);
        lyIdQssj = (LinearLayout) findViewById(R.id.ly_id_qssj);
        lyIdQssj.setOnClickListener(IdentityCopyActivity.this);
        tvIdZzs = (TextView) findViewById(R.id.tv_id_zzs);
        lyIdZzs = (LinearLayout) findViewById(R.id.ly_id_zzs);
        lyIdZzs.setOnClickListener(IdentityCopyActivity.this);
        fabIdCopy = (FloatingActionButton) findViewById(R.id.fab_id_copy);
        fabIdCopy.setOnClickListener(IdentityCopyActivity.this);

    }

    @Override
    protected int getViewId3() {
        return R.id.ctl_id_copy;
    }

    @Override
    protected int getViewId2() {
        return R.id.abl_id_copy;
    }

    @Override
    protected int getViewId1() {
        return R.id.tb_id_copy;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_identity_copy;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ly_id_xm) {
            PastePlate.copy(tvIdXm.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_id_xb) {
            PastePlate.copy(tvIdXb.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.ly_id_mz) {
            PastePlate.copy(tvIdMz.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();


        }else if (view.getId() == R.id.ly_id_sfzh) {
            PastePlate.copy(tvIdSfzh.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.ly_id_jtzz) {
            PastePlate.copy(tvIdJtzz.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.ly_id_qfjg) {
            PastePlate.copy(tvIdQfjg.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.ly_id_qssj) {
            PastePlate.copy(tvIdXb.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();


        } else if (view.getId() == R.id.ly_id_zzs) {
            PastePlate.copy(tvIdZzs.getText().toString(), this);
            Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.fab_id_copy) {
            IDCOPYID = DaoUtils.getIdentityCardManager().daoSession.getAddIdentityCardDao().load(IdentityDetailedActivity.IDENTITYID).getId();
            startActivity(new Intent(IdentityCopyActivity.this, IdentityModifyActivity.class));
            finish();
        }
    }
}
