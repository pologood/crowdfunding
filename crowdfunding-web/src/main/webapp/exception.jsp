<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Crowdfunding Exception Information</title>
</head>
<body>
异常代码:${CrowdfundingException.code}<br/>
异常信息:${CrowdfundingException.message}<br/>
<%
	Object throwableObject = request.getAttribute("OtherException");
	if(throwableObject != null){
		Throwable t = (Throwable)throwableObject;
		//t.printStackTrace();
		%>系统发生未知异常:<br/><%=t.getMessage()%><%
	}
%>
${OtherException}
</body>
</html>