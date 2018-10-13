<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/user/home">管理中心</a> <a href="${ctx}/manage/style/list"> > 风格类型</a> <span> > 查看风格</span>
            </div>
            <form>
	            <div class="new-recruit">
	                <div class="bug-input input310">
	                <p>风格类型:</p><input type="text" name="name" value="${style.name}" readonly="readonly"/>
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
		                <input type="button" onclick="document.location.href='${ctx}/manage/style/list'" value="返 回" />
	                </div>
	            </div>
	
        	</form>
        </div>
    </section>
</body>
</html>