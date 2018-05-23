<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/base/user/home">管理中心</a> <a href="${ctx}/manage/notice/list"> > 公告通知</a> <span> > 新增公告</span>
            </div>
            <form action="${ctx}/manage/notice/add" method="post" enctype="multipart/form-data">
	            <div class="new-recruit">
	                <div class="bug-input input900">
	                <p>公告标题:</p><input type="text" name="title" value="${noticeCommand.title}" />
	                </div>
	                <div class="new-content">
	                    <p>公告内容:</p>
	                    <textarea name="requirement">${noticeCommand.content}</textarea>
	                </div>
	                <div>
	                    <div class="shop-goods-pic">
	                        <p>公告图片:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格1600×435px</p>
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
		                <input type="button" onclick="document.location.href='${ctx}/manage/notice/list'" value="取 消" />
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

    //选择文件之后执行上传  
/*     $('#fileToUpload').live('change', function() {
    	
    	var text = $(this).val();
	    var file_type = text.substring(text.lastIndexOf('.')+1,text.length).toLowerCase();
		if(!(file_type=='gif'||file_type=='jpg'||file_type=='jpeg'||file_type=='png'||file_type=='bmp')){
			warning("图片格式不正确！",1000);
			return false;
		}
		
        $.ajaxFileUpload({
        	type: 'POST',
            url:'${ctx}/common/upload',  
            secureuri:false,  
            fileElementId:'fileToUpload',//file标签的id 
            data: {"path": "notice"},
            dataType: 'json',//返回数据的类型  
            success: function (data, status) {  
            	
            	if(data.error){
            		warning(data.error, 1500);
            	}else{
	                $('#fileName').attr("value",data.fileName);
	                $('#imgName').attr("src",data.preFileName);
            	}
            	
            },  
            error: function (data, status, e) { 
            	warning(e, 1000);
            }  
        }); 
        
        $("#fileToUpload").replaceWith($("#fileToUpload").clone(true));
        
    }); */	
	
})

</script>
</html>