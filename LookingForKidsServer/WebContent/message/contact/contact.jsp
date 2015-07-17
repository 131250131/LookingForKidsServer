<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>contact</title>
</head>
<body>
	<s:form action="contact" enctype="multipart/form-data" method="post">
	    <s:textfield name="suspectedKidForm.userID" label="用户ID" />
		<s:textfield name="suspectedKidForm.time" label="时间" />
		<s:textfield name="suspectedKidForm.place" label="地点" />
		<s:textarea name="suspectedKidForm.description" label="描述" cols="17" rows="5"/>
		<s:file name="file" label="照片1"></s:file>
		<s:file name="file" label="照片2"></s:file>
		<s:file name="file" label="照片3"></s:file>
		<s:submit value="发布"></s:submit>
	</s:form>
</body>
</html>