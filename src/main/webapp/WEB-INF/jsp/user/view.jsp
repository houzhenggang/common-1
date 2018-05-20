<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<%
	request.setAttribute("MENU_INDEX", "home");
%>
<jsp:include page="/base/user/head" flush="true" />

<div class="management-center second-section">
	<div class="second-title">
		<a href="${ctx}/base/user/list">管理员</a> <span> > 查看用户</span>
	</div>

	<form>
		<div class="new-manager">
			<div>
				<div class="bug-input ml30 input310">
					<p>用 户 名 :</p>
					&nbsp;<input type="text" name="username"
						value="${user.username}" readonly="readonly" />
				</div>
				<div class="bug-input ml30 input310">
					<p>电子邮件:</p>
					<input type="text" name="email" value="${user.email}"
						readonly="readonly" />
				</div>
				<div class="bug-input ml30 input310">
					<p>所属角色:</p>
					<c:forEach items="${listRole}" var="role">
						<span style="line-height: 40px; padding-left: 15px;">${role.name}</span>
					</c:forEach>
				</div>
			</div>
			<div class="add-button">
				<input class="add-button" type="button"
					onclick="document.location.href='${ctx}/base/user/list'" value="返 回" />
			</div>
		</div>
	</form>

</div>
</section>
</body>
</html>