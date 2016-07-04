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
 * Created by Administrator on 2016/7/3.
 */
public class MyVideoView extends SurfaceView {
    private SurfaceHolder surfaceHolder;
    private PopupWindow mWindow;
    private Context   mContext;
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

    private void init(Context context){
        getHolder().addCallback(callback);
        mContext=context;
        myControl= LayoutInflater.from(context).inflate(R.layout.my_window,null);
        mWindow=new PopupWindow(myControl, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mWindow.setFocusable(true);
        mWindow.setOutsideTouchable(true);
        mWindow.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
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

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener listener){
        seekBar.setOnSeekBarChangeListener(listener);
    }

    public void setMax(int max){
     seekBar.setMax(max);

    }
    public void setProgress(int progress){
    seekBar.setProgress(progress);
    }
}
