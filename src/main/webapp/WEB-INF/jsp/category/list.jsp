<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.Category"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
            	<a href="${ctx}/base/user/home">管理中心</a> <span> > 配置管理</span>
            </div>
            <div class="manager-property-content " id="categoryPage">
                <div class="clear">
                    <ul class="manage-nav">
                        <%-- <shiro:hasPermission name="category:manage"> --%>
                        <li class="manager"><a href="${ctx}/manage/category/list">公告通知</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <%-- <shiro:hasPermission name="category:manage"> --%>
                        <li class="manager on"><a href="${ctx}/manage/category/list">商品类别</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <%-- <shiro:hasPermission name="rewardPlan:manage"> --%>
                        <li class="manager "><a href="${ctx}/manage/style/list">风格类型</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <shiro:hasPermission name="exchangeType:manage">
                        <li class="manager "><a href="${ctx}/manage/allowedIp">允许IP</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>类别名称</th>
                        <th>创建者</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="manager-content-tbody">
                    
	                    <%
	                    List<Category> listCategory = (List<Category>)session.getAttribute("listCategory");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	/* 设置每页数量 */
					 	PageUtil pUtil = null;
					 	if(listCategory != null){ //不为空的情况
						 	pUtil = new PageUtil(8, listCategory.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Category category = listCategory.get(i);
	                    %>
                    
                        <tr>
                            <td><%=category.getName()%></td>
                            <td><%=category.getCreator()%></td>
                            <td><%=category.getUpdateTime()%></td>
                            <td>
							<%-- <shiro:hasPermission name="category:view"> --%>
                            <span><a href="${ctx}/manage/category/view/<%=category.getId()%>">查看</a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="category:edit"> --%>
                            <span><a href="${ctx}/manage/category/edit/<%=category.getId()%>">编辑</a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="category:delete"> --%>
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该商品类别吗?'))
                            {window.location='${ctx}/manage/category/delete/<%=category.getId()%>'}">删除</a></span>
                            <%-- </shiro:hasPermission> --%>
                            </td>
                        </tr>
	                    <%
							}
					 	}
						%>   
						  
                    </tbody>
                </table>
                <div class="add-btn">
                	<%-- <shiro:hasPermission name="category:add"> --%>
                    <a href="${ctx}/manage/category/add">新增类别</a>
                    <%-- </shiro:hasPermission> --%>
                </div>
                <div class="page-num">
                    
					<%
						if(listCategory != null){
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
		url:'${ctx}/manage/category/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#categoryPage").html();
			$("#categoryPage").html(changePageHtml);
		}
	})
}
   
</script>

</html>