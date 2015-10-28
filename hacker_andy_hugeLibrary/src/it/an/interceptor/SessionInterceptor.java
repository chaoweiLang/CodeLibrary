package it.an.interceptor;

import it.andy.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * session管理的拦截器！
 * 每次访问action的时候，创建hibernate中的session会话对象
 * @author User
 *
 *	注意：要在*hbm.xml中设置<property name="hibernate.current_session_context_class">thread</property>
 *
 */
public class SessionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Transaction tx = null;
		Session session = null;
		try {
			//创建Session
			session = HibernateUtil.getSession();
			//开启事务
			tx = session.beginTransaction();
			//放行（action-->service-->dao）
			String result = invocation.invoke();  //action的业务方法返回
			return result;
		} catch (Exception e) {	//如果设置为Throwable，则可精确到配置文件
			//回滚事务
			//事务回滚后，程序会停止的，所以顺序很重要
			e.printStackTrace();
			tx.rollback();
			return "error";		//此行然并卵
		}finally{
			tx.commit();
		}
	}

	
}
