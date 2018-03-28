package head.secretspace.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.utils.flyn.Eyes;

/**
 * Created by HEAD on 2017/10/16.
 */

public abstract class SetCoordinatorActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        DaoUtils.init(this);

        initView();
        initData();

        Toolbar toolbar = (Toolbar) findViewById(getViewId1());
        setSupportActionBar(toolbar);
        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(getViewId2());
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(getViewId3());
        collapsingToolbarLayout.setTitle(getName());
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

    protected abstract CharSequence getName();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getViewId3();

    protected abstract int getViewId2();

    protected abstract int getViewId1();

    protected abstract int getLayoutId();

    
}
