   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>find</title>
</head>
<body>
	<s:form action="publish" enctype="multipart/form-data">
		<s:textfield name="kidPublishForm.userID" label="用户ID" />
		<s:textfield name="kidPublishForm.kidName" label="姓名" />
		<s:textfield name="kidPublishForm.kidAge" label="年龄" />
		<s:textfield name="kidPublishForm.lostTime" label="丢失时间" />
		<s:textfield name="kidPublishForm.lostPlace" label="丢失地点" />
		<s:textfield name="kidPublishForm.clothes_up" label="上身着装" />
		<s:textfield name="kidPublishForm.clothes_down" label="下身着装" />
		<s:textfield name="kidPublishForm.clothes_shoes" label="鞋子" />
		<s:textarea name="kidPublishForm.appearanceDescription" label="外貌描述" cols="17" rows="5"/>
		<s:textarea name="kidPublishForm.addedInfo" label="附加信息" cols="17" rows="5"/>
		<s:textfield name="kidPublishForm.contactInfo" label="联系方式" />
		<s:file name="kidPublishForm.kidPhotos" label="照片" accepet="image/*" />
		<s:submit value="发布"></s:submit>
	</s:form>
</body>
</html>