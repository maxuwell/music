package com.example.day38_musicplayerhomework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.adapter.RVadapter;
import com.example.day38_musicplayerhomework.bean.GridMusicEntity;
import com.example.day38_musicplayerhomework.ui.MusicListActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by NYR on 2016/10/26.
 */
public class MainGridFragment extends BaseFragment {
    @BindView(R.id.fragment_main_grid_recyclerViewId)
    RecyclerView recyclerView;
    private RVadapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_grid, container, false);
        ButterKnife.bind(this, rootView);
        init();
        return rootView;
    }

    private void init() {
        //实例化适配器
        adapter = new RVadapter(getContext());
        //设置layoutManager
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        //设置item点击监听
        adapter.setListener(new RVadapter.itemOnClickListener() {
            @Override
            public void onClickListener(View itemView, int position) {
                if (adapter.getDatas().get(position).getCh_name()!=null){
                    Intent intent = new Intent(getActivity(), MusicListActivity.class);
                    intent.putExtra("url",adapter.getDatas().get(position).getCh_name());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(),"接口不可用",Toast.LENGTH_LONG).show();
                }

            }
        });
        //绑定适配器
        recyclerView.setAdapter(adapter);

    }



    //加载数据
    public void addAll(List<GridMusicEntity.ResultBean.ChannellistBean> dd) {
        adapter.addAll(dd);
    }
}
