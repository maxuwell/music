package com.example.day38_musicplayerhomework.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.adapter.RVListAdapter;
import com.example.day38_musicplayerhomework.bean.ListMusicEntity;
import com.example.day38_musicplayerhomework.uri.AppInterfance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MusicListActivity extends BaseActivity {

    @BindView(R.id.activity_music_list_recyclerViewId)
    RecyclerView recyclerView;
    private String url;
    private RVListAdapter adapter;
    private LinearLayoutManager layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        ButterKnife.bind(this);

        //接收数据
        url = getIntent().getStringExtra("url");

        init();
    }



    private void init() {
        //实例化适配器
        adapter = new RVListAdapter(getApplicationContext());
        //设置layoutManager
        layout = new LinearLayoutManager(getApplicationContext());
        layout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layout);
        //设置item点击监听
        adapter.setListener(new RVListAdapter.itemOnClickListener() {
            @Override
            public void onClickListener(View itemView, int position) {
                //Toast.makeText(getApplicationContext(),"pos:"+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MusicListActivity.this,MusicPlayerActivity.class);
                ArrayList<String> idList = new ArrayList<String>();
                for (int i = 0; i < adapter.getDatas().size(); i++) {
                    if (adapter.getDatas().get(i).getSongid()!=null){
                        idList.add(adapter.getDatas().get(i).getSongid());
                    }
                }
                intent.putStringArrayListExtra("idList",idList);
                intent.putExtra("curId",position);
                startActivity(intent);
            }
        });
        //绑定适配器
        recyclerView.setAdapter(adapter);
        //加载数据
        loadData();
    }

    private void loadData() {
        OkHttpUtils
                .get()
                .url(String.format(AppInterfance.LIST_URL,url))
                .build()
                .execute(new StringCallback()
                {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        ListMusicEntity datas = gson.fromJson(response, new TypeToken<ListMusicEntity>() {}.getType());
                        adapter.addAll(datas.getResult().getSonglist());
                    }
                });
    }
}
