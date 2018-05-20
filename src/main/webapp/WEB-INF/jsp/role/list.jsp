<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.base.Role"%>
<%@ page import="com.common.util.PageUtil"%>

<%request.setAttribute("MENU_INDEX", "home");%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <p>管理中心</p>
            </div>
            <div class="management-content " id="rolePage">
                <div class="clear">
                    <ul class="manage-nav">
                    	<shiro:hasPermission name="user:manage">
                        <li class="manager"><a href="${ctx}/base/user/list">管理员</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:manage">
                        <li class="manager on"><a href="${ctx}/base/role/list">角色管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="permission:manage">
                        <li class="manager"><a href="${ctx}/base/permission/list">权限管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="exchangeType:manage">
                        <li class="manager"><a href="${ctx}/base/">配置管理</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="management-content-tbody">
                    	<%
                    	List<Role> listRole = (List<Role>)session.getAttribute("listRole");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	PageUtil pUtil = null;
					 	if(listRole != null){ //不为空的情况
						 	/* 设置每页数量 */
						 	pUtil = new PageUtil(8, listRole.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Role role = listRole.get(i);
	                    	%>
                        <tr>
                            <td><%=role.getName()%></td>
                            <td><%=role.getDescription()%></td>
                            <td>
                            <shiro:hasPermission name="role:view">
                            <span><a href="${ctx}/base/role/view/<%=role.getId()%>">查看</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="role:edit">
                            <span><a href="${ctx}/base/role/edit/<%=role.getId()%>">编辑</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="role:delete">
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该角色吗?'))
                            {window.location='${ctx}/base/role/delete/<%=role.getId()%>'}">删除</a></span>
                            </shiro:hasPermission>
                            </td>
                        </tr>
						<%
							}
					 	}
						%>
                    </tbody>
                </table>
                <div class="add-btn">
                	<shiro:hasPermission name="role:add">
                    <a href="${ctx}/base/role/add">新增角色</a>
                    </shiro:hasPermission>
                </div>
                <div class="page-num">
                
                    <%
						if(listRole != null){
				 	%>
						<span><%=currentPage%>/<%=pUtil.getPageCount()%></span>
						<div><a href="javascript:void(0)" onclick="gotoPage(<%=(currentPage - 1)%>)">上一页</a>
	                    <a href="javascript:void(0)" onclick="gotoPage(<%=(currentPage + 1)%>)">下一页</a>
	                    </div>
					<%
						}else{
				 	%>
				 		<span>0/0</span>
				 		<div><a href="javascript:void(0)" >上一页</a>
                    	<a href="javascript:void(0)">下一页</a>
                   	 	</div>
				 	<%
						}
				 	%>
                    
                </div>
            </div>

        </div>
    </section>
</body>
<script type="text/javascript">
   
/* 分页使用ajax无刷新获取 */
function gotoPage(pageNum){
	$.ajax({
		url:'${ctx}/base/role/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#rolePage").html();
			$("#rolePage").html(changePageHtml);
		}
	})
}
   
</script>
</html>