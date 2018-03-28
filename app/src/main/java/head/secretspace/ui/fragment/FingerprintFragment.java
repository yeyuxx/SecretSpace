package head.secretspace.ui.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import head.secretspace.R;

/**
 * Created by HEAD on 2017/10/14.
 */

public class FingerprintFragment extends Fragment {

    @InjectView(R.id.zwti)
    TextView zwti;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fingerprint_login, container, false);
        preferences = getActivity().getSharedPreferences("ZW", Context.MODE_PRIVATE);
        editor = preferences.edit();
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (preferences.getBoolean("zhiwen", true) == false) {
            zwti.setText(R.string.zwts);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
