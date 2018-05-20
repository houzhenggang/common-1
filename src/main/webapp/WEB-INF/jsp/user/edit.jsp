<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/user/list">管理员</a> <span> > 修改用户</span>
            </div>
            
            <form action="${ctx}/base/user/edit/${id}" method="post">
	            <div class="new-manager">
	                <div>
	                    <div class="bug-input ml30 input310">
	                    <p>用 户 名 :</p>&nbsp;<input type="text" name="username" id="username" value="${userCommand.username}" onkeyup="generateEmail();"
	                    <c:if test="${userCommand.userId == 1}">readonly="readonly"</c:if>><span>*必填</span></div>
	                    <div class="bug-input ml30 input310">
	                    <p>电子邮件:</p><input type="text" name="email" id="email" value="${userCommand.email}" ><span>*必填</span>
	                    </div>
	                    <div class="bug-input ml30 input310">
	                    <p>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</p>
	                    <input type="password" name="password" placeholder="不填将会默认原密码" /><span>*必填</span>
	                    </div>
	                </div>
	                
	                <div class="choose-role">
	                    <p>选择用户所属角色</p>
	                    <input type="hidden" id="roleValueId" name="roleIds"/>
	                    <div>
	                        <div class="optional-role">
	                            <span>可选角色列表</span>
	                           	<select id="selectL" name="selectL" size="6" class="optional-role-list" multiple="multiple">
							    <c:forEach items="${roleCommandList}" var="roleCommand">
								<option value="${roleCommand.roleId}" title="${roleCommand.description}">${roleCommand.name}</option>
								</c:forEach>
								</select>
	                        </div>
	                        <div class="arrow">
	                            <div id="toright" title="添加" class=" arr-right"></div>
	                            <div id="toleft" title="移除" class=" arr-left"></div>
	                        </div>
	                        <div class="selected-role">
	                            <span>已选角色列表</span>
	                           	<select id="selectR" name="selectR" size="6" class="selected-role-list" multiple="multiple">
								<c:forEach items="${originalCommandList}" var="roleCommand">
								 <option value="${roleCommand.roleId}" title="${roleCommand.description}">${roleCommand.name}</option>
								 </c:forEach>
								</select>
	                        </div>
	                    </div>
	                </div>
	                
	                <div class="add-button">
	                	<input class="add-button" type="button" value="保 存" id="sub"/>
	                	<input class="add-button" type="button" onclick="document.location.href='${ctx}/base/user/list'" value="取 消" />
	                </div>
	            </div>
			</form>

        </div>
    </section>
</body>

<script type="text/javascript">
   
$(function() {
	var leftSel = $("#selectL");
	var rightSel = $("#selectR");
	$("#toright").bind("click", function() {
		leftSel.find("option:selected").each(function() {
			$(this).remove().appendTo(rightSel);
		});
	});
	$("#toleft").bind("click", function() {
		rightSel.find("option:selected").each(function() {
			$(this).remove().appendTo(leftSel);
		});
	});
	leftSel.dblclick(function() {
		$(this).find("option:selected").each(function() {
			$(this).remove().appendTo(rightSel);
		});
	});
	rightSel.dblclick(function() {
		$(this).find("option:selected").each(function() {
			$(this).remove().appendTo(leftSel);
		});
	});
	$("#sub").click(function() {
		var selVal = [];
		rightSel.find("option").each(function() {
			selVal.push(this.value);
		});
		selVals = selVal.join(",");
		if (selVals == "") {
			warning("请为用户选择相应的角色哦！",1000);
		} else {
			$("#roleValueId").val(selVals);
			document.forms[0].submit();
		}
	});
});
</script>

</html>