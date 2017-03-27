package com.example.day38_musicplayerhomework;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener {
    public MusicService() {
    }

    public MediaPlayer mediaPlayer;
    private int currentTime;
    private int duration;
    private int mCurrentPosition;
    private int msg;
    private boolean isPause;
    private String url;
    private int percent;
    public static final String MUSIC_CURRENT = "com.nyr.action.NET_MUSIC_CURRENT";
    public static final String MUSIC_DURATION = "com.nyr.action.NET_MUSIC_DURATION";
    public static final String MUSIC_PERCENT="com.nyr.action.NET_MUSIC_PERCENT";
    public static final String MUSIC_FINISH = "com.nyr.action.NET_MUSIC_FINISH";
    public static final String MUSIC_NAME = "com.nyr.action.NET_MUSIC_NAME";
    public static final String MUSIC_DNAME = "com.nyr.action.NET_MUSIC_DNAME";
    public static final String MUSIC_NEXT = "com.nyr.action.NET_MUSIC_NEXT";

    public static final int PLAY_MSG= 1;
    public static final int PROGRESS_CHANGE=2;
    public static final int PLAYING_MSG=3;
    public static final int PAUSE_MSG=4;
    public static final int STOP_MSG=5;
    public static final int CONTINUE_MSG=6;
    public static final int QUERY_DURATION = 7;

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                if(mediaPlayer != null) {
                    currentTime = mediaPlayer.getCurrentPosition();
                    Intent intent = new Intent();
                    intent.setAction(MUSIC_CURRENT);
                    intent.putExtra("currentTime", currentTime);
                    sendBroadcast(intent);
                    handler.sendEmptyMessageDelayed(0, 1000);
                    //每隔1秒发一次歌曲进度广播
                }
            }
            if(msg.what==1){
                //歌曲时长
                if(mediaPlayer != null) {
                    duration = mediaPlayer.getDuration();
                    if (duration > 0) {
                        Intent intent = new Intent();
                        intent.setAction(MUSIC_DURATION);
                        intent.putExtra("pos", mCurrentPosition);
                        Log.v("DUA", duration + "");
                        intent.putExtra("duration", duration);
                        sendBroadcast(intent);
                    }
                }
            }
            if(msg.what==2){
                //缓冲百分比
                Intent intent=new Intent();
                intent.setAction(MUSIC_PERCENT);
                intent.putExtra("percent", percent);
                sendBroadcast(intent);
            }
            if (msg.what == 3){
                //歌曲结束，提醒切歌
                Intent intent=new Intent();
                intent.setAction(MUSIC_FINISH);
                sendBroadcast(intent);
            }
        }
    };


    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnPreparedListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void play(int currentTime) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
            handler.sendEmptyMessage(0);
            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(Intent intent, int flags) {
        // TODO Auto-generated method stub
        if(intent==null){
            stopSelf();
        }
        url = intent.getStringExtra("url");
        msg = intent.getIntExtra("MSG", 0);
        mCurrentPosition = intent.getIntExtra("position", -1);
        if (msg == PLAY_MSG) {
            //播放
            play(0);
        }else if (msg == PROGRESS_CHANGE) {
            //进度条滑动，歌曲快进
            currentTime = intent.getIntExtra("progress", -1);
            if (currentTime > 0 && currentTime <= (percent * duration / 100)) {
                mediaPlayer.seekTo(currentTime);
                handler.sendEmptyMessage(1);
                //play(currentTime);
            }
        } else if (msg == PLAYING_MSG) {
            handler.sendEmptyMessage(0);
        } else if (msg == PAUSE_MSG) {
            pause();
        } else if (msg == STOP_MSG) {
            stop();
        } else if (msg == CONTINUE_MSG) {
            resume();
        }else if (msg == QUERY_DURATION){
            handler.sendEmptyMessage(1);
        }
        return;
    }

    private void pause() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPause = true;
        }
    }

    private void resume() {
        if (isPause) {
            mediaPlayer.start();
            isPause = false;
        }
    }

    /**
     * 停止音乐
     */
    private void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            try {
                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    //歌曲准备好之后，开始播放
    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
        //设置播放完毕的监听
        mediaPlayer.setOnCompletionListener(this);
        Intent intent = new Intent();
        intent.setAction(MUSIC_DURATION);
        duration = mediaPlayer.getDuration();
        intent.putExtra("duration", duration);
        Log.v("duran", duration+"");
        sendBroadcast(intent);
    }
    //当前歌曲播放结束
    @Override
    public void onCompletion(MediaPlayer mp) {
        handler.sendEmptyMessage(3);
        Log.i("ceshi","finish");
        mediaPlayer.setOnCompletionListener(null);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int perc) {
        percent=perc;
        handler.sendEmptyMessage(2);
    }

}

