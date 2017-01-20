package com.bjsz.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;

import com.bjsz.app.R;
import com.bjsz.app.utils.BaseImmersedStatusbarUtils;
import com.bjsz.app.utils.BasePreference;
import com.orhanobut.logger.Logger;

/**
 * 引导页类
 * @author fuenmao
 */
public class GuideActivity extends Activity {
	private Handler mHandler=new Handler();
	private BasePreference basePreference;//本地存储
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view=View.inflate(this, R.layout.start_activity, null);
		setContentView(view);
		View topView = findViewById(R.id.lin);
		BaseImmersedStatusbarUtils.initAfterSetContentView(this, topView);
		basePreference = new BasePreference(GuideActivity.this);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.alpha);
		view.startAnimation(animation);
		animation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mHandler.postDelayed(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						goHome();
					}

				}, 1000);
			}
		});
	}

	private void goHome() {
		Intent intent=new Intent();
		String uid = basePreference.getString("uid");
		Logger.v("uid是"+uid);
		if(uid.equals("")){
			intent.setClass(GuideActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}else{
			intent.setClass(GuideActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
		//String username = lyp.getString("ru_user");
		/*if(!username.equals("") && !username.equals(null)){
			Intent intent=new Intent();
			intent.setClass(GuideActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}else{
			Intent intent=new Intent();
			intent.putExtra("bs", "g");
			intent.setClass(GuideActivity.this, LoginActivity.class);
			startActivity(intent);
			finish();
		}*/
	}
}
