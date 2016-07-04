package com.yztc.wdigth;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.SeekBar;

import com.yztc.myviewplayer.R;

/**
 * 用于视频播放的自定义的控件（surfaceView）
 * Created by Administrator on 2016/7/3.
 */

public class MyVideoView extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    //弹出窗口
    private PopupWindow mWindow;
    private Context   mContext;
    //进度条以及全屏和缩小
    private View     myControl;
    private  SeekBar seekBar;
    private   SurfaceHolder.Callback callback=new SurfaceHolder.Callback(){

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            surfaceHolder=holder;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }
    };
    public MyVideoView(Context context) {
        super(context);
        init(context);
    }

    public MyVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
   // 初始化 PopupWindow 并且 设置它显示的位置
    private void init(Context context){
        getHolder().addCallback(callback);
        mContext=context;
        myControl= LayoutInflater.from(context).inflate(R.layout.my_window,null);
        mWindow=new PopupWindow(myControl, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        // sufaceView点击PopupWindow出现或者消失
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWindow != null && mWindow.isShowing()) {
                    mWindow.dismiss();
                } else {
                    mWindow.showAtLocation(MyVideoView.this, Gravity.BOTTOM, 0, 0);
                }
            }
        });
        seekBar= (SeekBar) myControl.findViewById(R.id.myseekbar);
    }
   // 暴漏给调用者 seekBar 拖动时的监听事件
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener){
        seekBar.setOnSeekBarChangeListener(listener);
    }
   //  设置seekBar最大值
    public void setMax(int max){
     seekBar.setMax(max);

    }
    //  设置seekBar当前进度
    public void setProgress(int progress){
    seekBar.setProgress(progress);
    }
}
