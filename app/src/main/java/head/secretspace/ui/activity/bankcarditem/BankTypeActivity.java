package head.secretspace.ui.activity.bankcarditem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import head.secretspace.R;
import head.secretspace.adapter.BankTypeAdapter;

/**
 * Created by HEAD on 2017/9/23.
 */

public class BankTypeActivity extends AppCompatActivity {
    private ListView lvBankType;
    private Button btBack;
    public static int TYPE;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_type);
        lvBankType= (ListView) findViewById(R.id.lv_bank_type);
        BankTypeAdapter bankTypeAdapter=new BankTypeAdapter(this,getData1(),R.layout.item_bank_type);
        lvBankType.setAdapter(bankTypeAdapter);

        lvBankType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TYPE=position;
                startActivity(new Intent(BankTypeActivity.this,BankCardActivity.class));
                finish();

            }
        });

        btBack= (Button) findViewById(R.id.bt_bank_type_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public List<Integer> getData1(){
        List<Integer> data=new ArrayList<>();
        data.add(R.drawable.bank1);
        data.add(R.drawable.bank2);
        data.add(R.drawable.bank3);
        data.add(R.drawable.bank4);
        data.add(R.drawable.bank5);
        data.add(R.drawable.bank6);
        return data;
    }



}
