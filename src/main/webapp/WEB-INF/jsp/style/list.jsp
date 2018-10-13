<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.Style"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
            	<a href="${ctx}/base/user/home">管理中心</a> <span> > 配置管理</span>
            </div>
            <div class="manager-property-content " id="stylePage">
                <div class="clear">
                    <ul class="manage-nav">
                        <%-- <shiro:hasPermission name="style:manage"> --%>
                        <li class="manager"><a href="${ctx}/manage/style/list">公告通知</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <%-- <shiro:hasPermission name="style:manage"> --%>
                        <li class="manager"><a href="${ctx}/manage/style/list">商品类别</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <%-- <shiro:hasPermission name="rewardPlan:manage"> --%>
                        <li class="manager on"><a href="${ctx}/manage/style/list">风格类型</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <shiro:hasPermission name="exchangeType:manage">
                        <li class="manager "><a href="${ctx}/manage/allowedIp">允许IP</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>风格类型</th>
                        <th>创建者</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="manager-content-tbody">
                    
	                    <%
	                    List<Style> listStyle = (List<Style>)session.getAttribute("listStyle");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	/* 设置每页数量 */
					 	PageUtil pUtil = null;
					 	if(listStyle != null){ //不为空的情况
						 	pUtil = new PageUtil(8, listStyle.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Style style = listStyle.get(i);
	                    %>
                    
                        <tr>
                            <td><%=style.getName()%></td>
                            <td><%=style.getCreator()%></td>
                            <td><%=style.getUpdateTime()%></td>
                            <td>
							<%-- <shiro:hasPermission name="style:view"> --%>
                            <span><a href="${ctx}/manage/style/view/<%=style.getId()%>">查看</a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="style:edit"> --%>
                            <span><a href="${ctx}/manage/style/edit/<%=style.getId()%>">编辑</a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="style:delete"> --%>
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该风格类型吗?'))
                            {window.location='${ctx}/manage/style/delete/<%=style.getId()%>'}">删除</a></span>
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
                	<%-- <shiro:hasPermission name="style:add"> --%>
                    <a href="${ctx}/manage/style/add">新增类别</a>
                    <%-- </shiro:hasPermission> --%>
                </div>
                <div class="page-num">
                    
					<%
						if(listStyle != null){
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
		url:'${ctx}/manage/style/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#stylePage").html();
			$("#stylePage").html(changePageHtml);
		}
	})
}
   
</script>

</html>