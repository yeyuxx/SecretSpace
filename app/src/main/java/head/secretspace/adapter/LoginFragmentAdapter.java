package head.secretspace.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by HEAD on 2017/10/14.
 */

public class LoginFragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    public LoginFragmentAdapter( List<Fragment> fragment, FragmentManager fm) {
        super(fm);
        this.fragments=fragment;
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
}
