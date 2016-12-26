package com.bjsz.app.utils;

import android.os.Environment;

/**
 * 检查是否存在SD卡
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseIsSdcard {
	/**
	 * 检查是否存在SDCard
	 * @return
	 */
	public static boolean hasSdcard(){
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			return true;
		}else{
			return false;
		}
	}
}
