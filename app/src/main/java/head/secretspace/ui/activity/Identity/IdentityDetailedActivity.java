package head.secretspace.ui.activity.Identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.adapter.BankDetailedAdapter;
import head.secretspace.adapter.IdentityDetailedAdapter;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.entity.AddIdentityCard;
import head.secretspace.ui.activity.bankcarditem.BankCopyActivity;
import head.secretspace.ui.activity.bankcarditem.BankDetailedActivity;
import head.secretspace.ui.view.NestedListView;
import head.secretspace.utils.flyn.Eyes;

/**
 * Created by HEAD on 2017/11/24.
 */

public class IdentityDetailedActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener  {
    private NestedListView nlvBank;

    private IdentityDetailedAdapter identityDetailedAdapter;

    public static Long IDENTITYID;

    private List<AddIdentityCard> data=new ArrayList<>();
    private List<AddIdentityCard> list=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identity_detailed);
        DaoUtils.init(this);
        initView();
    }

    private void initView() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.idcard_toolbar);
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
        list=DaoUtils.getIdentityCardManager().QueryAll(AddIdentityCard.class);
        nlvBank= (NestedListView) findViewById(R.id.lv_detailed_idcard);
        identityDetailedAdapter=new IdentityDetailedAdapter(this,list);
        nlvBank.setAdapter(identityDetailedAdapter);
        nlvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDENTITYID=DaoUtils.getIdentityCardManager().QueryAll(AddIdentityCard.class).get(position).getId();
                Intent intent=new Intent(IdentityDetailedActivity.this,IdentityCopyActivity.class);
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
        list = DaoUtils.getIdentityCardManager().QueryAll(AddIdentityCard.class);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().startsWith(newText) || (list.get(i).getName()).indexOf(newText) != -1) {
                data.add(list.get(i));
                identityDetailedAdapter = new IdentityDetailedAdapter(this, data);
                nlvBank.setAdapter(identityDetailedAdapter);
                nlvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        IDENTITYID = data.get(position).getId();
                        startActivity(new Intent(IdentityDetailedActivity.this, IdentityCopyActivity.class));
                        finish();
                    }
                });
            }
            if (!TextUtils.isEmpty(newText)) {
                identityDetailedAdapter = new IdentityDetailedAdapter(this, data);
                nlvBank.setAdapter(identityDetailedAdapter);
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
        nlvBank.setTextFilterEnabled(false);
        return true;
    }
}
