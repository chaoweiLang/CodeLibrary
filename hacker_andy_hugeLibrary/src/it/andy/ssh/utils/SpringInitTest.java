package it.andy.ssh.utils;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  		 //  指定使用Spring的测试用例
@ContextConfiguration("/applicationContext.xml") //  要加载的容器对象
public class SpringInitTest {
	
	// 直接从容器获取对象
//	@Resource    找注解修饰的成员方法
//	public void setStr(String str) {
//		System.out.println(str);
//	}
	
	@Resource   // 找注解修饰的成员变量
	private String str;
	

	// 测试
	@Test
	public void testApp() throws Exception {
	}
}
