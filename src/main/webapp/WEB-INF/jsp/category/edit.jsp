<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/user/home">管理中心</a> <a href="${ctx}/manage/category/list"> > 商品类别</a> <span> > 修改类别</span>
            </div>
            
            <form action="${ctx}/manage/category/edit/${id}" method="post">
	            <div class="new-recruit">
	                <div class="bug-input input310">
	                <p>类别名称:</p><input type="text" name="name" value="${category.name}" />
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
	                    <input type="submit" value="保 存" />
		                <input type="button" onclick="document.location.href='${ctx}/manage/category/list'" value="取 消" />
	                </div>
	            </div>
	
        	</form>
        </div>
    </section>
</body>
</html>