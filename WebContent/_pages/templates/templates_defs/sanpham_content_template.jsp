<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	
<jsp:include page="/_pages/product/sanpham_charts.jsp"></jsp:include>
<article class="module width_full">
	<header>
		<h3>Danh Sách Sản Phẩm</h3>
	</header>
	<div class="module_content">

		<div id="result"></div>
		<tiles:insertAttribute name="filter" />
		<br />
		<hr class="hr" />
		<br />
		<tiles:insertAttribute name="list" />
		
		<div class="clear"></div>
		<tiles:insertAttribute name="paging" />
	</div>
</article>
<!-- end of stats article -->