package com.bjsz.app.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 根据value获取key
 * @author enmaoFu
 * @date 2016-12-23
 */
public class BaseMapValueGetKey {
	HashMap map;

	public BaseMapValueGetKey(HashMap map) {//初始化操作
		this.map=map;
	}

	public Object getKey(Object value) {
		Object o=null;
		ArrayList all=new ArrayList();//建一个数组用来存放符合条件的KEY值
 

	/* 这里关键是那个entrySet()的方法,它会返回一个包含Map.Entry集的Set对象. Map.Entry对象有getValue和getKey的方法,利用这两个方法就可以达到从值取键的目的了 **/
		Set set=map.entrySet();
		Iterator it=set.iterator();
		while(it.hasNext()) {
			Map.Entry entry=(Map.Entry)it.next();
			if(entry.getValue().equals(value)) {
				o=entry.getKey();
				all.add(o);//把符合条件的项先放到容器中,方便操作
			}
		}
		return all;
	}


	/**
	 * 使用方法
	 *
	 * HashMap map=new HashMap();
	 map.put("1","a");
	 map.put("2","b");
	 map.put("3","c");
	 map.put("4","c");
	 map.put("5","e");
	 Map_ValueGetKey mvg=new Map_ValueGetKey(map);
	 这样就可以获取到c对应的key
	 mvg.getKey("c");
	 */


}