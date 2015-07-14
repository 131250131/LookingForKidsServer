<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>publish</title>
</head>
<body>
	<s:form action="publish">
		<s:textfield name="kidPublishForm.kidName" label="姓名" />
		<s:textfield name="kidPublishForm.gender" label="性别" />
		<s:textfield name="kidPublishForm.birthday" label="出生日期" />
		<s:textfield name="kidPublishForm.height" label="失踪时身高" />
		<s:textfield name="kidPublishForm.lostTime" label="丢失时间" />
		<s:textfield name="kidPublishForm.lostPlace" label="丢失地点" />
		<s:textfield name="kidPublishForm.homeTown" label="籍贯" />
		<s:textarea name="kidPublishForm.description" label="描述" cols="17" rows="5"/>
		<s:submit value="发布"></s:submit>
	</s:form>
</body>
</html>