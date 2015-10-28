package it.andy.ssh.base;


import it.andy.ssh.utils.Condition;
import it.andy.ssh.utils.PageBean;

import java.io.Serializable;
import java.util.List;

/**
 * 	通用的业务逻辑成，base类！
 * 	很可惜，我还没做到级联关系的通用类，但是我觉得解决方案有2：
 * 		1：spring的IOC注入可能解决
 * 		2：反射注解获取字段也可能解决，但这个需要测试api了
 * @author User
 *
 */
public interface IBaseService<T> {
	

	// 添加
	public void save(T t);
	
	// 列表查询
	public List<T> findAll();
	
	//主键查询
	public T findById(Serializable id);
	
	//更新
	public void update(T t);
	
	//主键删除（dao原理不是主键删除）
	public boolean delete(int id) ;
	

	/**
	 * 	模糊条件查询，终极版
	 * 	分页里面包含了总条数查询
	 * @param hql		
	 * @param values
	 * @param index
	 * @param count
	 * @return
	 */
	public PageBean<T> fingByCon(Condition con,int curPage,int pageCount);
	
	//主键查询
	public T findById(int id);
	
	
}
