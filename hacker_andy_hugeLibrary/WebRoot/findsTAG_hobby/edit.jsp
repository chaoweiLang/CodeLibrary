<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>My JSP 'edit.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
	
	<s:form action="#" method="post">
		<!-- 隐藏域，保存主键id的值 -->
		<s:hidden name="employee.id" id="employee.id"></s:hidden>
	
		<table cellpadding="5" cellspacing="0" border="1" width="60%" align="center">
			
			<tr>
				<td>员工名称</td>
				<td>
					<!-- 
						总结：Struts标签，name有俩作用：
						1.作为表单元素的名称
						2.作为Ognl表达式取值
							相当于给value赋值
					 -->
					<s:textfield name="employee.name"></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td>部门</td>
				<td>
					<%-- 
	  	 			<select name="employee.dept.deptId">
	  	 				<option value="-1">--请选择--</option>
	  	 				迭代....
	  	 				<option value=""></option>
	  	 			</select>
	  	 			 --%>
	  	 			 
	  	 			 <!-- 
	  	 			 	name       下拉列表名称
	  	 			 	headerKey  下拉列表第一个文本内容，对应的实际的值
	  	 			 	headerValue下拉列表第一个文本内容
	  	 			 	list 		下拉列表取值的集合
	  	 			 	listKey	   集合中的对象的那个属性作为实际的值
	  	 			 	listValue集合中对象那个属性作为“显示的值”
	  	 ************	value	 默认选中的key的值
	  	 			 	
	  	 			 	
	  	 			  -->
				<s:select 
						name="employee.dept.deptId"
						headerKey="-1"
						headerValue="--请选择--"
						list="#listDept"
						listKey="deptId"
						listValue="deptName"
				></s:select>
				</td>
			</tr>
			
			<tr>
				<td>生日</td>
				<td>
					<s:date var="birth" name="employee.birth" format="yyyy-MM-dd"/>
					<s:textfield name="employee.birth" value="%{#birth}"></s:textfield>
				</td>
			</tr>
			
			<tr>
				<td>性别</td>
				<td>
					<!-- 
						list 表示取值集合是一个map
							冒号前面是key,后面是value
						listKey：
							实际的值是从map总的key还是value中获取
						listValue
							显示的值是从map中的key还是value中获取
						value
							指定默认值
					
					 -->
					<s:radio name="employee.sex" list="#{'true':'男','false':'女'}" listKey="value" listValue="value"></s:radio>
				</td>
			</tr>
			
			<tr>
				<td>爱好</td>
				<td>
					<!-- list复选框值的集合，是一个list集合； vlaue 表示默认选中的值，注意：是list集合-->
					<s:checkboxlist name="employee.hobby" list="{'游戏','编码','游泳'}" value="%{list}"></s:checkboxlist>
				</td>
			
			</tr>
			
			<tr>
				<td colspan="6">
					<input type="submit" value="保存" onclick="submitForm()">
				</td>
			</tr>
		</table>
	
	</s:form>
	
	<script type="text/javascript">
		
		function submitForm(){
			var id = document.getElementById('employee.id').value;
		
			var frm = document.forms[0];
			
			//判断
			if( id == null || id=="" ){
				//新增
				frm.action = "${pageContext.request.contextPath}/emp_save.action";
			}
			 else {
			// 修改功能
			 frm.action = "${pageContext.request.contextPath}/emp_edit.action";
  	  	 }
  	  	 
  	  	 // 3. 提交
  	  	 frm.submit();
	}
		
		
	</script>



  </body>
</html>
