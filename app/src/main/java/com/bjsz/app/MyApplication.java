package com.bjsz.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;


/**
 * 当前应用的 Application
 * @author enmaoFu
 * @date 2016-12-23
 */
public class MyApplication extends Application {

    private static String TAG = "print";//设置日志框架打印的TAG

    @Override
    public void onCreate() {
        super.onCreate();

        /**
         * init将TAG初始化
         * logLevel 默认是FULL，打印日志
         * NONE为不打印日志，上线后可设置
         */
        Logger.init(TAG);
        Logger.v("Init Application...");
        initFresco();

    }

    /**
     * 初始化 Fresco 图片异步加载缓存框架
     * 使用例：
     * 1.在xml布局文件中, 加入命名空间
     *   xmlns:fresco="http://schemas.android.com/apk/res-auto"
     * 2.加入SimpleDraweeView:
     *   <com.facebook.drawee.view.SimpleDraweeView
            android:id="@+id/my_image_view"
            android:layout_width="130dp"
            android:layout_height="130dp"
            fresco:placeholderImage="@drawable/my_drawable"
        />
       3.开始加载图片:
         Uri uri = Uri.parse("https://raw.githubusercontent.com/facebook/fresco/gh-pages/static/logo.png");
         SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
         draweeView.setImageURI(uri);
     */
    public void initFresco(){

        Fresco.initialize(this);

    }

}
