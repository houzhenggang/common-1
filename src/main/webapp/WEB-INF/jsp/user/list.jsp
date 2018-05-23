<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="com.common.dto.base.UserDTO"%>
<%@ page import="com.common.pojo.base.Role"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <p>管理中心</p>
            </div>
            <div class="manager-content" id="userPage">
                <div class="clear">
                    <ul class="manage-nav">
                        <shiro:hasPermission name="user:manage">
                        <li class="manager on"><a href="${ctx}/base/user/list">管理员</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="role:manage">
                        <li class="manager"><a href="${ctx}/base/role/list">角色管理</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="permission:manage">
                        <li class="manager"><a href="${ctx}/base/permission/list">权限管理</a></li>
                        </shiro:hasPermission>
                        <%-- <shiro:hasPermission name="exchangeType:manage"> --%>
                        <li class="manager"><a href="${ctx}/manage/notice/list">配置管理</a></li>
                        <%-- </shiro:hasPermission> --%>
                    </ul>
                </div>

                <table>
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>邮件</th>
                        <th>所属角色</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="manager-content-tbody">
                    <% 
	                    List<UserDTO> listUser = (List<UserDTO>)session.getAttribute("listUser");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	PageUtil pUtil = null;
					 	if(listUser != null){ //不为空的情况
						 	/* 设置每页数量 */
						 	pUtil = new PageUtil(8, listUser.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		String roles = "";
						 		UserDTO user = listUser.get(i);
						 		List<Role> list = user.getRoles();
						 		for(Role role : list){
						 			roles += role.getName()+" "; 
						 		}
					 		
                    %>
                        <tr>
                            <td><%=user.getUsername()%></td>
                            <td><%=user.getEmail()%></td>
                            <td><%=roles%></td>
                            <td>
                            <shiro:hasPermission name="user:view">
                            <span><a href="${ctx}/base/user/view/<%=user.getId()%>">查看</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="user:edit">
                            <span><a href="${ctx}/base/user/edit/<%=user.getId()%>">编辑</a></span>
                            </shiro:hasPermission>
							<shiro:hasPermission name="user:delete">
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该用户吗?'))
                            {window.location='${ctx}/base/user/delete/<%=user.getId()%>'}">删除</a></span>
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
                	<shiro:hasPermission name="user:add">
                    <a href="${ctx}/base/user/add">新增用户</a>
                    </shiro:hasPermission>
                </div>
                
                <div class="page-num">
                
                    <%
						if(listUser != null){
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
		url:'${ctx}/base/user/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#userPage").html();
			$("#userPage").html(changePageHtml);
		}
	})
}
   
</script>
</html>