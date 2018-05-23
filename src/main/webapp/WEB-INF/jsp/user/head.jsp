<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
    <link rel="stylesheet" href="${ctx}/background/css/main.css">
    <link rel="stylesheet" href="${ctx}/background/css/magic-check.css">
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

<c:if test="${not empty msg}">
	<div class="alert-mask">
	    <div class="alert-content-msg">
	    <p>${msg}</p>
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
			<%String menu = (String)request.getAttribute("MENU_INDEX");%>            
            <nav class="nav fr">
                <ul class="clear">
                
				<shiro:hasPermission name="user:manage">
                    <li><a href="${ctx}/security/user"
                    <%="home".equals(menu)?" class=\"on\"":""%> >管理中心</a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="account:manage">
                    <li><a href="${ctx}/security/accountM" 
                    <%="account".equals(menu)?" class=\"on\"":""%> >成员列表</a></li>
                </shiro:hasPermission>    
				
				<shiro:hasPermission name="order:manage">                    
                    <li><a href="${ctx}/security/orderM"
                    <%="order".equals(menu)?" class=\"on\"":""%> >订单管理</a></li>
				</shiro:hasPermission>
				      
				<shiro:hasPermission name="hole:manage"> 				                    
                    <li><a href="${ctx}/security/holeM"
                    <%="hole".equals(menu)?" class=\"on\"":""%> >漏洞管理</a></li>
				</shiro:hasPermission>   
				    
				<shiro:lacksPermission name="hole:manage"> 					           
                    <li><a href="${ctx}/security/holeRecord"
                    <%="hole".equals(menu)?" class=\"on\"":""%> >漏洞修复</a></li>
				</shiro:lacksPermission>	                    
                    
				<shiro:hasPermission name="article:manage"> 	                    
                    <li><a href="${ctx}/security/articleM"
                    <%="article".equals(menu)?" class=\"on\"":""%> >安全动态</a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="product:manage">			                    
                    <li><a href="${ctx}/security/product"
                    <%="product".equals(menu)?" class=\"on\"":""%> >礼品商城</a></li>
				</shiro:hasPermission>
				
				<shiro:hasPermission name="position:manage">				
                    <li><a href="${ctx}/security/positionM" 
                    <%="position".equals(menu)?" class=\"on\"":""%> >招聘信息</a></li>
				</shiro:hasPermission>
				                    
                </ul>
            </nav>
        </div>
    </header>
    <section class="content">
        <div class="first-section">
        	<div class="width1000">
                <p class="fl">管理员后台管理页面</p>
                <div class="sign-out fr">
                    <p class="fl">Hi,<span>${currentUser.username}</span></p>
            		<a href="${ctx}/base/user/logout">退出登录</a>
                </div>
            </div>
        </div>