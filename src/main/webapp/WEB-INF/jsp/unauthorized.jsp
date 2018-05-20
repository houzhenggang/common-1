<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%-- <%request.setAttribute("MENU_INDEX", "home");%> --%>
<jsp:include page="/base/user/head" flush="true"/>

	<div class="second-section">
	
		<div class="second-title">
			<h1><img src="${ctx}/styles/img/icons/smiley-sad.png" /> 用户未授权！</h1>
		</div>
	
		<div class="manager-content">
	    	<div style="font-size:16px;">
			   <strong>未授权 :</strong> 你所属的用户未授予相应的功能权限，请换其他用户试试，更多信息请咨询管理员. 
			</div>
		</div>
	<a href="javascript:;" onclick="history.go(-1)" class="fr go-back">返回</a>
	</div> 
	</section>
</body>
</html>