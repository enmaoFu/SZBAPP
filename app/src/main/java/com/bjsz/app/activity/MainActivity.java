package com.bjsz.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjsz.app.R;
import com.bjsz.app.base.BaseFragmentActivity;
import com.bjsz.app.fragments.archives.ArchivesFragment;
import com.bjsz.app.fragments.data.DataFragment;
import com.bjsz.app.fragments.home.HomeFragment;
import com.bjsz.app.fragments.my.MyFragment;
import com.bjsz.app.utils.BasePreference;
import com.bjsz.app.utils.ExampleUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 主页面
 * @author enmaoFu
 * @date 2016-12-26
 */
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

    //记录是否有首次按键
    private boolean mBackKeyPressed = false;

    //底部菜单的布局
    private RelativeLayout home,data,archives,my;

    //底部菜单需要的图片
    private ImageView home_img,data_img,archives_img,my_img;

    //底部菜单的字
    private TextView home_text,data_text,archives_text,my_text;

    private FrameLayout contentf;

    private FragmentManager fm;

    private HomeFragment hf;//首页fragemnt

    private DataFragment df;//健康数据fragment

    private ArchivesFragment af;//健康档案fragment

    private MyFragment mf;//个人中心fragment

    private int textcolor,textcolor1;//底部菜单字体颜色

    private BasePreference basePreference;//本地存储

    public static boolean isForeground = false;

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void initView() {
        contentf = (FrameLayout)findViewById(R.id.content_frame);
        home = (RelativeLayout)findViewById(R.id.home);
        data = (RelativeLayout)findViewById(R.id.data);
        archives = (RelativeLayout)findViewById(R.id.archives);
        my = (RelativeLayout)findViewById(R.id.my);
        home_img = (ImageView)findViewById(R.id.home_img);
        data_img = (ImageView)findViewById(R.id.data_img);
        archives_img = (ImageView)findViewById(R.id.archives_img);
        my_img = (ImageView)findViewById(R.id.my_img);
        home_text = (TextView)findViewById(R.id.home_text);
        data_text = (TextView)findViewById(R.id.data_text);
        archives_text = (TextView)findViewById(R.id.archives_text);
        my_text = (TextView)findViewById(R.id.my_text);

        home.setOnClickListener(this);
        data.setOnClickListener(this);
        archives.setOnClickListener(this);
        my.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        registerMessageReceiver();  // used for receive msg
        basePreference = new BasePreference(this);
        textcolor = Color.parseColor("#50BDFE");
        textcolor1 = Color.parseColor("#505050");
        /*basePreference.setString("identificationPerson","0");//表示未缓存基本信息身高体重
        basePreference.setString("identificationHabit","0");//表示未缓存生活习惯数据*/
        fm = getSupportFragmentManager();
        selectTab(0);
    }

    @Override
    protected void initActionBar() {

    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }


    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }

    private MessageReceiver mMessageReceiver;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.jpushdemo.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";

    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                String messge = intent.getStringExtra(KEY_MESSAGE);
                String extras = intent.getStringExtra(KEY_EXTRAS);
                StringBuilder showMsg = new StringBuilder();
                showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                if (!ExampleUtil.isEmpty(extras)) {
                    showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                }
            }
        }
    }

    /**
     * 菜单切换
     * @param postion 选择了第几个fragement
     */
    public void selectTab(int postion){

        FragmentTransaction ft = fm.beginTransaction();

        switch (postion) {
            case 0:
                if(hf == null){
                    hf = new HomeFragment();
                    ft.replace(R.id.content_frame, hf);
                }else{
                    ft.replace(R.id.content_frame, hf);
                }

                /*textcolor = Color.parseColor("#777777");
                textcolor1 = Color.parseColor("#fa7121");

                relacolor = Color.parseColor("#f4f4f4");
                relacolor1 = Color.parseColor("#ffffff");

                home.setBackgroundColor(relacolor);
                data.setBackgroundColor(relacolor1);
                archives.setBackgroundColor(relacolor1);
                my.setBackgroundColor(relacolor1);*/

                home_text.setTextColor(textcolor);
                data_text.setTextColor(textcolor1);
                archives_text.setTextColor(textcolor1);
                my_text.setTextColor(textcolor1);

                home_img.setBackgroundResource(R.mipmap.ic_home_img_suc);
                archives_img.setBackgroundResource(R.mipmap.ic_archives_img);
                data_img.setBackgroundResource(R.mipmap.ic_data_img);
                my_img.setBackgroundResource(R.mipmap.ic_my_img);

                break;

            case 1:
                if(df == null){
                    df = new DataFragment();
                    ft.replace(R.id.content_frame, df);
                }else{
                    ft.replace(R.id.content_frame, df);
                }

                /*textcolor = Color.parseColor("#777777");
                textcolor1 = Color.parseColor("#fa7121");

                relacolor = Color.parseColor("#f4f4f4");
                relacolor1 = Color.parseColor("#ffffff");

                home.setBackgroundColor(relacolor1);
                data.setBackgroundColor(relacolor);
                archives.setBackgroundColor(relacolor1);
                my.setBackgroundColor(relacolor1);*/

                home_img.setBackgroundResource(R.mipmap.ic_home_img);
                archives_img.setBackgroundResource(R.mipmap.ic_archives_img);
                data_img.setBackgroundResource(R.mipmap.ic_data_img_suc);
                my_img.setBackgroundResource(R.mipmap.ic_my_img);

                home_text.setTextColor(textcolor1);
                data_text.setTextColor(textcolor);
                archives_text.setTextColor(textcolor1);
                my_text.setTextColor(textcolor1);

                break;

            case 2:

                if(af == null){
                    af = new ArchivesFragment();
                    ft.replace(R.id.content_frame, af);
                }else{
                    ft.replace(R.id.content_frame, af);
                }

                /*textcolor = Color.parseColor("#777777");
                textcolor1 = Color.parseColor("#fa7121");

                home_text.setTextColor(textcolor);
                data_text.setTextColor(textcolor);
                archives_text.setTextColor(textcolor1);
                my_text.setTextColor(textcolor);

                relacolor = Color.parseColor("#f4f4f4");
                relacolor1 = Color.parseColor("#ffffff");

                home.setBackgroundColor(relacolor1);
                data.setBackgroundColor(relacolor1);
                archives.setBackgroundColor(relacolor);
                my.setBackgroundColor(relacolor1);*/

                home_img.setBackgroundResource(R.mipmap.ic_home_img);
                archives_img.setBackgroundResource(R.mipmap.ic_archives_img_suc);
                data_img.setBackgroundResource(R.mipmap.ic_data_img);
                my_img.setBackgroundResource(R.mipmap.ic_my_img);

                home_text.setTextColor(textcolor1);
                data_text.setTextColor(textcolor1);
                archives_text.setTextColor(textcolor);
                my_text.setTextColor(textcolor1);

                break;

            case 3:

                if(mf == null){
                    mf = new MyFragment();
                    ft.replace(R.id.content_frame, mf);
                }else{
                    ft.replace(R.id.content_frame, mf);
                }

                /*textcolor = Color.parseColor("#777777");
                textcolor1 = Color.parseColor("#fa7121");

                home_text.setTextColor(textcolor);
                data_text.setTextColor(textcolor);
                archives_text.setTextColor(textcolor);
                my_text.setTextColor(textcolor1);

                relacolor = Color.parseColor("#f4f4f4");
                relacolor1 = Color.parseColor("#ffffff");

                home.setBackgroundColor(relacolor1);
                data.setBackgroundColor(relacolor1);
                archives.setBackgroundColor(relacolor1);
                my.setBackgroundColor(relacolor);*/

                home_img.setBackgroundResource(R.mipmap.ic_home_img);
                archives_img.setBackgroundResource(R.mipmap.ic_archives_img);
                data_img.setBackgroundResource(R.mipmap.ic_data_img);
                my_img.setBackgroundResource(R.mipmap.ic_my_img_suc);

                home_text.setTextColor(textcolor1);
                data_text.setTextColor(textcolor1);
                archives_text.setTextColor(textcolor1);
                my_text.setTextColor(textcolor);

                break;
        }

        ft.commit();

    }

    /**
     * 事件监听
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                selectTab(0);
                break;

            case R.id.data:
                selectTab(1);
                break;

            case R.id.archives:
                selectTab(2);
                break;

            case R.id.my:
                selectTab(3);
                break;
        }
    }

    /**
     * 监听后退键，点击两次退出APP
     */
    @Override
    public void onBackPressed() {
        if(!mBackKeyPressed){
            showToast("再按一次退出应用");
            mBackKeyPressed = true;
            //延时两秒，如果超出则擦除第一次按键记录
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }
            }, 2000);
        }else{
            //退出程序
            this.finish();
            System.exit(0);
        }
    }

}
