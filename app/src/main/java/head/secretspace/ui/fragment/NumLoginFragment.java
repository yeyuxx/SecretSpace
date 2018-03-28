package head.secretspace.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import head.secretspace.R;
import head.secretspace.db.DaoUtils;
import head.secretspace.entity.SecretspaceUser;
import head.secretspace.ui.activity.MainActivity;
import head.secretspace.utils.AndroidBugWorkaround;

/**
 * Created by HEAD on 2017/10/14.
 */

public class NumLoginFragment extends Fragment {
    @InjectView(R.id.num_login_fragment_tv)
    TextView numLoginFragmentTv;
    @InjectView(R.id.num_login_fragment_ed_password)
    EditText numLoginFragmentEdPassword;
    @InjectView(R.id.num_login_fragment_textInput)
    TextInputLayout numLoginFragmentTextInput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_login, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        DaoUtils.init(getActivity());
        if (isFullScreen(getActivity())) {
            AndroidBugWorkaround.assistActivity(getActivity());
        }

        initView();
        initState();

    }

    private void initView() {
        numLoginFragmentEdPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0 && !s.toString().equals(DaoUtils.getSecretSpacePasswordManager().
                        QueryAll(SecretspaceUser.class).get(0).getPassword())) {
                    numLoginFragmentTv.setText("密码错误");
                } else if (s.length() == 0) {
                    numLoginFragmentTv.setText("请在下方输入密码");
                } else {
                    numLoginFragmentTv.setText("密码正确,正在登录");
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    getActivity().finish();
                }
            }
        });
    }

    private boolean isFullScreen(Activity activity) {
        return (activity.getWindow().getAttributes().flags &
                WindowManager.LayoutParams.FLAG_FULLSCREEN) == WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    private void initState() {
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
