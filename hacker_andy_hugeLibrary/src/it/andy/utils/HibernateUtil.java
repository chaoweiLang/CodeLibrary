package it.andy.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	//初始化session工厂（最好在启动时候创建，单例）
	private static SessionFactory sf;
	static {
		sf = new Configuration().configure().buildSessionFactory();
	}
	
	/**
	 * 对于org.hibernate.classic.Session 的接口继承session接口，有
	 * 一些是被废弃的方法，如find...
	 * @return session
	 */
	public static Session getSession(){
		return sf.getCurrentSession();
	}
}
