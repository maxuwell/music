package com.example.day38_musicplayerhomework.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.day38_musicplayerhomework.fragment.BaseFragment;

import java.util.List;

/**
 * Created by NYR on 2016/10/26.
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;
    private final String[] titles={"公共频道","音乐人"};

    public MainViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    // 获得指定位置的碎片
    @Override
    public BaseFragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    // 获得指定页的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
