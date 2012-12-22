<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
	<header>
		<h3>Danh Sách Nhà Sản Xuất</h3>
	</header>
	<div class="module_content">
		<div id="result"></div>
		<h4>Tìm Kiếm Nhà Sản Xuất</h4>
		<form method="GET" id="filterForm" action="list">
			<table class="tableA tableB" cellspacing="0">
				<thead>
					<tr>
						<th class="" title="Sản Phẩm">Sản Phẩm</th>	
						<th class="" title="Thành Viên">Thành Viên</th>	
						<th class="" title="Ngày Đăng">Ngày Đăng</th>
						<th class="tt_col" title="Tình Trạng">Tình Trạng</th>
						<th></th>
					</tr>
					<tr>
						<td class="id_col" title="Sản Phẩm"><input type="text" size="30" name="sanPham" id="sanPham" value="<c:out value="${commentQuery.sanPham.tenSanPham}"/>" /></td>
						<td class="id_col" title="Thành Viên"><input type="text" size="10" name="thanhVien" id="thanhVien" value="<c:out value="${commentQuery.thanhVien.tenDangNhap}"/>" /></td>
						<td class="id_col" title="Ngày Đăng"><input type="text" size="10" name="ngayDang" id="ngayDang" value="<c:out value="${commentQuery.ngayDang}"/>" /></td>
						<td><select name="tinhTrang">
								<option value="-2">-----</option>
								<option value="-1">-1</option>
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
					<th class="" title="Nội Dung">Nội Dung</th>	
					<th class="" title="Sản Phẩm">Sản Phẩm</th>	
					<th class="" title="Thành Viên">Thành Viên</th>	
					<th class="" title="Ngày Đăng">Ngày Đăng</th>
					<th class="" title="Tình Trạng">Tình Trạng</th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
					<th class="tt_col"><a class="btt-chrome" href="javascript:f_Approve();">Duyệt</a></th>
					<th class="ncn_col"><a class="btt-chrome" href="javascript:f_Disapprove();">Không Duyệt</a></th>
				</tr>
			</thead>
			<tbody>

				<c:set var="i" value="${1}" />
				<c:forEach var="bl" items="${listBinhLuan}">
					<c:if test="${i % 2 == 0}">
						<c:set var="_current" value="even" />
					</c:if>
					<c:if test="${i % 2 != 0}">
						<c:set var="_current" value="odd" />
					</c:if>
					<tr id="col<c:out value="${bl.id}"/>"
						class="<c:out value="${_current}"/>">
						<td class="id_col center_col"><c:out value="${i}" /></td>
						<td class="id_col center_col"><c:out value="${bl.id}" /></td>
						<td id='' class="sp_col left_col"><c:out value="${bl.noiDung}" /></td>
						<td id='' class="slx_col left_col"><c:out value="${bl.sanPham.tenSanPham}" /></td>
						<td id='' class="tt_col left_col"><c:out value="${bl.thanhVien.tenDangNhap}" /></td>
						<td id='' class="ncn_col center_col"><c:out value="${bl.ngayDang}" /></td>
						<td id='' class="tt_col"><c:out value="${bl.tinhTrang}" /></td>
						<td class="id_col"><input type="checkbox" name="approve_checkbox" value="${bl.id}"/></td>
						<td class="id_col"><input type="checkbox" name="disapprove_checkbox" value="${bl.id}"/></td>
						<c:set var="i" value="${i+1}" />
					</tr>
					<tr id="${bl.id}deleteconfirm"></tr>
					<tr id="${bl.id}editconfirm"></tr>
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
										<c:param name="sanPham" value="${commentQuery.sanPham.tenSanPham}" />
										<c:param name="thanhVien" value="${commentQuery.thanhVien.tenDangNhap}" />	
										<c:param name="ngayDang" value="${commentQuery.ngayDang}" />	
										<c:param name="tinhTrang" value="${commentQuery.tinhTrang}" />							
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
//
$("#ngayDang").datepicker({ dateFormat: "dd/mm/yy" });
$(".column").height($(document).height());

</script>
<!-- end of stats article -->
