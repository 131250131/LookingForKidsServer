<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登陆成功</title>
</head>
<body>
    登陆成功！
    <br>
    <s:a href="/LookingForKidsServer/message/publish/publish.jsp"> 发布走失儿童信息</s:a>
    <br>
    <s:a href="/LookingForKidsServer/message/contact/contact.jsp"> 发布疑似走失儿童信息</s:a>
</body>
</html>