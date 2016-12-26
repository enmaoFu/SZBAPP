package com.bjsz.app.utils;

/**
 * 判断一个字符串是否包含某个字符串中的字符
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseIsHave {

    public static boolean isHave(String strs, String s){
        for (int i = 0; i < strs.length()-1; i++) {
            for (int j = i+1; j < strs.length(); j++) {
                if(s.toLowerCase().contains(strs.toLowerCase().substring(i, j))){
                    return true;
                }
            }
        }
        return false;
    }

}
