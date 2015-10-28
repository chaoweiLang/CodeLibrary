package it.andy.ssh.utils;

/**
 * 	条件查询时，封装用户输入的信息
 * @author User
 *
 */
public class Condition {
	
	//此处可以加入更多数据库通用查询字段，设计表的有简称约束可使用的Condition，不用修改Service层
	//对于数据库特殊字段，就要特殊声明了，就要修改service层，主要看程序的要求吧，没办法！可以反射，但是减少了代码的可读性
	private String name;
	private String address;
	private String email;
	private String deptName;		//此处就是因为数据库字段为name，service层能安全毕业！
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
