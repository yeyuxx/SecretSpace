package head.secretspace.ui.activity.bankcarditem;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddBankCard;
import head.secretspace.adapter.BankDetailedAdapter;
import head.secretspace.entity.AddMainValue;
import head.secretspace.entity.AddPwItem;
import head.secretspace.utils.flyn.Eyes;
import head.secretspace.ui.view.NestedListView;

/**
 * Created by HEAD on 2017/10/7.
 */

public class BankDetailedActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private NestedListView nlvBank;

    private BankDetailedAdapter bankDetailedAdapter;

    public static Long BANKID;


    private List<AddBankCard> data=new ArrayList<>();
    private List<AddBankCard> list=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_detailed);
        DaoUtils.init(this);
        initView();

    }

    private void initView() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.bank_toolbar);
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
        list=DaoUtils.getBankCardManager().QueryAll(AddBankCard.class);
        nlvBank= (NestedListView) findViewById(R.id.lv_detailed_bank);
        bankDetailedAdapter=new BankDetailedAdapter(this,list);
        nlvBank.setAdapter(bankDetailedAdapter);
        nlvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BANKID=DaoUtils.getBankCardManager().QueryAll(AddBankCard.class).get(position).getId();
                Intent intent=new Intent(BankDetailedActivity.this,BankCopyActivity.class);
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
        list = DaoUtils.getBankCardManager().QueryAll(AddBankCard.class);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIssuingBank().startsWith(newText) || (list.get(i).getIssuingBank())
                    .indexOf(newText) != -1) {
                data.add(list.get(i));
                bankDetailedAdapter = new BankDetailedAdapter(this, data);
                nlvBank.setAdapter(bankDetailedAdapter);
                nlvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        BANKID = data.get(position).getId();
                        startActivity(new Intent(BankDetailedActivity.this,
                                BankCopyActivity.class));
                        finish();
                    }
                });
            }
            if (!TextUtils.isEmpty(newText)) {
                bankDetailedAdapter = new BankDetailedAdapter(this, data);
                nlvBank.setAdapter(bankDetailedAdapter);
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
