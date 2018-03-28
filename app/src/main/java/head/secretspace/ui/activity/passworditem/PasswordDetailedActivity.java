package head.secretspace.ui.activity.passworditem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.entity.AddMainValue;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.adapter.DetailedPasswordLibraryAdapter;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddPwItem;
import head.secretspace.utils.flyn.Eyes;
import head.secretspace.ui.view.NestedListView;



/**
 * Created by HEAD on 2017/8/9.
 */

public class PasswordDetailedActivity extends AppCompatActivity implements DetailedPasswordLibraryAdapter.CallBackTwo,DetailedPasswordLibraryAdapter.CallBackOne,SearchView.OnQueryTextListener {
    private NestedListView listView;
    private DetailedPasswordLibraryAdapter dplAdapter;

    private List<AddPwItem> data=new ArrayList<>();
    private List<AddPwItem> list=new ArrayList<>();

    public static Long SEARCHID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_password);
        DaoUtils.init(this);
        initView();


    }


    private void initView() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.del_toolbar);
        setSupportActionBar(toolbar);
        Eyes.setStatusBarColor(this, ContextCompat.getColor(this,R.color.black_one));
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
        list=DaoUtils.getAddPwItemManager().QueryAll(AddPwItem.class);
        listView= (NestedListView) findViewById(R.id.lv_detailed_password_library);
        dplAdapter=new DetailedPasswordLibraryAdapter(this,list,this,this);
        listView.setAdapter(dplAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SEARCHID=DaoUtils.getAddPwItemManager().QueryAll(AddPwItem.class).get(position).getId();
                Intent intent=new Intent(PasswordDetailedActivity.this,CopyActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        data.clear();
        list=DaoUtils.getAddPwItemManager().QueryAll(AddPwItem.class);
        for (int i=0;i<list.size();i++){
            if (list.get(i).getName().startsWith(newText)|| (list.get(i).getName()).indexOf(newText)!=-1) {
                data.add(list.get(i));
                dplAdapter = new DetailedPasswordLibraryAdapter(this, data, this, this);
                listView.setAdapter(dplAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        SEARCHID=data.get(position).getId();
                        startActivity(new Intent(PasswordDetailedActivity.this,CopyActivity.class));
                        finish();
                    }
                });
            }

            if (!TextUtils.isEmpty(newText)) {
                dplAdapter = new DetailedPasswordLibraryAdapter(this, data, this, this);
                listView.setAdapter(dplAdapter);
            }
        }
        return true;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        final MenuItem searchItem=menu.findItem(R.id.context_search_item);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        listView.setTextFilterEnabled(false);
        return true;
    }


    @Override
    public void click(View view) {


    }
}
