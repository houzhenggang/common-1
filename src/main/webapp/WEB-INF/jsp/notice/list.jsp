<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.Notice"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
            	<a href="${ctx}/base/user/home">管理中心</a> <span> > 配置管理</span>
            </div>
            <div class="manager-property-content " id="noticePage">
                <div class="clear">
                    <ul class="manage-nav">
                        <%-- <shiro:hasPermission name="notice:manage"> --%>
                        <li class="manager on"><a href="${ctx}/manage/notice/list">公告通知</a></li>
                        <%-- </shiro:hasPermission> --%>
                        <shiro:hasPermission name="notice:manage">
                        <li class="manager"><a href="${ctx}/manage/notice">宣传图片</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="rewardPlan:manage">
                        <li class="manager "><a href="${ctx}/manage/rewardPlan">奖励计划</a></li>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="exchangeType:manage">
                        <li class="manager "><a href="${ctx}/manage/allowedIp">允许IP</a></li>
                        </shiro:hasPermission>
                    </ul>
                </div>
                <table>
                    <thead>
                    <tr>
                        <th>公告标题</th>
                        <th>公告状态</th>
                        <th>创建者</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="manager-content-tbody">
                    
	                    <%
	                    List<Notice> listNotice = (List<Notice>)session.getAttribute("listNotice");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	/* 设置每页数量 */
					 	PageUtil pUtil = null;
					 	if(listNotice != null){ //不为空的情况
						 	pUtil = new PageUtil(8, listNotice.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	String statusP = "";
					 		String statusB = "";
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Notice notice = listNotice.get(i);
						 		int status = notice.getStatus();//1：开启  0：禁止
						 		if(status == 0){
						 			statusP = "未公告";
						 			statusB = "开启";
						 		}else{
						 			statusP = "公告中";
						 			statusB = "禁止";
						 		}
						 		
	                    %>
                    
                        <tr>
                            <td title="<%=notice.getTitle()%>">
                            <a href="${ctx}/manage/notice/view/<%=notice.getId()%>">
                            <%=notice.getTitle()%></a></td>
                            <td id="st<%=notice.getId()%>"><%=statusP%></td>
                            <td><%=notice.getCreator()%></td>
                            <td><%=notice.getUpdateTime()%></td>
                            <td>
							<%-- <shiro:hasPermission name="notice:change"> --%>
                            <span><a href="javascript:void(0);" onclick="changeStatus(<%=notice.getId()%>);" id="<%=notice.getId()%>"><%=statusB%></a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="notice:edit"> --%>
                            <span><a href="${ctx}/manage/notice/edit/<%=notice.getId()%>">编辑</a></span>
                            <%-- </shiro:hasPermission> --%>
							<%-- <shiro:hasPermission name="notice:delete"> --%>
                            <span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该公告吗?'))
                            {window.location='${ctx}/manage/notice/delete/<%=notice.getId()%>'}">删除</a></span>
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
                	<%-- <shiro:hasPermission name="notice:add"> --%>
                    <a href="${ctx}/manage/notice/add">新增公告</a>
                    <%-- </shiro:hasPermission> --%>
                </div>
                <div class="page-num">
                    
					<%
						if(listNotice != null){
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
		url: "${ctx}/manage/notice/change/" +id,
		data: "_method=POST",
		dataType: 'json',
		success: function(data) {
			var msg = data.msg;
			if(msg){
				warning(data.msg,2000);
			}else{
				setTimeout(function(){
					if(status == '禁止'){
						objStatus.text("开启");
						$("#st"+id).text("未公告");
					}else{
						objStatus.text("禁止");
						$("#st"+id).text("公告中");
					} 
				},300)
			}
		}
	});
}

/* 分页使用ajax无刷新获取 */
function gotoPage(pageNum){
	$.ajax({
		url:'${ctx}/manage/notice/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#noticePage").html();
			$("#noticePage").html(changePageHtml);
		}
	})
}
   
</script>

</html>