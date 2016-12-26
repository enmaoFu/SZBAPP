package com.bjsz.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 此类用于验证各种格式
 * @author enmaoFu
 * @@date 2016-12-23
 */
public class BaseVerification {

	/**
	 * 判断手机格式是否正确
	 * [1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位
	 * @param mobiles 手机号
	 * @return 返回真则手机号正确，返回假则手机号错误
	 */
	public static boolean isMobileNO(String mobiles){
		Pattern p = Pattern.compile("[1][358]\\d{9}");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * 判断邮箱格式是否正确
	 * @param email 邮箱号
	 * @return 返回真则邮箱号正确，返回假则邮箱号错误
	 */
	public static boolean isEmail(String email){
		String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
}

