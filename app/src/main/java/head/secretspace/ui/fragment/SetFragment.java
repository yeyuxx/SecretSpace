package head.secretspace.ui.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.broadcastreceiver.MyTimeBroadcast;
import head.secretspace.service.DelService;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.ui.activity.login.ModifyPasswordActivity;

/**
 * Created by HEAD on 2017/5/30.
 */

public class SetFragment extends Fragment {

    @InjectView(R.id.ly_modify_pw)
    LinearLayout lyModifyPw;
    @InjectView(R.id.sw_modify_zw)
    Switch swModifyZw;
    @InjectView(R.id.ly_modify_copy)
    LinearLayout lyModifyCopy;
    @InjectView(R.id.sw_modify_jp)
    Switch swModifyJp;

    int yourChoice;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set, container, false);
        ButterKnife.inject(this, view);
        preferences = getActivity().getSharedPreferences("ZW", Context.MODE_PRIVATE);
        editor = preferences.edit();


        init();
        return view;

    }

    private void init() {
        swModifyZw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    editor.putBoolean("zhiwen", true);
                    editor.commit();

                } else if (isChecked == false) {
                    editor.putBoolean("zhiwen", false);
                    editor.commit();
                }
            }
        });
        swModifyJp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    editor.putBoolean("jie", true);
                    editor.commit();

                } else if (isChecked == false) {
                    editor.putBoolean("jie", false);
                    editor.commit();
                }
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (preferences.getBoolean("zhiwen", true) == true) {
            swModifyZw.setChecked(true);
        } else if (preferences.getBoolean("zhiwen", true) == false) {
            swModifyZw.setChecked(false);
        }
        if (preferences.getBoolean("jie", true) == true) {
            swModifyJp.setChecked(true);
        } else if (preferences.getBoolean("jie", true) == false) {
            swModifyJp.setChecked(false);
        }
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.ly_modify_pw, R.id.sw_modify_zw, R.id.ly_modify_copy, R.id.sw_modify_jp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ly_modify_pw:
                startActivity(new Intent(getContext(), ModifyPasswordActivity.class));
                break;
            case R.id.sw_modify_zw:
                break;
            case R.id.ly_modify_copy:
                showSingleChoiceDialog();
                break;
            case R.id.sw_modify_jp:
                break;
        }


    }
    private void showSingleChoiceDialog(){
        final String[] items = {getResources().getString(R.string.threeminutes)
                ,getResources().getString(R.string.fiveminutes)
                ,getResources().getString(R.string.tenminutes)
                ,getResources().getString(R.string.fifteenminutes)};
        yourChoice = 0;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(getContext());
        singleChoiceDialog.setTitle("请选择");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            Intent timeService1 = new Intent(getContext(), DelService.class);

                            switch (yourChoice){
                                case 0:
                                    getActivity().stopService(timeService1);
                                    isService(5000);
                                    break;
                                case 1:
                                    Intent timeService2 = new Intent(getContext(), DelService.class);

                                    getActivity().stopService(timeService2);
                                    isService(10000);
                                    break;
                                case 2:
                                    Intent timeService3 = new Intent(getContext(), DelService.class);

                                    getActivity().stopService(timeService3);
                                    isService(20000);
                                    break;
                                case 3:

                                    getActivity().stopService(timeService1);
                                    break;
                            }
                        }
                    }
                });
        singleChoiceDialog.show();
    }
    private void isService(int i){
        editor.putInt("Time", i);
        editor.commit();
            Intent timeService = new Intent(getContext(), DelService.class);
            getActivity().startService(timeService);

    }
}
