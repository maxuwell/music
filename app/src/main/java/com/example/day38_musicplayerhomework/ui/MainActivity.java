package com.example.day38_musicplayerhomework.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.adapter.MainViewPagerAdapter;
import com.example.day38_musicplayerhomework.bean.GridMusicEntity;
import com.example.day38_musicplayerhomework.fragment.BaseFragment;
import com.example.day38_musicplayerhomework.fragment.MainGridFragment;
import com.example.day38_musicplayerhomework.uri.AppInterfance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MainActivity extends BaseActivity {

    @BindView(R.id.activity_main_tabLayoutId)
    TabLayout tabLayout;
    @BindView(R.id.activity_main_viewPagerId)
    ViewPager viewPager;

    private List<BaseFragment> fragments;
    private MainViewPagerAdapter adapter;
    private MainGridFragment fragment1;
    private MainGridFragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        //初始化碎片
        fragments = new ArrayList<>();
        fragment1 = new MainGridFragment();
        fragments.add(fragment1);
        fragment2 = new MainGridFragment();
        fragments.add(fragment2);
        //实例化适配器
        adapter = new MainViewPagerAdapter(getSupportFragmentManager(),fragments);
        //绑定适配器
        viewPager.setAdapter(adapter);
        //tab与viewpager绑定
        tabLayout.setupWithViewPager(viewPager);
        //加载数据
        loadData();
    }

    private void loadData() {
        OkHttpUtils
                .get()
                .url(AppInterfance.CHANNEL_URL)
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        Log.d("1606",response);
                        GridMusicEntity datas = gson.fromJson(response, new TypeToken<GridMusicEntity>() {}.getType());
                        fragment1.addAll(datas.getResult().get(0).getChannellist());
                        fragment2.addAll(datas.getResult().get(1).getChannellist());
                    }
                });
    }
}
