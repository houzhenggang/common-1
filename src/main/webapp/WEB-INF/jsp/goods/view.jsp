<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<%request.setAttribute("MENU_INDEX", "goods");%>
<jsp:include page="/base/user/head" flush="true"/>

        <div class="management-center second-section">
            <div class="second-title">
                <a href="${ctx}/manage/goods/list">商品管理</a> <span> > 查看商品</span>
            </div>
            <form>
	            <div class="new-recruit">
	                <div class="bug-input input310">
	                <p>商品名称:</p><input type="text" name="name" value="${goods.name}" readonly="readonly"/>
	                </div>
	                <div class="bug-input input310">
	                <p>商品价格:</p><input type="text" name="price" value="${goods.price}" readonly="readonly"/>
	                </div>
	                <div class="bug-input select310">
	                    <p>商品类型:</p>
	                    <select name="categoryId" disabled="disabled"> 
	                    	<c:forEach items="${listCategory}" var="category" varStatus="vs">
								<option value="${category.id}" <c:if test="${goods.categoryId == category.id}">selected = "selected"</c:if> >${category.name}</option> 			
				            </c:forEach>
						</select>  
					</div>
	                <div class="bug-input input310">
	                <p>商品长度:</p><input type="text" name="longness" value="${goods.longness}" readonly="readonly"/>
	                </div>
	                <div class="bug-input input310">
	                <p>商品材质:</p><input type="text" name="material" value="${goods.material}" readonly="readonly"/>
	                </div>
	                <div class="bug-input select310">
	                    <p>风格类型:</p>
	                    <select name="styleId" disabled="disabled"> 
	                    	<c:forEach items="${listStyle}" var="style" varStatus="vs">
								<option value="${style.id}" <c:if test="${goods.styleId == style.id}">selected = "selected"</c:if> >${style.name}</option> 			
				            </c:forEach>
						</select>  
					</div>
	                <div class="bug-input input310">
	                <p>环保等级:</p><input type="text" name="envLevel" value="${goods.envLevel}" readonly="readonly"/>
	                </div>
	                <div class="bug-input input900">
	                <p>颜色分类:</p><input type="text" name="colors" value="${goods.colors}" readonly="readonly"/>
	                </div>
	                <div class="bug-input input900">
	                <p>关 键 词 :</p>&nbsp;<input type="text" name="keywords" value="${goods.keywords}" readonly="readonly"/>
	                </div>
	                
	                <div class="upload-img-div">
	                    <div class="shop-goods-pic">
	                        <p>商品封面:</p>
	                        <div id="divImgPreview">
	                            <p>图片规格690×1033px</p>
	                            <div>
	                            <img id="img" 
	                            <c:choose>          
									<c:when test="${not empty goods.coverImg}"> 
									src="${ctx}/${goods.coverImg}"
									</c:when> 
									<c:otherwise>src=""</c:otherwise>
								</c:choose>/>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="new-content">
	                    <p>商品详情:</p>
	                    <textarea name="detail" readonly="readonly">${goods.detail}</textarea>
	                </div>
	                <div class="upload-img-div">
	                	<div class="shop-goods-pic">
	                        <p>详情图片:</p>
	                        <div id="divImgPreview" style="margin-left:100px;">
			                <c:forEach items="${goods.detailImgList}" var="detailImg" varStatus="vs">
	                            <div style="float:left;margin:-20px 60px 40px 0;">
	                            	<img src="${ctx}/${detailImg}" />
	                            </div>
				            </c:forEach>
	                        </div>
	                    </div>
	                </div>
	                <div class="add-button" style="margin-bottom:50px">
		                <input type="button" onclick="document.location.href='${ctx}/manage/goods/list'" value="返 回" />
	                </div>
	            </div>
		</form>
        </div>
    </section>
</body>

</html>