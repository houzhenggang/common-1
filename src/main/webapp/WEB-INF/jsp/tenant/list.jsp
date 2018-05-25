<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.Tenant"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "tenant");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <p>租户管理</p>
            </div>
            <div class="track-content " id="tenantPage">
                <table class="recruit-table ">
                    <thead>
                    <tr>
                        <th>租户照片</th>
                        <th>租户名称</th>
                        <th>租户年龄</th>
                        <th>租户状态</th>
                        <th>创建者</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="recruit-tbody">
	                    <%
	                    List<Tenant> listTenant = (List<Tenant>)session.getAttribute("listTenant");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	PageUtil pUtil = null;
					 	if(listTenant != null){ //不为空的情况
					 	/* 设置每页数量 */
						 	pUtil = new PageUtil(8, listTenant.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	String statusP = "";
					 		String statusB = "";
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Tenant tenant = listTenant.get(i);
						 		int status = tenant.getStatus();//1：正常  0：被冻结
						 		if(status == 0){
						 			statusP = "被冻结";
						 			statusB = "解冻";
						 		}else{
						 			statusP = "正常";
						 			statusB = "冻结";
						 		}
	                    %>
                    
                        <tr>
                            <td><img style="width:30px" src="<%=tenant.getPhoto()%>"/></td>
                            <td><%=tenant.getName()%></td>
                            <td><%=tenant.getAge()%></td>
                            <td id="st<%=tenant.getId()%>"><%=statusP%></td>
                            <td><%=tenant.getCreator()%></td>
                            <td title="<%=tenant.getUpdateTime()%>"><%=tenant.getUpdateTime()%></td>
                            <td>
                            <span><a href="javascript:void(0);" onclick="changeStatus(<%=tenant.getId()%>);" id="<%=tenant.getId()%>"><%=statusB%></a></span>
                            <span><a href="${ctx}/manage/tenant/view/<%=tenant.getId()%>" target="_blank">查看</a></span>
                            <%-- <shiro:hasPermission name="tenant:edit"> --%>
                            	<span><a href="${ctx}/manage/tenant/edit/<%=tenant.getId()%>">编辑</a></span>
                            <%-- </shiro:hasPermission>
                            <shiro:hasPermission name="tenant:delete"> --%>
                            	<span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该租户吗?'))
                            	{window.location='${ctx}/manage/tenant/delete/<%=tenant.getId()%>'}">删除</a></span>
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
                <%-- <shiro:hasPermission name="tenant:add"> --%>
                    <a href="${ctx}/manage/tenant/add">新增租户</a>
				<%-- </shiro:hasPermission> --%>
                </div>
                <div class="page-num">
                
                    <%
						if(listTenant != null){
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

function changeStatus(id){
	var objStatus = $("#"+id);
	var status = objStatus.text();
	$.ajax({
		type: "POST",
		url: "${ctx}/manage/tenant/change/" +id,
		data: "_method=POST",
		dataType: 'json',
		success: function(data) {
			var msg = data.msg;
			if(msg){
				warning(data.msg,2000);
			}else{
				setTimeout(function(){
					if(status == '冻结'){
						objStatus.text("解冻");
						$("#st"+id).text("被冻结");
					}else{
						objStatus.text("冻结");
						$("#st"+id).text("正常");
					} 
				},300)
			}
		}
	});
}

/* 分页使用ajax无刷新获取 */
function gotoPage(pageNum){
	$.ajax({
		url:'${ctx}/manage/tenant/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#tenantPage").html();
			$("#tenantPage").html(changePageHtml);
		}
	})
}
   
</script>

</html>