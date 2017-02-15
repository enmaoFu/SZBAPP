package com.bjsz.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * 当前应用的 Application
 * @author enmaoFu
 * @date 2016-12-23
 */
public class MyApplication extends Application {

    private static String TAG = "print";//设置日志框架打印的TAG
    public static Map<Integer,String> homeGridviewImageMap = new HashMap<>();//gridview图片数据源

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
        initHomeGridviewImageMap();
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

    /**
     * 初始化首页girdview map数据源
     */
    public void initHomeGridviewImageMap(){
        homeGridviewImageMap.put(R.mipmap.ic_blood_pressure_img,"血压2");
        homeGridviewImageMap.put(R.mipmap.ic_oxygen_img,"血氧4");
        homeGridviewImageMap.put(R.mipmap.ic_blood_sugar_img,"血糖3");
        homeGridviewImageMap.put(R.mipmap.ic_temperature_img,"体温5");
        homeGridviewImageMap.put(R.mipmap.ic_uric_acid_img,"尿酸6");
        homeGridviewImageMap.put(R.mipmap.ic_cholesterol_img,"胆固醇7");
        homeGridviewImageMap.put(R.mipmap.ic_urine_routine_img,"尿常规8");
        homeGridviewImageMap.put(R.mipmap.ic_ecg_img,"心电9");

    }

}
