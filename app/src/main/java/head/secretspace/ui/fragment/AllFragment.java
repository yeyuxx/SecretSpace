package head.secretspace.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import head.secretspace.R;
import head.secretspace.adapter.FmAllAdapter;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.AddMainValue;
import head.secretspace.ui.activity.Identity.IdentityCardActivity;
import head.secretspace.ui.activity.Identity.IdentityDetailedActivity;
import head.secretspace.ui.activity.bankcarditem.BankDetailedActivity;
import head.secretspace.ui.activity.bankcarditem.BankTypeActivity;
import head.secretspace.ui.activity.passworditem.PasswordAddActivity;
import head.secretspace.ui.activity.passworditem.PasswordDetailedActivity;

/**
 * Created by HEAD on 2017/5/30.
 */

public class AllFragment extends Fragment {
    @InjectView(R.id.all_fragment_lv)
    ListView allFragmentLv;
    @InjectView(R.id.fab1)
    FloatingActionButton fab1;
    @InjectView(R.id.fab2)
    FloatingActionButton fab2;
    @InjectView(R.id.fab3)
    FloatingActionButton fab3;
    @InjectView(R.id.menu_labels_right)
    FloatingActionMenu menuLabelsRight;

    private FmAllAdapter fmAllAdapter;
    private List<AddMainValue> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DaoUtils.init(getContext());
        initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        list = DaoUtils.getAddMainValueManager().QueryAll(AddMainValue.class);
        fmAllAdapter = new FmAllAdapter(getContext(), list);
        allFragmentLv.setAdapter(fmAllAdapter);

    }

    private void initView() {
        menuLabelsRight.setClosedOnTouchOutside(true);
        list = DaoUtils.getAddMainValueManager().QueryAll(AddMainValue.class);
        fmAllAdapter = new FmAllAdapter(getContext(), list);
        allFragmentLv.setAdapter(fmAllAdapter);
        fmAllAdapter.notifyDataSetChanged();
        allFragmentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(getContext(), PasswordDetailedActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(getContext(), BankDetailedActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(getContext(), IdentityDetailedActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.fab1, R.id.fab2, R.id.fab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fab1:
                Intent intent=new Intent(getContext(), PasswordAddActivity.class);
                startActivity(intent);
                menuLabelsRight.close(true);
                break;
            case R.id.fab2:
                Intent intentBC = new Intent(getContext(), BankTypeActivity.class);
                startActivity(intentBC);
                menuLabelsRight.close(true);
                break;
            case R.id.fab3:
                Intent intentIC=new Intent(getContext(), IdentityCardActivity.class);
                startActivity(intentIC);
                menuLabelsRight.close(true);
                break;
        }
    }

}
