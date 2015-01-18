<%@ page import="com.youku.share.crowdfunding.web.security.PasswordUtil" %>

<%--
  ~ Copyright (c) 2014. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
  ~ Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
  ~ Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
  ~ Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
  ~ Vestibulum commodo. Ut rhoncus gravida arcu.
  --%>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-6-15
  Time: 下午9:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  	<%

  	%>
  	
  	
    <form method="post" action="<%=request.getContextPath()%>/index/j_spring_security_check">
        name:<input type="text" name="j_username"><br>
        pwd:<input type="password" name="j_password"><br>
        <button type="submit">Login</button><br>
    </form>
  </body>
</html>
