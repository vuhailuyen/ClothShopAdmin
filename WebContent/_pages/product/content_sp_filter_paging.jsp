<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="pagination">
	<table id="pagination-table">
		<tbody id="pagination-body">
			<tr id="pagination-text">
				<td id="pagination-numbers">
					<c:forEach var="pageNumber" items="${pagingNumbers}">
						<c:if test="${pageNumber.equals(current)}">
							<c:set var="_current" value="current" />
						</c:if>
						<c:if test="${!pageNumber.equals(current)}">
							<c:set var="_current" value="" />
						</c:if>
						<c:url value="list?" var="pagingHref">
                            <c:param name="productId"      value="${productQuery.id}"/>
                            <c:param name="tenSanPham"  value="${productQuery.tenSanPham}"/>
                            <c:param name="nhaSanXuat"     value="${productQuery.nhaSanXuat.id}"/>
                            <c:param name="loaiSanPham"     value="${productQuery.loaiSanPham.id}"/>
                            <c:param name="doiTuong"      value="${productQuery.doiTuong.id}"/>
                            <c:param name="tinhTrang"      value="${productQuery.tinhTrang}"/>
                            <c:param name="page"  value="${pageNumber.toString()}"/>
                        </c:url>
						<a href="<c:out value="${pagingHref}"/>" class="pagination-number <c:out value="${_current}"/>"><c:out value="${pageNumber.toString()}"/></a>
					</c:forEach> 
					<a class="pagination-number current">Tổng Số Trang: <b><c:out value="${totalPage}" /></b></a>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>
