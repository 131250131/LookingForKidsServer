<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>findsimilarkids</title>
</head>
<body>
	<s:form action="findsimilarkids">
		<s:textfield name="userID" label="用户ID"></s:textfield>
		<s:submit value="查看"></s:submit>
	</s:form>
</body>
</html>