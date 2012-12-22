<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
	<header>
		<h3>Thông Tin Giỏ Hàng</h3>
	</header>
	<table class="tableA" cellspacing="0">
		<thead>
			<tr>
				<th>Mã Giỏ Hàng</th>
				<th>Khách Hàng</th>
				<th>Ngày Ghi Nhận</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class=""><c:out value="${gioHang.id}" /></td>
				<td class="slt_col"><a class="text_stlye_1"
					href="/ClothShopAdmin/users/list?userId=${gioHang.thanhVien.id}&hoVaTen=&tenDangNhap=&tinhTrang=-1&page=1">
					<c:out value="${gioHang.thanhVien.tenDangNhap}" /></a></td>
				<td><c:out value="${gioHang.ngayCapNhat}" /></td>
			</tr>
		</tbody>
	</table>
	<br />
	<hr class="hr" />
	<br />
	<header>
		<h3>Chi Tiết Giỏ Hàng</h3>
	</header>
	<div class="module_content">
		<div id="result"></div>
		<table class="tableA sortable" cellspacing="0">
			<thead>
				<tr>
					<th class="id_col" title="Rank">#</th>
					<th class="id_col" title="Id">Id</th>
					<th class="" title="Sản Phẩm">Sản Phẩm</th>
					<th class="" title="Số Lượng">Số Lượng</th>
					<th class="" title="Tổng Tiền">Tổng Tiền</th>
					<th class=""></th>
					<th class=""></th>
				</tr>
			</thead>
			<tbody>
				<c:set var="i" value="${1}" />
				<c:forEach var="ctgh" items="${listChiTietGioHang}">
					<c:if test="${i % 2 == 0}">
						<c:set var="_current" value="even" />
					</c:if>
					<c:if test="${i % 2 != 0}">
						<c:set var="_current" value="odd" />
					</c:if>
					<tr id="col<c:out value="${ctgh.id}"/>"
						class="<c:out value="${_current}"/>">
						<td class="id_col slx_col center_col"><c:out value="${i}" /></td>
						<td class="id_col slt_col center_col"><c:out
								value="${ctgh.id}" /></td>
						<td class="sp_col  left_col"><c:out
								value="${ctgh.sanPham.tenSanPham}" /></td>
						<td class="ght_col center_col"><c:out value="${ctgh.soLuong}" /></td>
						<td class="slx_col center_col"><c:out
								value="${ctgh.tongTien}" /></td>
						<td class="id_col center_col slt_col"><a
							href="javascript:f_detail('<c:out value="${ctgh.id}"/>');"
							class="button">Detail</a></td>
						<td class="id_col center_col slb_col"><a
							href="javascript:f_cancelUI('<c:out value="${ctgh.id}"/>');"
							class="button">Cancel</a></td>
						<c:set var="i" value="${i+1}" />
					</tr>
					<tr id="${ctgh.id}deleteconfirm"></tr>
					<tr id="${ctgh.id}editconfirm"></tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="clear"></div>
		<div id="pagination">
			<table id="pagination-table">
				<tbody id="pagination-body">
					<tr id="pagination-text">
						<td id="pagination-numbers"><c:set var="filterTask"
								value="filter" /> <c:set var="viewTask" value="view" /> <c:if
								test="${task.equals(viewTask)}">
								<c:forEach var="pageNumber" items="${pagingNumbers}">
									<c:if test="${pageNumber.equals(current)}">
										<c:set var="_current" value="current" />
									</c:if>
									<c:if test="${!pageNumber.equals(current)}">
										<c:set var="_current" value="" />
									</c:if>
									<a href="list?page=<c:out value="${pageNumber.toString()}"/>"
										class="pagination-number <c:out value="${_current}"/>"><c:out
											value="${pageNumber.toString()}" /></a>
								</c:forEach>
								<a class="pagination-number current">Tổng Số Trang: <b><c:out
											value="${totalPage}" /></b></a>
							</c:if> <c:if test="${task.equals(filterTask)}">
								<c:forEach var="pageNumber" items="${pagingNumbers}">
									<c:if test="${pageNumber.equals(current)}">
										<c:set var="_current" value="current" />
									</c:if>
									<c:if test="${!pageNumber.equals(current)}">
										<c:set var="_current" value="" />
									</c:if>
									<c:url value="list?" var="pagingHref">
										<c:param name="thanhVien"
											value="${cartQuery.thanhVien.tenDangNhap}" />
										<c:param name="ngayGhiNhan" value="${cartQuery.ngayCapNhat}" />
										<c:param name="tinhTrang" value="${cartQuery.tinhTrang}" />
										<c:param name="page" value="${pageNumber.toString()}" />
									</c:url>
									<a href="<c:out value="${pagingHref}"/>"
										class="pagination-number <c:out value="${_current}"/>"><c:out
											value="${pageNumber.toString()}" /></a>
								</c:forEach>
								<a class="pagination-number current">Tổng Số Trang: <b><c:out
											value="${totalPage}" /></b></a>
							</c:if></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</article>
<script>
	$("#ngayGhiNhan").datepicker({
		dateFormat : "dd/mm/yy"
	});
</script>
<!-- end of stats article -->
