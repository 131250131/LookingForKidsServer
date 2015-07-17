<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆页面</title>
</head>
<body>
	<s:form action="login" method="post">
		<s:textfield name="userLoginForm.account" label="账号"></s:textfield>
		<s:password name="userLoginForm.password" label="密码"></s:password>
		<s:submit value="登录"></s:submit>
	</s:form>
	
	<s:a href="/LookingForKidsServer/user/register.jsp">
	注册用户
	</s:a>

</body>
</html>