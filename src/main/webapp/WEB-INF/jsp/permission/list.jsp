<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.base.Permission"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <p>管理中心</p>
                <shiro:hasPermission name="permission:initAdmin">
                <a href="javascript:void(0);" onclick="javascript:if (confirm('确定初始化系统管理权限吗？'))
				{window.location='${ctx}/base/permission/initAdmin'}" style="color:#EB6143"> > 初始化系统管理员权限</a>
				</shiro:hasPermission>
            </div>
            <div class="manager-property-content " id="permissionPage">
                <div class="clear">
                    <ul class="manage-nav">
                        <shiro:hasPermission name="user:manage">
                        <li class="manager"><a href="${ctx}/base/user/list">管理员</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:manage">
                        <li class="manager"><a href="${ctx}/base/role/list">角色管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="permission:manage">
                        <li class="manager on"><a href="${ctx}/base/permission/list">权限管理</a></li>
                        </shiro:hasPermission>
                        <%-- <shiro:hasPermission name="notice:manage"> --%>
                        <li class="manager"><a href="${ctx}/manage/notice/list">配置管理</a></li>
                        <%-- </shiro:hasPermission> --%>
                        
                    </ul>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>父权限ID</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>权限描述</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="manager-content-tbody">
                    
	                    <%
	                    List<Permission> listPermission = (List<Permission>)session.getAttribute("listPermission");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	/* 设置每页数量 */
					 	PageUtil pUtil = null;
					 	if(listPermission != null){ //不为空的情况
						 	pUtil = new PageUtil(20, listPermission.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Permission permission = listPermission.get(i);
	                    %>
                    
                        <tr>
                            <td><%=permission.getParentid()%></td>
                            <td><%=permission.getName()%></td>
                            <td><%=permission.getElement()%></td>
                            <td title="<%=permission.getDescription()%>"><%=permission.getDescription()%></td>
                            <td>
                            <shiro:hasPermission name="permission:view">
                            <span><a href="${ctx}/base/permission/view/<%=permission.getId()%>">查看</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="permission:edit">
                            <span><a href="${ctx}/base/permission/edit/<%=permission.getId()%>">编辑</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="permission:delete">
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该条权限吗?'))
                            {window.location='${ctx}/base/permission/delete/<%=permission.getId()%>'}">删除</a></span>
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
                	<shiro:hasPermission name="permission:add">
                    <a href="${ctx}/base/permission/add">新增权限</a>
                    </shiro:hasPermission>
                </div>
                <div class="page-num">
                    
					<%
						if(listPermission != null){
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
		url:'${ctx}/base/permission/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#permissionPage").html();
			$("#permissionPage").html(changePageHtml);
		}
	})
}
   
</script>

</html>