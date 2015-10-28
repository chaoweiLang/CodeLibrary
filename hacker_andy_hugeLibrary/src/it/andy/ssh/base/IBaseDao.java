package it.andy.ssh.base;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {
	
	/**
	 * 	新增
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 	这个不是主键删除方法，HibernateTemplate提供的是删除是对象删除
	 * @param id
	 * @throws Exception 
	 */
	void delete(Serializable id);

	/**
	 * 	更新
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 	主键查询
	 *  作用域：利于对点击的单体对象进行下一步操作：例如修改，购买等
	 *  
	 * @param id
	 * @return
	 */
	T findById(Serializable id);
	
	/**
	 * 	无条件查询全部，注意懒加载问题，有待测试，加入不加入任何条件，此方法会比findAllByCondition效率高
	 * 	当不作分页查询和模糊查询时，挺好用
	 * @return	无条件限制的list
	 */
	List<T> findAll();
	
	/**
	 * 	根据条件查询：如登录验证，只要带占位符？的都可以
	 * @param hql
	 * @param paramvalues
	 * @return
	 * @throws Exception 
	 */
	T findByCondition(String hql,Object... paramvalues) ;
	
	
	/**
	 * 	条件查询，终极版
	 * @param hql		查询语句
	 * @param values	参数
	 * @param index		curPage
	 * @param count		pageCount
	 * @return
	 */
	List<T> findByPage(String hql,List<Object> values,int index,int count);
	
	/**
	 * 	配合终极版条件查询查询总条数
	 * @param hql
	 * @param values
	 * @return
	 */
	Long getTotalCount(String hql,List<Object> values);
	
	/**
	 *  条件查询全部/模糊搜查：在service层结合Condition完美查询
	 * @return
	 */
	List<T> findAllByCondition(String hql,Object...values);
}
