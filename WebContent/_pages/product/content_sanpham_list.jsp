<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="tableA sortable" cellspacing="0">
	<thead>
		<tr>
			<th class="id_col" title="Rank">#</th>
			<th class="id_col" title="Id">Id</th>
			<th class="" title="Tên Sản Phẩm">Tên Sản Phẩm</th>
			<th class="" title="Nhà Sản Xuất">Nhà Sản Xuất</th>
			<th class="" title="Chủng Loại">Chủng Loại</th>
			<th class="" title="Đối Tượng">Đối Tượng</th>
			<th class="" title="Ngày Cập Nhật">Ngày Cập Nhật</th>
			<th class="" title="Giá Cứng">Giá Cứng</th>
			<th class="" title="Giá Hiện Tại">Giá Hiện Tại</th>
			<th class="" title="Tình Trạng">Tình Trạng</th>
			<th class="" title="Số Lượt Xem">SLX</th>
			<th class="" title="Số Lượng Tồn">SLT</th>
			<th class="" title="Số Lượng Bán">SLB</th>
			<th class=""></th>
			<th class=""></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="i" value="${1}" />
		<c:forEach var="sp" items="${listSanPham}">
			<c:if test="${i % 2 == 0}">
				<c:set var="_current" value="even" />
			</c:if>
			<c:if test="${i % 2 != 0}">
				<c:set var="_current" value="odd" />
			</c:if>
			<tr id="col<c:out value="${sp.id}"/>"
				class="<c:out value="${_current}"/>">
				<td class="id_col center_col"><c:out value="${i}" /></td>
				<td class="id_col center_col"><c:out value="${sp.id}" /></td>
				<td class="sp_col left_col"><c:out value="${sp.tenSanPham}" /></td>
				<td class="nsx_col left_col"><c:out
						value="${sp.nhaSanXuat.tenNhaSanXuat}" /></td>
				<td class="lsp_col left_col"><c:out
						value="${sp.loaiSanPham.tenLoai}" /></td>
				<td class="dt_col left_col"><c:out value="${sp.doiTuong.moTa}" /></td>
				<td class="ncn_col right_col"><c:out value="${sp.ngayCapNhat}" /></td>
				<td class="gbd_col center_col"><c:out value="${sp.giaBanDau}" /></td>
				<td class="ght_col center_col"><c:out value="${sp.giaHienTai}" /></td>
				<td class="tt_col"><c:out value="${sp.tinhTrang}" /></td>
				<td class="id_col center_col slx_col"><c:out
						value="${sp.soLuotXem}" /></td>
				<td class="id_col center_col slt_col"><c:out
						value="${sp.soLuongTon}" /></td>
				<td class="id_col center_col slb_col"><c:out
						value="${sp.soLuongDaBan}" /></td>
				<td class="id_col center_col slt_col"><a
					href="edit?id=<c:out value="${sp.id}"/>" class="button">Edit</a></td>
				<td class="id_col center_col slb_col"><a
					href="javascript:f_deleteUI('<c:out value="${sp.id}"/>');"
					class="button">Delete</a></td>
				<c:set var="i" value="${i+1}" />
			</tr>
			<tr id="${sp.id}deleteconfirm">
			</tr>
		</c:forEach>
	</tbody>
</table>