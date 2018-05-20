<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section" >
            <div class="second-title">
                <a href="${ctx}/security/role">角色管理</a> <span> > 修改角色</span>
            </div>
            
            <form action="${ctx}/base/role/edit/${id}" method="post">
            
	            <div class="new-manager" >
	                <div>
	                    <div class="bug-input ml30 input310">
	                    <p>角色名称:</p><input type="text" name="name" value="${roleCommand.name}"><span>*必填</span></div>
	                    <div class="bug-input ml30 input310">
	                    <p>角色描述:</p><input type="text" name="description" value="${roleCommand.description}"><span>*必填</span></div>
	                </div>
	                <div class="power-choose" id="contentId">
	                    <p>选择权限</p>
	                    <input type="hidden" name="permissions" id="permissions"/>
	                </div>
	                <div class="add-button login-btn">
	                	<input class="add-button" type="submit" value="保 存" onclick='fChecked()'/>
	                	<input class="add-button" type="button" onclick="document.location.href='${ctx}/base/role/list'" value="返 回" />
	                </div>
	            </div>
	            
			</form>  

        </div>
    </section>
    
<link href="${ctx}/oTree/css/otree/otree.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/oTree/js/otree/otree.js"></script>
<script type="text/javascript" src="${ctx}/oTree/js/otree/common4otree.js"></script> 
<script type="text/javascript">
chinaAreas = ([
	{'pid':'-1','text':'权限树','id':'0','checked':0},
	${permissionTree}
]);

	var l0 = new Date().getTime();
	var otree = new OTree({
		panel 	: document.getElementById("contentId"),
		data	: chinaAreas,
		icoPath : '<%=request.getContextPath()%>/oTree/img/'
	});
	otree.paint();
	function fChecked(){
		document.getElementById("permissions").value=otree.getCheckeds('input');
		document.forms[0].submit();
	}
</script>    
</body>

</html>