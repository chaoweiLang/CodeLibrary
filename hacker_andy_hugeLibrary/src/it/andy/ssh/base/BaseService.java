package it.andy.ssh.base;


import it.andy.ssh.utils.Condition;
import it.andy.ssh.utils.PageBean;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 	注意注意 1:protected的用法是，所有继承该类的子类都有权访问父类声明过的属性。
 * 			2:这里没有写findAllByCondition的业务方法，再子接口写吧，因为每次条件都不一样	
 * 	
 * @author User
 *
 * @param <T>
 */
public class BaseService<T> implements IBaseService<T> {
	
	private String Tname;
	private Class<T> clazz;
	
	//注入			
	protected IBaseDao baseDao;
	public void setBaseDao(IBaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public IBaseDao getBaseDao() {
		return baseDao;
	}
	
	//-----------------------------------------------
	
	
	//反射获取T的simpleName
	public BaseService(){
			Type type = this.getClass().getGenericSuperclass();
			ParameterizedType pt = (ParameterizedType)type;
			Type[] types = pt.getActualTypeArguments();
			clazz =(Class<T>) types[0];
			Tname = clazz.getSimpleName();
	}
	
	public void save(T t) {
		baseDao.save(t);
	}

	public boolean delete(int id) {
		boolean flag = false;
		if(id>0){
		baseDao.delete(id);
		flag = true;
		}
		return flag;
	}

	public void update(T t) {
		baseDao.update(t);
	}



	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return baseDao.findAll();
	}

	public T findById(Serializable id) {
		return (T) baseDao.findById(id);
	}


	/**
	 * 尽量约束数据库表的字段未name，不然要动态拼字段。
	 */
	public PageBean<T> fingByCon(Condition con , int currentPage,int count) {
		if(con==null){
			con = new Condition(); //第一次访问
		}
		if(currentPage<1){
			currentPage = 1;
		}
		if(count<1){
			count = 2;
		}
		//first limit
		int index = (currentPage-1) * count;
			
		//处理查询条件
		StringBuffer sb = new StringBuffer();
		
		//拼String 
		sb.append(" where 1=1 ");
		List<String> values= new ArrayList<String>();
		
		//组装hql语句
		if(StringUtils.isNotBlank(con.getDeptName())){
			sb.append(" and deptName like ?");
			values.add("%"+con.getDeptName()+"%");
		}
		if(StringUtils.isNotBlank(con.getName())){
			sb.append(" and name like ?");
			values.add("%"+con.getName()+"%");
		}
		if(StringUtils.isNotBlank(con.getAddress())){
			sb.append(" and address like ?");
			values.add("%"+con.getAddress()+"%");
		}
		if(StringUtils.isNotBlank(con.getEmail())){
			sb.append(" and email like ?");
			values.add("%"+con.getEmail()+"%");
		}
		
		//终极查询语句
		String c_hql = "from "+ Tname + sb.toString();
		
		PageBean<T> bean = new PageBean<T>();
		//查询并封装data
		List<T> list = baseDao.findByPage(c_hql, values, index, count);
		bean.setPageData(list);
		
		//查询总条数
		String t_hql = " select count(*) from " + Tname + sb.toString();
		Long con2 = baseDao.getTotalCount(t_hql, values);
		bean.setTotalCount(con2.intValue());
		
		bean.setCurPage(currentPage);
		bean.setPageCount(count);
		return bean;
	}
	
	public T findById(int id) {
		return (T) this.baseDao.findById(id);
	}

}
