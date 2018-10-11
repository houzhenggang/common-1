<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "tenant");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/manage/tenant/list">租户管理</a> <span> > 查看租户</span>
            </div>
            <form>
	            <div class="new-recruit">
	                <div class="bug-input input310">
	                <p>租户姓名:</p><input type="text" name="name" value="${tenant.name}" readonly="readonly"/>
	                </div>
	                <div class="bug-input input310">
	                <p>租户年龄:</p><input type="text" name="age" value="${tenant.age}" readonly="readonly"/>
	                </div>
	                <div class="upload-img-div">
	                    <div class="user-img-pic">
	                        <p>租户照片:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格350×490px</p>
	                            <div>
	                            <img id="img"
								<c:choose>          
									<c:when test="${not empty tenant.photo}"> 
									src="${ctx}/${tenant.photo}"
									</c:when> 
									<c:otherwise>src=""</c:otherwise>
								</c:choose> />
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
		                <input type="button" onclick="document.location.href='${ctx}/manage/tenant/list'" value="返 回" />
	                </div>
	            </div>
		</form>
        </div>
    </section>
</body>
</html>