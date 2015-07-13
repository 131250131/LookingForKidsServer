<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>upload</title>
</head>
<body>
	<s:form action="upload" enctype="multipart/form-data" method="post">
		<s:file name="file" label="照片"></s:file>
		<s:submit value="发布"></s:submit>
	</s:form>
</body>
</html>