<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page import="java.util.List"%>
<%@ page import="com.common.pojo.Goods"%>
<%@ page import="com.common.util.PageUtil"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "goods");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <p>商品管理</p>
            </div>
            <div class="track-content " id="goodsPage">
                <table class="recruit-table ">
                    <thead>
                    <tr>
                        <th>商品编号</th>
                        <th>商品名称</th>
                        <th>商品价格</th>
                        <th>商品状态</th>
                        <th>创建者</th>
                        <th>修改时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody class="recruit-tbody">
	                    <%
	                    List<Goods> listGoods = (List<Goods>)session.getAttribute("listGoods");
	                    String pageStr = request.getParameter("page");
					 	int currentPage = 1;
					 	if (pageStr != null){
					 		currentPage = Integer.parseInt(pageStr);
					 	}
					 	PageUtil pUtil = null;
					 	if(listGoods != null){ //不为空的情况
					 	/* 设置每页数量 */
						 	pUtil = new PageUtil(8, listGoods.size(), currentPage);
						 	currentPage = pUtil.getCurrentPage();
						 	String statusP = "";
					 		String statusB = "";
						 	for (int i = pUtil.getFromIndex(); i < pUtil.getToIndex(); i++){
						 		Goods goods = listGoods.get(i);
						 		int status = goods.getStatus();//1：上架 0：下架
						 		if(status == 0){
						 			statusP = "下架了";
						 			statusB = "上架";
						 		}else{
						 			statusP = "上架中";
						 			statusB = "下架";
						 		}
	                    %>
                    
                        <tr>
                            <td><%=goods.getCode()%></td>
                            <td><%=goods.getName()%></td>
                            <td><%=goods.getPrice()%></td>
                            <td id="st<%=goods.getId()%>"><%=statusP%></td>
                            <td><%=goods.getCreator()%></td>
                            <td title="<%=goods.getUpdateTime()%>"><%=goods.getUpdateTime()%></td>
                            <td>
                            <span><a href="javascript:void(0);" onclick="changeStatus(<%=goods.getId()%>);" id="<%=goods.getId()%>"><%=statusB%></a></span>
                            <span><a href="${ctx}/manage/goods/view/<%=goods.getId()%>" target="_blank">查看</a></span>
                            <%-- <shiro:hasPermission name="goods:edit"> --%>
                            	<span><a href="${ctx}/manage/goods/edit/<%=goods.getId()%>">编辑</a></span>
                            <%-- </shiro:hasPermission>
                            <shiro:hasPermission name="goods:delete"> --%>
                            	<span><a href="javascript:void(0)" onclick="javascript:if (confirm('确定删除该商品吗?'))
                            	{window.location='${ctx}/manage/goods/delete/<%=goods.getId()%>'}">删除</a></span>
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
                <%-- <shiro:hasPermission name="goods:add"> --%>
                    <a href="${ctx}/manage/goods/add">新增商品</a>
				<%-- </shiro:hasPermission> --%>
                </div>
                <div class="page-num">
                
                    <%
						if(listGoods != null){
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
		url: "${ctx}/manage/goods/change/" +id,
		data: "_method=POST",
		dataType: 'json',
		success: function(data) {
			var msg = data.msg;
			if(msg){
				warning(data.msg,2000);
			}else{
				setTimeout(function(){
					if(status == '下架'){
						objStatus.text("上架");
						$("#st"+id).text("下架了");
					}else{
						objStatus.text("下架");
						$("#st"+id).text("上架中");
					} 
				},300)
			}
		}
	});
}

/* 分页使用ajax无刷新获取 */
function gotoPage(pageNum){
	$.ajax({
		url:'${ctx}/manage/goods/list?page=' + pageNum,
		type:'POST',
		success:function(data){
			var changePageHtml = $(data).find("#goodsPage").html();
			$("#goodsPage").html(changePageHtml);
		}
	})
}
   
</script>

</html>