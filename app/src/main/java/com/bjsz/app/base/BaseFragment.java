package com.bjsz.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bjsz.app.dialogs.WaitDialog;
import com.bjsz.app.interfaces.retrofit.service.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 基础的 Fragment 封装类
 * @author enmaoFu
 * @date 2016-12-23
 */
public abstract class BaseFragment extends Fragment {

    /** 子类必须继承的方法 */
    protected abstract int bindViews();// 初始化视图

    protected abstract void initView();// 初始化视图组件

    protected abstract void initData();// 初始化数据

    protected abstract void initActionBar();// 初始化标题栏

    private View fragmentview;

    protected String TAG;

    private WaitDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentview = inflater.inflate(bindViews(), container, false);

        return fragmentview;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TAG = getActivity().getClass().getSimpleName();
        initView();
        initActionBar();
        initData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
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
            dialog = new WaitDialog(getActivity());
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
     * 隐藏输入法
     */
    protected void hideSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 显示输入法
     */
    protected void showSoftInput() {
        InputMethodManager imeManager = (InputMethodManager) getActivity()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imeManager.isActive();
        imeManager.hideSoftInputFromWindow(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        imeManager.showSoftInputFromInputMethod(getActivity().getWindow()
                        .getDecorView().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 发送提示消息
     *
     * @param message
     */
    public void showToast(String message) {
        Toast.makeText(getActivity(), message + "~", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载视图组件
     *
     * @param id
     * @return
     */
    protected View findViewById(int id) {
        return fragmentview.findViewById(id);
    }

    /**
     * 打印日志
     *
     * @param log
     */
    protected void log(String tag, String log) {
        Log.i(tag, log);
    }

}
