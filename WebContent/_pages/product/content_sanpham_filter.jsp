<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h4>Tìm Kiếm Sản Phẩm</h4>
<form method="GET" id="filterForm" action="list">
	<table class="tableA tableB" cellspacing="0">
		<thead>
			<tr>
				<th class="id_col" title="Id">Id</th>
				<th class="" title="Tên Sản Phẩm">Tên Sản Phẩm</th>
				<th class="nsx_col" title="Nhà Sản Xuất">Nhà Sản Xuất</th>
				<th class="lsp_col" title="Chủng Loại">Chủng Loại</th>
				<th class="dt_col" title="Đối Tượng">Đối Tượng</th>
				<th class="tt_col" title="Tình Trạng">Tình Trạng</th>
				<th></th>
			</tr>
			<tr>
				<td class="id_col" title="Id"><input type="text"
					name="productId" id="productId" size="3" /></td>
				<td><input type="text" name="tenSanPham" id="tenSanPham"
					value="<c:out value="${productQuery.tenSanPham}"/>" /></td>
				<td><select id="nhaSanXuat" name="nhaSanXuat">
						<option value="-1">---------</option>
						<c:forEach var="nsx" items="${listNhaSanXuat}">
							<option value="<c:out value="${nsx.getId()}"/>">
								<c:out value="${nsx.getTenNhaSanXuat()}" />
							</option>
						</c:forEach>
				</select></td>
				<td><select id="loaiSanPham" name="loaiSanPham">
						<option value="-1">---------</option>
						<c:forEach var="lsp" items="${listLoaiSanPham}">
							<option value="<c:out value="${lsp.getId()}"/>">
								<c:out value="${lsp.getTenLoai()}" />
							</option>
						</c:forEach>
				</select></td>
				<td><select id="doiTuong" name="doiTuong">
						<option value="-1">---------</option>
						<c:forEach var="dt" items="${listDoiTuong}">
							<option value="<c:out value="${dt.getId()}"/>">
								<c:out value="${dt.getMoTa()}" />
							</option>
						</c:forEach>
				</select></td>
				<td><select id="tinhTrang" name="tinhTrang">
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