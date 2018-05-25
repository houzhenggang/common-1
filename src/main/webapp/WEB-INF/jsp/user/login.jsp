<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>MY 后台管理系统中心</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="description" content="乐信集团安全应急响应中心">
    <meta name="keywords" content="乐信集团,安全团队,应急响应,乐信集团安全应急响应中心,LXSRC">
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link rel="icon shortcut" href="${ctx}/background/img/top-bird.ico">
    <link rel="stylesheet" type="text/css" href="${ctx}/background/css/main.css">
    <script type="text/javascript" src="${ctx}/background/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/background/js/main.js"></script>
</head>

<body>

<c:if test="${not empty error}">
	<div class="alert-mask">
	    <div class="alert-content">
	    <p>${error}</p>
	    </div>
	</div>
</c:if>

    <header class="header">
        <div>
            <div class="logo fl">
                <a href="${ctx}/security/index" class="clear">
                    <div class="logo-pic fl">
                        <img src="${ctx}/background/img/logo.png" alt="">
                    </div>
                    <div class="logo-text fl">
                        <p>MY 后台管理系统中心</p>
                        <p>MY Manage system Center </p>
                    </div>
                </a>
            </div>
        </div>
    </header>
    <section class="content">
        <div class="first-section">
            <div class="width1000">
                <p class="fl">管理员后台管理页面</p>
                <div class="sign-out fr">
            		<a href="#">其它登录</a>
                </div>
            </div>
        </div>
        <div class="management-center second-section">
			<form action="${ctx}/base/user/login" method="post">
	            <div class="new-manager">
	
	                <div>
	                    <p class="login-text">MY 管理员登录</p>
	                    <div class="bug-input ml30 input310">
	                    <p>用 户 名 :</p>&nbsp;<input type="text" name="username" value="${loginCommand.username}">
	                    </div>
	                    <div class="bug-input ml30 input310">
	                    <p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码 :</p>
	                    <input type="password" name="password" value="${loginCommand.password}">
	                    </div>
	                    <div class="bug-input ml30">
                            <p>验 证 码 :</p>
                            <input type="text" name="captcha" style="width: 150px" class="fl">
                            <span class="fl">
                            <a href="javascript:reloadVerifyCode();" title="看不清楚,换一张" >
							<img alt="验证码" id="safecode" style="margin-top:4px;border:1px solid #e9e9e9;"
							src="${ctx}/servlet/ImageLoginServlet" border="0">
							</a>
							</span>
                            <a href="javascript:reloadVerifyCode();" title="看不清楚,换一张">换一张</a>
                    	</div>
	                </div>
	                <div class="add-button login-btn">
	                	<input class="add-button" type="submit" value="登 录"/>
	                	<input class="add-button" type="button" onclick="document.location.href='${ctx}/security/index'" value="取消" />
	                </div>
	                
	            </div>
            </form>  

        </div>
    </section>
</body>
<script type="text/javascript">

//验证码！
function reloadVerifyCode(){  
    var timenow = new Date().getTime();                         
    document.getElementById("safecode").src="${ctx}/servlet/ImageLoginServlet?d=" + timenow;  
} 

</script>
</html>