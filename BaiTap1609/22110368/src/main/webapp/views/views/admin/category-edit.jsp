<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/taglib.jsp"%>
<form action="${pageContext.request.contextPath}/admin/category/update" method="post" enctype="multipart/form-data">
	<input type="text" id="categoryid" name="categoryid" value="${cate.categoryid}" hidden="hidden"><br> 
	<label for="categoryname">Category Name:</label><br> 
	<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}" required><br> 
	
	<label for="images">Images</label><br>
	<c:if test="${cate.images.substring(0,5) != 'https'}">
		<c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<c:if test="${cate.images.substring(0,5) == 'https'}">
		<c:url value="${cate.images}" var="imgUrl"></c:url>
	</c:if>
	<img height="150" width="200" src="${imgUrl}" /> 
	
	<input type="file" id="images" name="images" accept="image/*"><br><br>
	
	<label for="status">Status</label><br>
	<select id="status" name="status" required>
		<option value="1" <c:if test="${cate.status == 1}">selected</c:if>>Active</option>
		<option value="0" <c:if test="${cate.status == 0}">selected</c:if>>Inactive</option>
	</select><br><br>
	
	<input type="submit" value="Submit">
</form>
