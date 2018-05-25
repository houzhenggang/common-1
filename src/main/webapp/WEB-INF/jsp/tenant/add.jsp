<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "tenant");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/manage/tenant/list">租户管理</a> <span> > 新增租户</span>
            </div>
            <form action="${ctx}/manage/tenant/add" method="post" enctype="multipart/form-data">
	            <div class="new-recruit">
	                <div class="bug-input input900">
	                <p>租户姓名:</p><input type="text" name="name" value="${tenantCommand.name}" />
	                </div>
	                <div class="bug-input input900">
	                <p>租户年龄:</p><input type="text" name="age" value="${tenantCommand.age}" />
	                </div>
	                <div class="upload-img-div">
	                    <div class="user-img-pic">
	                        <p>租户照片:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格350×490px</p>
	                            <div>
	                            <img id="img" src="" />
	                            </div>
                            	<a href="javascript:void(0)" class="file" id="upload">上传图片</a>
			                    <input id="fileToUpload" style="display: none" type="file"
			                    onchange="PreviewImage(this,'img','divImgPreview')" name="img">  
	                        </div>
	                    </div>
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
	                    <input type="submit" value="保 存" />
		                <input type="button" onclick="document.location.href='${ctx}/manage/tenant/list'" value="取 消" />
	                </div>
	            </div>
		</form>
        </div>
    </section>
</body>
<script src="${ctx}/background/js/uploadFile.js"></script>
<script type="text/javascript">
$(function () {
	//点击打开文件选择器  
    $('#upload').on('click', function() {  
        $('#fileToUpload').click();  
    });
})
</script>
</html>