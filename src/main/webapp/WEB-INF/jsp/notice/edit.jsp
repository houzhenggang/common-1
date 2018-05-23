<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "home");%>
<jsp:include page="/security/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/security/user/home">管理中心</a> <a href="${ctx}/security/banner"> > 宣传图片</a> <span> > 修改图片</span>
            </div>
            
            <form action="${ctx}/security/banner/edit/${id}" method="post" >
	            <div class="new-goods">
	                <div class="bug-input input900">
	                <p>图片名称:</p><input type="text" name="title" value="${banner.title}" />
	                </div>
	                <div>
	                    <div class="shop-goods-pic">
	                        <p>宣传图片:</p>
	                        <div id="divFileNamePreview">
	                            <p>图片规格1600×435px</p>
	                            <div>
	                            <img id=imgName 
								<c:choose>          
									<c:when test="${not empty banner.img}"> 
									src="${ctx}/${banner.img}"
									</c:when> 
									<c:otherwise>src=""</c:otherwise>
								</c:choose> />
	                            </div>
	                            <a href="javascript:void(0)" class="file" id="upload">上传图片</a>
			                    <input id="fileToUpload" style="display: none" type="file" name="upfile">  
							    <input id="fileName" type="hidden" name="img" value="">
	                        </div>
	
	                    </div>
	
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
	                    <input type="submit" value="保 存" />
		                <input type="button" onclick="document.location.href='${ctx}/security/banner'" value="取 消" />
	                </div>
	            </div>
	
        	</form>
        </div>
    </section>
</body>
<script charset="utf-8" src="${ctx}/background/ajax/jquery-1.11.0.min.js"></script>
<script charset="utf-8" src="${ctx}/background/ajax/jquery-migrate-1.2.1.min.js"></script>
<script charset="utf-8" src="${ctx}/background/ajax/ajaxfileupload.js"></script>
<script type="text/javascript">

$(function () {
	
	//点击打开文件选择器  
    $('#upload').on('click', function() {  
        $('#fileToUpload').click();  
    });

    //选择文件之后执行上传  
    $('#fileToUpload').live('change', function() {
    	
    	var text = $(this).val();
	    var file_type = text.substring(text.lastIndexOf('.')+1,text.length).toLowerCase();
		if(!(file_type=='gif'||file_type=='jpg'||file_type=='jpeg'||file_type=='png'||file_type=='bmp')){
			warning("图片格式不正确！",1000);
			return false;
		}
		
        $.ajaxFileUpload({
        	type: 'POST',
            url:'${ctx}/security/banner/uploadBanner',  
            secureuri:false,  
            fileElementId:'fileToUpload',//file标签的id  
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
        
    });	
	
})

</script>
</html>