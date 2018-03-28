package head.secretspace.ui.activity.passworditem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.utils.PastePlate;
import head.secretspace.utils.flyn.Eyes;

/**
 * Created by HEAD on 2017/8/9.
 */

public class CopyActivity extends AppCompatActivity {


    public static Long MOID;
    @InjectView(R.id.pd_toolbar)
    Toolbar pdToolbar;
    @InjectView(R.id.pd_toolbar_layout)
    CollapsingToolbarLayout pdToolbarLayout;
    @InjectView(R.id.pd_app_bar)
    AppBarLayout pdAppBar;
    @InjectView(R.id.tv_one_item)
    TextView tvOneItem;
    @InjectView(R.id.ly_one_item)
    LinearLayout lyOneItem;
    @InjectView(R.id.tv_two_item)
    TextView tvTwoItem;
    @InjectView(R.id.ly_two_item)
    LinearLayout lyTwoItem;
    @InjectView(R.id.tv_three_item)
    TextView tvThreeItem;
    @InjectView(R.id.ly_three_item)
    LinearLayout lyThreeItem;
    @InjectView(R.id.pd_fab)
    FloatingActionButton pdFab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_copy);
        ButterKnife.inject(this);
        DaoUtils.init(this);
        initView();
        setToolbar();

    }

    private void initView() {
        tvOneItem.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(PasswordDetailedActivity.SEARCHID).getName() + " ");
        tvTwoItem.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(PasswordDetailedActivity.SEARCHID).getPassword() + " ");
        tvThreeItem.setText(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(PasswordDetailedActivity.SEARCHID).getRemarks() + " ");

    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.pd_toolbar);
        setSupportActionBar(toolbar);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.pd_app_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.pd_toolbar_layout);
        collapsingToolbarLayout.setTitle(DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(PasswordDetailedActivity.SEARCHID).getName());
        Eyes.setStatusBarColorForCollapsingToolbar(this,
                mAppBarLayout,
                collapsingToolbarLayout,
                toolbar,
                ContextCompat.getColor(this, R.color.black_one));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.del_back);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_copy, menu);
        return true;
    }

    @OnClick({R.id.ly_one_item, R.id.ly_two_item, R.id.ly_three_item, R.id.pd_fab})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_one_item:
                PastePlate.copy(tvOneItem.getText().toString(), this);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ly_two_item:
                PastePlate.copy(tvTwoItem.getText().toString(), this);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
            case R.id.pd_fab:
                MOID = DaoUtils.getAddPwItemManager().daoSession.getAddPwItemDao().load(PasswordDetailedActivity.SEARCHID).getId();
                startActivity(new Intent(CopyActivity.this, PasswordModifyActivity.class));
                finish();
                break;
            case R.id.ly_three_item:
                PastePlate.copy(tvThreeItem.getText().toString(), this);
                Toast.makeText(this, "已复制", Toast.LENGTH_SHORT).show();
                break;
        }
    }




}
