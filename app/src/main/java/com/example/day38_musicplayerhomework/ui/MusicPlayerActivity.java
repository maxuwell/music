package com.example.day38_musicplayerhomework.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.day38_musicplayerhomework.MusicService;
import com.example.day38_musicplayerhomework.R;
import com.example.day38_musicplayerhomework.WindowService;
import com.example.day38_musicplayerhomework.bean.MusicPlayerEntity;
import com.example.day38_musicplayerhomework.uri.AppInterfance;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import okhttp3.Call;

import static com.example.day38_musicplayerhomework.MusicService.MUSIC_CURRENT;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_DNAME;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_DURATION;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_FINISH;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_NAME;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_NEXT;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_PERCENT;

public class MusicPlayerActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.activity_music_player_imgId)
    ImageView img;
    @BindView(R.id.activity_music_player_seekBarId)
    SeekBar seekBar;
    @BindView(R.id.activity_music_player_timeId)
    TextView time;
    @BindView(R.id.activity_music_player_previousId)
    ImageView previous;
    @BindView(R.id.activity_music_player_playerControlId)
    ImageView control;
    @BindView(R.id.activity_music_player_nextId)
    ImageView next;
    @BindView(R.id.activity_music_player_timeCountId)
    TextView TimeCount;
    @BindView(R.id.activity_music_player_orderId)
    ImageView Order;
    @BindView(R.id.activity_name)
    TextView name;

    private int orderSign;
    private Bitmap bitmapLoop;
    private Bitmap bitmapOne;
    private Bitmap bitmapShuffle;
    private ArrayList<String> songIdList;
    private int curId;
    private Bitmap bitmapPause;
    private Bitmap bitmapPlay;


    private MusicPlayerEntity datas;
    boolean isPlaying = false;
    UpdateSeekbarProgressReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
        ButterKnife.bind(this);

        //设置图片
        bitmapPause = BitmapFactory.decodeResource(getResources(), R.drawable.desk_pause);
        bitmapPlay = BitmapFactory.decodeResource(getResources(), R.drawable.desk_play);

        bitmapLoop = BitmapFactory.decodeResource(getResources(),R.drawable.play_icn_loop);
        bitmapOne = BitmapFactory.decodeResource(getResources(),R.drawable.play_icn_one);
        bitmapShuffle = BitmapFactory.decodeResource(getResources(),R.drawable.play_icn_shuffle);
        //返回销毁前数据代码
        if(savedInstanceState != null){
            duration = savedInstanceState.getInt("duration");
            orderSign = savedInstanceState.getInt("orderSign");
            orderControl();
            TimeCount.setText(getNeedFormatTime(duration));
            seekBar.setMax(duration);
        }
        //广播
        if(receiver == null) {
            receiver = new UpdateSeekbarProgressReceiver();
            IntentFilter filter = new IntentFilter();
            //filter.addAction(UPDATE_ACTION);
            filter.addAction(MUSIC_CURRENT);
            filter.addAction(MUSIC_DURATION);
            filter.addAction(MUSIC_PERCENT);
            filter.addAction(MUSIC_FINISH);
            filter.addAction(MUSIC_NAME);
            filter.addAction(MUSIC_NEXT);
            registerReceiver(receiver, filter);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(getApplicationContext())) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
        }
    }

    /**
     * 此Activity是singleInstance模式，只调用一次onCreate
     * 数据在onStart处理，并且用重写onNewIntent处理多次的intent传递
     */
    @Override
    protected void onStart() {
        super.onStart();
        WindowService.dismiss(this);
        //接收数据
        songIdList = new ArrayList<>();
        if(getIntent().getStringArrayListExtra("idList")!=null){
            songIdList.addAll(getIntent().getStringArrayListExtra("idList"));
        }
        curId = getIntent().getIntExtra("curId", -1);
        init();
        if(curId > -1) {
            loadData();
        }
    }

    /**
     *  多次对同一个singleInstance的Activity发送Intent需要重写此方法
     *  否则getIntent为最初数据，后面传入的intent会无效
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);

    }

    /**
     * 销毁保存数据
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("orderSign",orderSign);
        outState.putInt("duration",duration);
        super.onSaveInstanceState(outState);
    }

    /**
     * 监听SeekBar滑动，改变当前进度和时间
     * 手抬起后，通知service歌曲快进
     */
    private void init() {
        //seekbar设置拖拽监听
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /**
             * 进度发生变化
             * @param seekBar
             * @param progress
             * @param fromUser:是否是用户手指拖动改变的进度
             */
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    //mediaPlayer.seekTo(progress);
                    if(!TextUtils.isEmpty(url)) {
                        currentTime = progress;
                        time.setText(getNeedFormatTime(currentTime));
                    }
                }
            }

            /**
             * 拖拽中：手指没有离开屏幕
             * @param seekBar
             */
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            /**
             * 拖拽结束：手指已经离开屏幕
             * @param seekBar
             */
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent();
                    intent.setClass(MusicPlayerActivity.this, MusicService.class);
                    intent.setAction("com.lzw.media.NET_MUSIC_SERVICE");
                    intent.putExtra("url", url);
                    intent.putExtra("MSG", MusicService.PROGRESS_CHANGE);
                    intent.putExtra("position", mCurrentPosition);
                    intent.putExtra("progress", currentTime);
                    startService(intent);
                }
            }
        });
        //点击监听
        control.setOnClickListener(this);
        previous.setOnClickListener(this);
        next.setOnClickListener(this);
        Order.setOnClickListener(this);
        Order.setImageBitmap(bitmapLoop);
        orderSign=0;

    }

    /**
     * OkHttp下载数据
     * 同一首歌不切歌继续播放，不是同一个url则切歌
     */
    private void loadData() {
        OkHttpUtils
                .get()
                .url(String.format(AppInterfance.MUSIC_URL, songIdList.get(curId)))
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Gson gson = new Gson();
                        datas = gson.fromJson(response, new TypeToken<MusicPlayerEntity>() {
                        }.getType());
                        Picasso.with(getApplicationContext())
                                .load(datas.getData().getSongList().get(0).getSongPicBig())
                                .transform(new CropCircleTransformation())
                                .into(img);
                        String newUrl = datas.getData().getSongList().get(0).getSongLink();
                        Log.i("ceshi",url+"[-----]"+newUrl);
                        //同一首歌不切歌继续播放，不是同一个url切歌
                        if(TextUtils.isEmpty(url)||!newUrl.equals(url)) {
                            url = newUrl;
                            play();
                            control.setImageBitmap(bitmapPause);
                        }
                        Intent bIntent = new Intent();
                        bIntent.setAction(MUSIC_DNAME);
                        bIntent.putExtra("name",datas.getData().getSongList().get(0).getSongName());
                        sendBroadcast(bIntent);
                        name.setText(datas.getData().getSongList().get(0).getSongName());
                    }
                });
    }
    int mCurrentPosition = 0;
    int currentTime = 0;
    String url;
    int duration = 0;

    /**
     * 通知service播放（从头开始）
     */
    private void play(){
        Intent intent = new Intent();
        intent.setAction("com.nyr.media.MUSIC_SERVICE");
        intent.setClass(this,MusicService.class);
        intent.putExtra("url", url);
        intent.putExtra("position", mCurrentPosition);
        intent.putExtra("MSG", MusicService.PLAY_MSG);
        startService(intent);
        isPlaying = true;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_music_player_previousId://上一曲
                previousSong();
               // play();
                break;
            case R.id.activity_music_player_nextId://下一曲
                nextSong();
                //play();
                break;
            case R.id.activity_music_player_playerControlId://播放控制
                if (isPlaying) {
                    control.setImageBitmap(bitmapPlay);
                    pause();
                } else {
                    control.setImageBitmap(bitmapPause);
                    if(currentTime > 0) {
                        resume();//在原来进度上继续播放
                    }else{
                        play();//从头开始播放
                    }
                }
                break;
            case R.id.activity_music_player_orderId:
                orderControl();
                break;
        }
    }

    //下一曲
    private void nextSong() {
        time.setText("00:00");
        TimeCount.setText("00:00");
        seekBar.setProgress(0);
        //判断循环顺序
        judgeOrder(true);//参数：是否是下一曲
        loadData();
    }

    private void judgeOrder(boolean judge) {
        switch (orderSign){
            case 0://顺序
                Toast.makeText(getApplicationContext(),"顺序",Toast.LENGTH_SHORT).show();
                if(judge){//是下一曲
                    if (curId == songIdList.size() - 1) {
                        curId = 0;
                    } else {
                        curId++;
                    }
                }
                else {
                    if (curId == 0) {
                        curId = songIdList.size() - 1;
                    } else {
                        curId--;
                    }
                }
                break;
            case 1://单曲
                url = null;
                Toast.makeText(getApplicationContext(),"单曲",Toast.LENGTH_SHORT).show();
                break;
            case 2://随机
                Random rand = new Random();
                int i = rand.nextInt(songIdList.size());
                while(i==curId){
                    i=rand.nextInt(songIdList.size());
                }
                curId=i;
                break;
        }
        Log.i("ceshi","curid:"+curId);

    }

    //上一曲
    private void previousSong() {
        seekBar.setProgress(0);
        time.setText("00:00");
        TimeCount.setText("00:00");

        //判断循环顺序
        judgeOrder(false);//参数：是否是下一曲

        loadData();
    }

    //歌曲循环控制
    private void orderControl() {

        switch (orderSign){
            case 0:
                Order.setImageBitmap(bitmapOne);
                orderToast();
                break;
            case 1:
                Order.setImageBitmap(bitmapShuffle);
                orderToast();
                break;
            case 2:
                Order.setImageBitmap(bitmapLoop);
                orderToast();
                break;
        }
        orderSign = (orderSign+1)%3 ;
    }

    /**
     * 歌曲循环吐司
     */
    private void orderToast() {
        String t_word[] = {"单曲循环","随机播放","顺序播放"};
        Display display = getWindowManager().getDefaultDisplay();
        // 获取屏幕高度
        int height = display.getHeight();
        Toast toast = Toast.makeText(getApplicationContext(),t_word[orderSign],Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, height / 5);
        toast.show();
    }
    /**
     * 释放资源
     */
    @Override
    protected void onDestroy() {
        //注销广播
        unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        WindowService.show(MusicPlayerActivity.this);
        super.onStop();
    }

    /**
     * 按返回键不销毁activity
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 定义更新seekbar进度的广播接收器
     */
    class UpdateSeekbarProgressReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(MUSIC_CURRENT)) {
                //当前进度
                currentTime = intent.getIntExtra("currentTime", -1);
                time.setText(getNeedFormatTime(currentTime));
                seekBar.setProgress(currentTime);
            } else if (action.equals(MUSIC_DURATION)) {
                duration= intent.getIntExtra("duration", -1);
                Log.v("dura", duration+"");
                //设置歌曲总时长
                TimeCount.setText(getNeedFormatTime(duration));
                seekBar.setMax(duration);
            }else if(action.equals(MUSIC_PERCENT)){
                int percent=intent.getIntExtra("percent",-1);
                //下载进度
            }else if (action.equals(MUSIC_FINISH)) {
                nextSong();
                play();
            }else if(action.equals(MUSIC_NAME)){
                Intent bIntent = new Intent();
                bIntent.setAction(MUSIC_DNAME);
                bIntent.putExtra("name",datas.getData().getSongList().get(0).getSongName());
                sendBroadcast(bIntent);
            }else if(action.equals(MUSIC_NEXT)){
                nextSong();
            }
        }


    }
    //00:00时间转换
    private String getNeedFormatTime(int time) {
        //time是一个毫秒级的数据
        String timeFormat = "%02d:%02d";
        int totalSecond = time / 1000;
        int minute = totalSecond / 60;
        int second = totalSecond % 60;
        return String.format(timeFormat, minute, second);
    }
    /**
     * 通知service暂停歌曲
     */
    public void pause(){
        Intent intent=new Intent();
        intent.setClass(this, MusicService.class);
        intent.setAction("com.nyr.media.MUSIC_SERVICE");
        intent.putExtra("MSG", MusicService.PAUSE_MSG);
        startService(intent);
        isPlaying = false;
        //isPause = true;
        //playBtn.setBackgroundResource(R.drawable.fm_btn_play);

    }
    /**
     * 通知service继续播放（继续开始）
     */
    private void resume(){
        Intent intent=new Intent();
        intent.setAction("com.nyr.media.MUSIC_SERVICE");
        intent.setClass(this, MusicService.class);
        intent.putExtra("MSG", MusicService.CONTINUE_MSG);
        startService(intent);
        //isPause = false;
        isPlaying = true;
        //playBtn.setBackgroundResource(R.drawable.fm_btn_pause);
    }

    public void stop(){
        Intent intent=new Intent();
        intent.setClass(this, MusicService.class);
        intent.setAction("com.nyr.media.MUSIC_SERVICE");
        intent.putExtra("MSG", MusicService.STOP_MSG);
        startService(intent);
        isPlaying = false;
        //isPause = true;
        //playBtn.setBackgroundResource(R.drawable.fm_btn_play);

    }

}
