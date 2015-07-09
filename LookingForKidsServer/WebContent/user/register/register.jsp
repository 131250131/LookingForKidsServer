<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Register</title>
</head>
<body>
	<s:form action="register">
		<s:textfield name="userRegisterForm.nickName" label="昵称" />
		<s:textfield name="userRegisterForm.email" label="电子邮箱" />
		<s:password name="userRegisterForm.password" label="密码"></s:password>
		<s:textfield name="userRegisterForm.phonenumber" label="手机号码" />
		<s:submit value="注册"></s:submit>
	</s:form>
</body>
</html>