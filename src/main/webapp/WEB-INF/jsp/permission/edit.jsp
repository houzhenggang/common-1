<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/permission/list">权限管理</a> <span> > 修改权限</span>
            </div>
            
            <form action="${ctx}/base/permission/edit/${id}" method="post">
            
	            <div class="new-manager">
	                <div>
	                    <div class="bug-input ml30 select310">
	                        <p style="letter-spacing: 4.7px">父权限:</p>
	                        <select name="parentid" id="">
		                        <option value="0">根权限</option>
	                            <c:forEach items="${list}" var="perm" varStatus="vs">  
									<option value="${perm.id}"
									<c:if test="${permission.parentid == perm.id}">selected = "selected"</c:if> >${perm.name}</option> 			
								</c:forEach>
	                        </select>
	                    </div>
	                    <div class="bug-input ml30 input310">
	                    <p>权限名称:</p><input type="text" id="name" name="name" value="${permission.name}" /></div>
	                    <div class="bug-input ml30 input310">
	                    <p>权限表达:</p><input type="text" id="element" name="element" value="${permission.element}" /></div>
	                    <div class="bug-input ml30 input310">
	                    <p>权限描述:</p>
	                        <textarea id="description" name="description" class="new-power">${permission.description}</textarea>
	                    </div>
	                </div>
	                <div class="add-button">
	                    <input class="add-button" type="submit" value="保 存" />
	                	<input class="add-button" type="button" onclick="document.location.href='${ctx}/base/permission/list'" value="取 消" />
	                </div>
	            </div>
            
		</form>
		
        </div>
    </section>
</body>
</html>