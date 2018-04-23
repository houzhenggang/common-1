<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
</head>
<body>
<!-- CONTENT  BEGIN--> 
<div id="content" class="white">
<h1><img src="<c:url value="/styles/img/icons/smiley-sad.png"/>" /> 警告！！！</h1>
<div class="bloc">
	<div class="content">
    	<div class="warning" style="font-size:16px;">
		   <strong>异常 :</strong> <%=request.getAttribute("javax.servlet.error.exception_type")%>  <hr/>
		   <strong>信息 :</strong> <%=request.getAttribute("javax.servlet.error.message")%>
		</div>
	</div>
</div> 
<!-- CONTENT  END--> 
</div>
</body>
</html>