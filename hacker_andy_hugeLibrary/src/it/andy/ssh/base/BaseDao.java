package it.andy.ssh.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 	该dao必须配合spring和Hibernate的整合获取HibernateTemplate
 * 	通用Dao	abstract 不能new
 * @author User
 * @param <T>	利用了反射泛型获得参数化类型的参数名字
 */
public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {

	private Class<T> clazz;			//Dept.class
	private String className;		//Dept
	
	
	public BaseDao(){
		//这个自己查api，没什么好说
		Type type = this.getClass().getGenericSuperclass(); //BaseDao<Dept>"参数化类型"
		System.out.println(type);
		//Type里面没有方法的
		ParameterizedType pt=(ParameterizedType)type;
		Type[] types = pt.getActualTypeArguments();
		
		clazz = (Class<T>) types[0];
		className = clazz.getSimpleName();
	}
	
	
	public void delete(Serializable id) {
		T t = getHibernateTemplate().get(clazz, id);
		if(t!=null){
			getHibernateTemplate().delete(t);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return getHibernateTemplate().loadAll(clazz);
	}

	public T findById(Serializable id) {
		try {
			return getHibernateTemplate().get(clazz, id);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


	public void save(T t) {
		try {
			getHibernateTemplate().save(t);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public void update(T t) {
		getHibernateTemplate().update(t);
	}


	public T findByCondition(final String hql, final Object... paramvalues) {

			return getHibernateTemplate().execute(new HibernateCallback<T>() {

				public T doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query q = session.createQuery(hql);
					for(int i=0;i<paramvalues.length;i++){
						q.setParameter(i, paramvalues[i]);
					}
					return (T) q.uniqueResult();
				}
			});
	}


	public List<T> findByPage(String c_hql, List<Object> values, int index,
			int count) {
		//注意懒加载问题
		Session session = getSession();
		Query q = session.createQuery(c_hql);
		if(values!=null){
			for(int i=0; i<values.size();i++){
				q.setParameter(i, values.get(i));
			}
		}
		//设置分页
		q.setFirstResult(index);
		q.setMaxResults(count);
		return q.list();
	}


	public Long getTotalCount(String t_hql, List<Object> values) {
		
		Session session = getSession();
		Query q = session.createQuery(t_hql);
		if(values!=null){
			for(int i=0; i<values.size();i++){
				q.setParameter(i, values.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}


	public List<T> findAllByCondition(final String hql,final Object...values){
		return getHibernateTemplate().executeFind(new HibernateCallback<T>(){

			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
					Query q = session.createQuery(hql);
					if(values!= null){
						for(int i=0;i<values.length;i++){
							q.setParameter(i, values[i]);
						}
					}
				return (T)q.list();
			}
		});
		
	}
	
	

}
