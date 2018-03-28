package head.secretspace.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.mzule.fantasyslide.FantasyDrawerLayout;
import com.github.mzule.fantasyslide.SideBar;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.ui.activity.login.VerifyLoginActivity;
import head.secretspace.ui.fragment.AboutFragment;
import head.secretspace.ui.fragment.AllFragment;
import head.secretspace.ui.fragment.FavoritesFragment;
import head.secretspace.ui.fragment.SetFragment;


/**
 * Created by HEAD on 2017/5/1.
 */

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {
    @InjectView(R.id.main_toolbar_title)
    TextView mainToolbarTitle;
    @InjectView(R.id.fl_main)
    FrameLayout flMain;
    @InjectView(R.id.tv_main_favorites)
    TextView tvMainFavorites;
    @InjectView(R.id.tv_main_all)
    TextView tvMainAll;
    @InjectView(R.id.tv_main_set)
    TextView tvMainSet;
    @InjectView(R.id.tv_main_about)
    TextView tvMainAbout;
    @InjectView(R.id.main_left_sidebar)
    SideBar mainLeftSidebar;
    @InjectView(R.id.main_drawer_layout)
    FantasyDrawerLayout mainDrawerLayout;
    @InjectView(R.id.main_toolbar)
    Toolbar mainToolbar;

    private FragmentManager fragmentManager;

    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        init();
        initMenuFragment();

        replaceFragment(new AllFragment());

    }

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private void init() {
        fragmentManager = getSupportFragmentManager();
        setSupportActionBar(mainToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            final DrawerArrowDrawable indicator = new DrawerArrowDrawable(this);
            indicator.setColor(Color.WHITE);
            getSupportActionBar().setHomeAsUpIndicator(indicator);
            mainDrawerLayout.setScrimColor(Color.TRANSPARENT);
            mainDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    if (((ViewGroup) drawerView).getChildAt(1).getId() == R.id.main_left_sidebar) {
                        indicator.setProgress(slideOffset);
                    }
                }
            });
        }
        /**
         * 侧滑open or close
         */
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                    mainDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mainDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        mainToolbarTitle.setText("");
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_main, fragment);
        transaction.commit();
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.cha);

//        MenuObject send = new MenuObject("搜索");
//        send.setResource(R.drawable.sousuo);

        MenuObject like = new MenuObject("锁定");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.suoding);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("关闭");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.tuichu));
        addFr.setDrawable(bd);


        menuObjects.add(close);
//        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        return menuObjects;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
//            case R.id.context_search:
//                startActivity(new Intent(MainActivity.this, SearchAllDataActivity.class));
//                break;
        }
        return true;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        menuClick(position);
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        menuClick(position);
    }

    private void menuClick(int position) {
        switch (position) {
            case 0:

                break;
            case 1:
                startActivity(new Intent(MainActivity.this, VerifyLoginActivity.class));
                finish();
                break;
            case 2:
                finish();
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        finish();
        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.tv_main_favorites, R.id.tv_main_all, R.id.tv_main_set, R.id.tv_main_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_main_favorites:
                replaceFragment(new FavoritesFragment());
                break;
            case R.id.tv_main_all:
                replaceFragment(new AllFragment());
                break;
            case R.id.tv_main_set:
                replaceFragment(new SetFragment());
                break;
            case R.id.tv_main_about:
                replaceFragment(new AboutFragment());
                break;
        }
    }

    @Override
    protected void onStart() {
//        Intent timeService = new Intent(this, DelService.class);
//        startService(timeService);
//        MyTimeBroadcast myTimeBroadcast=new MyTimeBroadcast();
//        IntentFilter filter1 = new IntentFilter();
//        filter1.addAction("TIME_CHANGED_ACTION");
//        registerReceiver(myTimeBroadcast, filter1);
        super.onStart();
    }
}
//    Intent timeService = new Intent(this, DelService.class);
//    startService(timeService);

