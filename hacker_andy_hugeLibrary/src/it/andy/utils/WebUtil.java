package it.andy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

//常用的工具方法
public class WebUtil {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("ddhhmmssSSS");
	/**
	 * 生成唯一的32位字符串,通常从作为主键
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获取生成的订单ID ： User.hashCode+ddHHmmssSSS
	 * @param obj
	 * @return
	 */
	public static String getOrderId(Object obj){
		return obj.hashCode()+sdf.format(new Date());
	}
}
