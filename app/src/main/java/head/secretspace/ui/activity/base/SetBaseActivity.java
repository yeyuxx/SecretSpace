package head.secretspace.ui.activity.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import head.secretspace.R;

/**
 * Created by HEAD on 2017/6/5.
 */

public abstract class SetBaseActivity extends Activity{
    private TextView tvTitle;
    private Button btTitleClose,btTitleSave;
    private ImageView imTitleClose,imTitleSave;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.inject(this);
        initView();
        initData();
        setTitleLayout();

    }

    private void setTitleLayout(){
        tvTitle= (TextView) findViewById(R.id.tv_title);
        btTitleClose= (Button) findViewById(R.id.bt_title_close);
        btTitleSave= (Button) findViewById(R.id.bt_title_save);
        imTitleClose= (ImageView) findViewById(R.id.im_title_close);
        imTitleSave= (ImageView) findViewById(R.id.im_title_save);
        tvTitle.setText(getLayoutTitle());
        btTitleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveData();
            }
        });

        imTitleSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveData();
            }
        });

        btTitleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFinish();
            }
        });

        imTitleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFinish();
            }
        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getBackLogin();
    }


    protected abstract void getBackLogin();

    protected abstract void getFinish();


    protected abstract void onSaveData();

    protected abstract String getLayoutTitle();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayoutId();


}
