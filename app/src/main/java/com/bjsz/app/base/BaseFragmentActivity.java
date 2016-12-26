package com.bjsz.app.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bjsz.app.R;
import com.bjsz.app.dialogs.WaitDialog;
import com.bjsz.app.interfaces.retrofit.service.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 基础的 FragmentActivity 封装类
 * @author enmaoFu
 * @date 2016-12-23
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    /**
     * 子类必须实现的方法
     */
    protected abstract void bindViews();// 初始化视图

    protected abstract void initView();// 初始化视图

    protected abstract void initData();// 初始化数据

    protected abstract void initActionBar();// 初始化标题栏

    protected String TAG;

    private WaitDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        System.gc();
        /** 强制竖屏 *//*
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);*/
        /** 隐藏标题 */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /** 设置系统的输入框的模式 */
        getWindow()
                .setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
                                | WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED);
        super.onCreate(savedInstanceState);
        //super.setContentView(R.layout.activity_base);
        bindViews();
        initView();
        initData();
        initActionBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置Retrofit
     */
    public ApiService initRetrofit(String url){
        //设置超时时间
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //加入超时时间到Retrofit
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        ApiService as = retrofit.create(ApiService.class);
        return as;
    }

    /**
     * 显示对话框
     */
    public void showDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
        if (dialog == null) {
            dialog = new WaitDialog(this);
            dialog.show();
        }
    }

    /**
     * 隐藏对话框
     */
    public void hideDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    /**
     * 发送提示消息
     *
     * @param message
     */
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打印日志
     *
     * @param log
     */
    protected void log(String tag, String log) {
        Log.i(tag, log);
    }

    /**
     * 当activity触屏事件时，隐藏输入法
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        return super.onTouchEvent(event);
    }

    /**
     * 隐藏输入法
     */
    protected void hideSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示输入法
     */
    protected void showSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        imeManager.showSoftInputFromInputMethod(getWindow().getDecorView()
                .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.base_frame_anim_slide_right_in, R.anim.base_frame_anim_slide_right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_frame_anim_slide_right_in, R.anim.base_frame_anim_slide_right_out);
    }

    /**
     * 监听手机上的后退键 (non-Javadoc)
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(0, R.anim.base_frame_anim_back_right_out);
    }

    /**
     * 返回上一级
     */
    protected void backView() {
        finish();
        overridePendingTransition(0, R.anim.base_frame_anim_back_right_out);
    }
}
