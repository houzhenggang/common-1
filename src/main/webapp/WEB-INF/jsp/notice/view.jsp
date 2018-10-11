<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/user/home">管理中心</a> <a href="${ctx}/manage/notice/list"> > 公告通知</a> <span> > 查看公告</span>
            </div>
            
            <form>
	            <div class="new-recruit">
	                <div class="bug-input input900">
	                <p>公告标题:</p><input type="text" name="title" value="${notice.title}" readonly="readonly"/>
	                </div>
	                <div class="new-content">
	                    <p>公告内容:</p>
	                    <textarea name="content" readonly="readonly">${notice.content}</textarea>
	                </div>
	                <div class="upload-img-div">
	                    <div class="shop-goods-pic">
	                        <p>公告图片:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格1600×435px</p>
	                            <div>
	                            <img id="img"
								<c:choose>          
									<c:when test="${not empty notice.imgPath}"> 
									src="${ctx}/${notice.imgPath}"
									</c:when> 
									<c:otherwise>src=""</c:otherwise>
								</c:choose> />
	                            </div>
	                            <!-- <a href="javascript:void(0)" class="file" id="upload">上传图片</a>
			                    <input id="fileToUpload" style="display: none" type="file"
			                    onchange="PreviewImage(this,'img','divImgPreview')" name="img"> -->
	                        </div>
	
	                    </div>
	
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
		                <input type="button" onclick="document.location.href='${ctx}/manage/notice/list'" value="取 消" />
	                </div>
	            </div>
	
        	</form>
        </div>
    </section>
</body>
</html>