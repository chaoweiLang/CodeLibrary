package it.andy.utils;

import java.util.List;
/*
 * 用于如兴趣爱好的数据回显，数据库为一个String类型，有逗号分开，而标签需要的是list
 */
public class ConverToList_hobby {
	
	public void ConverToList(List<String> list,String hobby){
		
		if(hobby == null){
			return;
		}
		String[] str = hobby.split(",");
		if(str!=null && str.length>0){
			for(String s:str){
				list.add(s.trim());
			}
		}
		
	}
	
}
