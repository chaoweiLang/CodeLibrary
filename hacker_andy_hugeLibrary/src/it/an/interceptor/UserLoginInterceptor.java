package it.an.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import sun.security.x509.AVA;

public class UserLoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		try {
			String method = invocation.getProxy().getMethod();
			
			if("login".equals(method)){
				return invocation.invoke();
			}	
			return "input";
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
