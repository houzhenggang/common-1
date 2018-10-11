<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "goods");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/manage/goods/list">商品管理</a> <span> > 修改商品</span>
            </div>
            <form action="${ctx}/manage/goods/edit/${id}" method="post" enctype="multipart/form-data">
	            <div class="new-recruit">
	                <div class="bug-input input310">
	                <p>商品名称:</p><input type="text" name="name" value="${goods.name}" />
	                </div>
	                <div class="bug-input input310">
	                <p>商品价格:</p><input type="text" name="price" value="${goods.price}" placeholder="单位：分"/>
	                </div>
	                <div class="bug-input select310">
	                    <p>商品类型:</p>
	                    <select name="categoryId"> 
	                    	<c:forEach items="${listCategory}" var="category" varStatus="vs">
								<option value="${category.id}" <c:if test="${goods.categoryId == category.id}">selected = "selected"</c:if> >${category.name}</option> 			
				            </c:forEach>
						</select>  
					</div>
	                <div class="bug-input input310">
	                <p>商品长度:</p><input type="text" name="longness" value="${goods.longness}" />
	                </div>
	                <div class="bug-input input310">
	                <p>商品材质:</p><input type="text" name="material" value="${goods.material}" />
	                </div>
	                <div class="bug-input input310">
	                <p>商品风格:</p><input type="text" name="style" value="${goods.style}" />
	                </div>
	                <div class="bug-input input310">
	                <p>环保等级:</p><input type="text" name="envLevel" value="${goods.envLevel}" />
	                </div>
	                <div class="bug-input input900">
	                <p>颜色分类:</p><input type="text" name="colors" value="${goods.colors}" />
	                </div>
	                <div class="bug-input input900">
	                <p>关 键 词 :</p>&nbsp;<input type="text" name="keywords" value="${goods.keywords}" />
	                </div>
	                
	                <div class="upload-img-div">
	                    <div class="shop-goods-pic">
	                        <p>商品封面:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格360×460px</p>
	                            <div>
	                            <img id="img" 
	                            <c:choose>          
									<c:when test="${not empty goods.coverImg}"> 
									src="${ctx}/${goods.coverImg}"
									</c:when> 
									<c:otherwise>src=""</c:otherwise>
								</c:choose>/>
	                            </div>
                            	<a href="javascript:void(0)" class="file" id="upload">上传图片</a>
			                    <input id="fileToUpload" style="display: none" type="file"
			                    onchange="PreviewImage(this,'img','divImgPreview')" name="img">  
	                        </div>
	                    </div>
	                </div>
	                <div class="new-content">
	                    <p>商品详情:</p>
	                    <textarea name="detail">${goods.detail}</textarea>
	                </div>
	                <div class="bug-input input900">
	                <p>详情图片:</p><input type="hidden" id="detailImgs" name="detailImgs" value="${goods.detailImgs}" />
	                </div>
	                <div id="uploadImg" style="margin:auto;"></div> 
	                <div class="add-button" style="margin-bottom:50px">
	                    <input type="submit" value="保 存" />
		                <input type="button" onclick="document.location.href='${ctx}/manage/goods/list'" value="取 消" />
	                </div>
	            </div>
		</form>
        </div>
    </section>
</body>
<!-- 引用控制层插件样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/uploadFile/control/css/zyUpload.css" >
<!-- 引用一般上传图片插件 -->
<script type="text/javascript" src="${ctx}/background/js/uploadFile.js"></script>
<!-- 引用核心层插件 -->
<script type="text/javascript" src="${ctx}/uploadFile/core/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${ctx}/uploadFile/control/js/zyUpload.js"></script>
<!-- 引用初始化JS -->
<script type="text/javascript">
$(function(){
	//点击打开文件选择器  
    $('#upload').on('click', function() {  
        $('#fileToUpload').click();  
    });
	
	// 初始化插件
	$("#uploadImg").zyUpload({
		width            :   "660px",                 // 宽度
		height           :   "400px",                 // 高度
		itemWidth        :   "120px",                 // 文件项的宽度
		itemHeight       :   "100px",                 // 文件项的高度
		url              :   "http://127.0.0.1:8080/zmy-common/common/upload/many",  // 上传文件的路径
		multiple         :   true,                    // 是否可以多个文件上传
		dragDrop         :   true,                    // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){          // 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
		},
		onDelete: function(file, surplusFiles){       // 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file){                    // 文件上传成功的回调方法
			console.info("此文件上传成功：");
			console.info(file);
		},
		onFailure: function(file){                    // 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){           // 上传完成的回调方法
			console.info("文件上传完成");
			console.info(responseInfo);
		}
	});
});
</script>
</html>