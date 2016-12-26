package com.bjsz.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * 数据的分享 保存到数据下的东西 保存到本地目录下
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BasePreference {

	private SharedPreferences mPerferences;

	/**
	 * 初始化数据
	 * @param context
	 */
	public BasePreference(Context context) {
		mPerferences = PreferenceManager.getDefaultSharedPreferences(context);
	}

	/**
	 * 根据键值 获取对应的数据 如果没有 就默认值为空
	 * @param Key
	 * @return
	 */
	public String getString(String Key) {
		return mPerferences.getString(Key, "");
	}

	/**
	 * 根据键值 获取对应的数据 如果没有 就默认值为空
	 * @param key
	 * @return
	 */
	public Set<String> getSet(String key){
		Set<String> roomid = null;
		return mPerferences.getStringSet(key, roomid);
	}

	/**
	 * 保存 数据
	 * @param Key
	 * @param Values
	 */
	public void setString(String Key, String Values) {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.putString(Key, Values);
		Editor.commit();
	}

	/**
	 * 保存集合数据
	 * @param key
	 * @param values
	 */
	public void setList(String key, Set<String> values){
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.putStringSet(key, values);
		Editor.commit();
	}

	/**
	 * 保存 boolean类型的数据
	 * @param Key
	 * @param value
	 */
	public void setBoolean(String Key, Boolean value) {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.putBoolean(Key, value);
		Editor.commit();
	}

	/**
	 * 根据键值返回boolean类型数据
	 * @param key
	 * @return
	 */
	public boolean getBoolean(String key) {
		return mPerferences.getBoolean(key, false);
	}

	/**
	 * 移除数据
	 */
	public void removePreference() {
		SharedPreferences.Editor Editor = mPerferences.edit();
		Editor.clear();
		Editor.commit();
	}

	/**
	 * 移除指定的数据
	 */
	public void removeData(String key){
		SharedPreferences.Editor editor = mPerferences.edit();
		editor.remove(key);
		editor.commit();
	}
}
