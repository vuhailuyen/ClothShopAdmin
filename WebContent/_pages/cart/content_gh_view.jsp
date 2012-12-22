<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
	<header>
		<h3>Danh Sách Giỏ Hàng</h3>
	</header>
	<div class="module_content">
		<div id="result"></div>
		<h4>Tìm Kiếm Loại Sản Phẩm</h4>
		<form method="GET" id="filterForm" action="list">
			<table class="tableA tableB" cellspacing="0">
				<thead>
					<tr>
						<th class="id_col" title="Khách Hàng">Khách Hàng</th>
						<th class="" title="Ngày Ghi Nhận">Ngày Ghi Nhận</th>
						<th class="tt_col" title="Tình Trạng">Tình Trạng</th>
						<th></th>
					</tr>
					<tr>
						<td class="id_col" title="Khách Hàng"><input type="text" size="9"
							name="thanhVien" id="thanhVien" value="<c:out value="${cartQuery.thanhVien.tenDangNhap}"/>" /></td>
						<td><input type="text" name="ngayGhiNhan" id="ngayGhiNhan"
							value="<c:out value="${cartQuery.ngayCapNhat}"/>" /></td>

						<td><select name="tinhTrang">
								<option value="-1">---</option>
								<option value="0">0</option>
								<option value="1">1</option>
						</select></td>
						<td><input type="submit" id="filter-confirm"
							class="btt-refresh" value="Tìm Kiếm" size="7" onclick="" /></td>
					</tr>
					<input type="hidden" name="page" id="page" value="1" />
			</table>
		</form>
		<br />
		<hr class="hr" />
		<br />
		<table class="tableA sortable" cellspacing="0">

			<thead>
				<tr>
					<th class="id_col" title="Rank">#</th>
					<th class="id_col" title="Id">Id</th>
					<th class="" title="Khách Hàng">Khách Hàng</th>
					<th class="" title="Tổng Số Sản Phẩm">Tổng Số Sản Phẩm</th>
					<th class="" title="Ngày Ghi Nhận">Ngày Ghi Nhận</th>
					<th class="" title="Tình Trạng">Tình Trạng</th>
					<th class=""></th>
					<th class=""></th>
				</tr>
			</thead>

			<tbody>

				<c:set var="i" value="${1}" />
				<c:forEach var="gh" items="${listGioHang}">
					<c:if test="${i % 2 == 0}">
						<c:set var="_current" value="even" />
					</c:if>
					<c:if test="${i % 2 != 0}">
						<c:set var="_current" value="odd" />
					</c:if>
					<tr id="col<c:out value="${gh.id}"/>"
						class="<c:out value="${_current}"/>">
						<td class="id_col slx_col center_col"><c:out value="${i}" /></td>
						<td class="id_col slt_col center_col"><c:out value="${gh.id}" /></td>
						<td class="id_col  left_col"><a class="text_stlye_1" href="/ClothShopAdmin/users/list?userId=${gh.thanhVien.id}&hoVaTen=&tenDangNhap=&tinhTrang=-1&page=1"><c:out value="${gh.thanhVien.tenDangNhap}" /></a></td>
						<td class="ght_col center_col"><c:out value="${gh.tongSoSanPham}" /></td>
						<td class="ncn_col right_col"><c:out value="${gh.ngayCapNhat}" /></td>
						<td class="tt_col"><c:out value="${gh.tinhTrang}" /></td>
						<td class="id_col center_col slt_col"><a href="detail?id=<c:out value="${gh.id}"/>&page=1" class="button">Detail</a></td>
						<td class="id_col center_col slb_col">
						<a href="javascript:f_cancelUI('<c:out value="${gh.id}"/>');" class="button">Cancel</a></td>
						<c:set var="i" value="${i+1}" />
					</tr>
					<tr id="${gh.id}deleteconfirm"></tr>
					<tr id="${gh.id}editconfirm"></tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="clear"></div>
		<div id="pagination">
			<table id="pagination-table">
				<tbody id="pagination-body">
					<tr id="pagination-text">
						<td id="pagination-numbers">
						<c:set var="filterTask" value="filter"/> 
						<c:set var="viewTask" value="view"/> 
						
							<c:if test="${task.equals(viewTask)}">
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
							</c:if> 
							<c:if test="${task.equals(filterTask)}">
								<c:forEach var="pageNumber" items="${pagingNumbers}">
									<c:if test="${pageNumber.equals(current)}">
										<c:set var="_current" value="current" />
									</c:if>
									<c:if test="${!pageNumber.equals(current)}">
										<c:set var="_current" value="" />
									</c:if>
									<c:url value="list?" var="pagingHref">
										<c:param name="thanhVien" value="${cartQuery.thanhVien.tenDangNhap}" />
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
							</c:if>
							</td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</article>
<script>
$("#ngayGhiNhan").datepicker({ dateFormat: "dd/mm/yy" });
</script>
<!-- end of stats article -->
