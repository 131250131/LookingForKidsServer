<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>publish</title>
</head>
<body>
	<s:form action="publish" enctype="multipart/form-data" method="post">
	    <s:textfield name="kidPublishForm.userID" label="用户ID" />
		<s:textfield name="kidPublishForm.kidName" label="姓名" />
		<s:textfield name="kidPublishForm.gender" label="性别" />
		<s:textfield name="kidPublishForm.birthday" label="出生日期" />
		<s:textfield name="kidPublishForm.height" label="失踪时身高" />
		<s:textfield name="kidPublishForm.lostTime" label="丢失时间" />
		<s:textfield name="kidPublishForm.lostPlace" label="丢失地点" />
		<s:textfield name="kidPublishForm.homeTown" label="籍贯" />
		<s:textarea name="kidPublishForm.description" label="描述" cols="17" rows="5"/>
		<s:file name="file" label="照片1"></s:file>
		<s:file name="file" label="照片2"></s:file>
		<s:file name="file" label="照片3"></s:file>
		<s:file name="file" label="照片4"></s:file>
		<s:file name="file" label="照片5"></s:file>
		<s:file name="file" label="照片6"></s:file>
		<s:file name="file" label="照片7"></s:file>
		<s:file name="file" label="照片8"></s:file>
		<s:submit value="发布"></s:submit>
	</s:form>
</body>
</html>