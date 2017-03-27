package com.example.day38_musicplayerhomework;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.day38_musicplayerhomework.ui.MusicPlayerActivity;

import static com.example.day38_musicplayerhomework.MusicService.MUSIC_CURRENT;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_DNAME;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_DURATION;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_NAME;
import static com.example.day38_musicplayerhomework.MusicService.MUSIC_NEXT;

public class WindowService extends Service {
        public static final String ACTION_SHOW = "action_show";
        public static final String ACTION_DISMISS = "action_dismiss";

        public static boolean sIsShow = false;

        private static FloatWindow sFloatWindow;

        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            sFloatWindow = new FloatWindow(getApplicationContext());
        }

        @Override
        public void onDestroy() {
            if (sFloatWindow != null){
                sFloatWindow.destroy();
                sFloatWindow = null;
            }
            super.onDestroy();
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            int result = super.onStartCommand(intent, flags, startId);
            if (intent == null)   {
                return result;
            }
            if (ACTION_SHOW.equals(intent.getAction())) {

                sFloatWindow.show();
            } else if (ACTION_DISMISS.equals(intent.getAction())) {
                sFloatWindow.dismiss();
            }
            return result;
        }

        public static void show(Context context) {
            if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(context))
                return;

            Intent intent = new Intent(context,WindowService.class);
            intent.setAction(ACTION_SHOW);
            context.startService(intent);
            sIsShow = true;
        }

        public static void dismiss(Context context) {
            Intent intent = new Intent(context, WindowService.class);
            intent.setAction(ACTION_DISMISS);
            context.startService(intent);
            sIsShow = false;
        }

        public static void destroy(Context context) {
            Intent intent = new Intent(context, WindowService.class);
            context.stopService(intent);
            sIsShow = false;
        }

        static class FloatWindow implements View.OnTouchListener {

            public FloatWindow(Context context) {
                mContext = context;

                mWindowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

                mParams = new WindowManager.LayoutParams();
                mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
                mParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                mParams.gravity = Gravity.RIGHT | Gravity.TOP;

                mParams.x = 20;
                mParams.y = 48;

                mParams.alpha = 1.0f;
                //mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                //设置悬浮窗宽高
                mParams.width = 400;
               // mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                mParams.height = 300;
                mParams.format = PixelFormat.RGBA_8888;

                mIsShowing = false;

                initViews();
                initReceiver(context);
            }

            public void show() {
                if (mIsShowing) {
                    return;
                }
                mWindowManager.addView(mRootView, mParams);
                mIsShowing = true;
                Intent intent = new Intent();
                intent.setAction(MUSIC_NAME);
                mContext.sendBroadcast(intent);
            }

            public void dismiss() {
                if (!mIsShowing) {
                    return;
                }
                mWindowManager.removeView(mRootView);
                mIsShowing = false;
            }

            public void destroy() {
                dismiss();
                mRootView.setOnTouchListener(null);
                mContext = null;
            }

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mTouchX = event.getRawX();
                mTouchY = event.getRawY() - getStatusBarHeight();  // height of status bar
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mTouchStartX = event.getX();
                        mTouchStartY = event.getY();
                        mStartTimestamp = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        updateViewPosition();
                        break;
                    case MotionEvent.ACTION_UP:
                        float endX = event.getX();
                        float endY = event.getY();
                        boolean isMoved = (Math.abs(endX - mTouchStartX) + Math
                                .abs(endY - mTouchStartY)) > 5;

                        if (!isMoved
                                && System.currentTimeMillis() - mStartTimestamp < 300) {
                            backToJustalk();
                        }
                        break;
                }
                return true;
            }

            // control
            private Context mContext;
            private boolean mIsShowing = false;
            private final WindowManager mWindowManager;
            private final WindowManager.LayoutParams mParams;
            // views
            private View mRootView;
            UpdateSeekbarProgressReceiver receiver;
            private TextView tv_name;
            private Button btn_pre;
            private Button btn_next;
            private Button btn_play;
            private SeekBar seekBar;
            private ImageView iv_next;
            private float mTouchStartX;
            private float mTouchStartY;
            private long mStartTimestamp;
            private float mTouchX;
            private float mTouchY;

            int currentTime = 0;
            int duration = 0;
            @SuppressLint("InflateParams")
            private void initViews() {
                mRootView = LayoutInflater.from(mContext).inflate(R.layout.layout_window, null);
                tv_name = (TextView) mRootView.findViewById(R.id.window_music_name);
             /*   btn_next = (Button) mRootView.findViewById(R.id.window_music_player_nextId);
                btn_play = (Button) mRootView.findViewById(R.id.window_music_player_playerControlId);
                btn_pre = (Button) mRootView.findViewById(R.id.window_music_player_previousId);
               */
                iv_next = (ImageView) mRootView.findViewById(R.id.window_music_player_nextId);
                iv_next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(MUSIC_NEXT);
                        mContext.sendBroadcast(intent);
                    }
                });
                seekBar = (SeekBar) mRootView.findViewById(R.id.window_music_seekbar);
                mRootView.setOnTouchListener(this);

            }
            private void initReceiver(Context context){
                //广播
                if(receiver == null) {
                    receiver = new UpdateSeekbarProgressReceiver();
                    IntentFilter filter = new IntentFilter();
                    //filter.addAction(UPDATE_ACTION);
                    filter.addAction(MUSIC_CURRENT);
                    filter.addAction(MUSIC_DURATION);
                    filter.addAction(MUSIC_DNAME);
                    context.registerReceiver(receiver, filter);
                }
            }

            @SuppressWarnings("deprecation")
            private int getScreenWidth() {
                return mWindowManager.getDefaultDisplay().getWidth();
            }

            private int getStatusBarHeight() {
                Rect rect = new Rect();
                mRootView.getWindowVisibleDisplayFrame(rect);
                return rect.top;
            }

            private void updateViewPosition() {
                if (mIsShowing) {
                    mParams.x = getScreenWidth() - mRootView.getWidth() - ((int) (mTouchX - mTouchStartX));
                    mParams.y = (int) (mTouchY - mTouchStartY);
                    mWindowManager.updateViewLayout(mRootView, mParams);
                }
            }

            private void backToJustalk() {
                Intent intent = new Intent(mContext, MusicPlayerActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                //intent.putExtra(CallActivity.EXTRA_FLOAT_WINDOW_CALL, true);
                mContext.startActivity(intent);
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
                        seekBar.setProgress(currentTime);

                    } else if (action.equals(MUSIC_DURATION)) {
                        duration= intent.getIntExtra("duration", -1);
                        Log.v("dura", duration+"");
                        //设置歌曲总时长
                        seekBar.setMax(duration);
                    }else if(action.equals(MUSIC_DNAME)){
                        if (!TextUtils.isEmpty(intent.getStringExtra("name")))
                            tv_name.setText(intent.getStringExtra("name")+"");
                    }
                }

            }
        }


}
