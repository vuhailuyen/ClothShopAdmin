<%-- 
    Document   : listSanPham
    Created on : Oct 23, 2012, 2:58:24 PM
    Author     : ElC
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<article class="module width_full">
	<header>
		<h3>Thêm Mới Sản Phẩm</h3>
	</header>
	<div class="module_content">

		<h3>Thông Tin Sản Phẩm</h3>
		<hr class="hr" />
		<div id="result"></div>
		<sf:form class="form" method="POST" action="" id="sanPhamThemMoi">
			<table cellspacing="0" class="tableA">
				<thead>
					<tr>
						<th>Tên Sản Phẩm(*)</th>
						<th>Loại Sản Phẩm(*)</th>
						<th>Nhà Sản Xuất(*)</th>
						<th>Đối Tượng(*)</th>
						<th>Giá Ban Đầu(*)</th>
						<th>Giá Hiện Tại(*)</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" name='tenSanPham' id="tenSanPham" /></td>
						<td><select id="loaiSanPham" name="loaiSanPham">
								<c:forEach var="lsp" items="${listLoaiSanPham}">
									<option value="<c:out value="${lsp.getId()}"/>">
										<c:out value="${lsp.getTenLoai()}" />
									</option>
								</c:forEach>
						</select></td>
						<td><select id="nhaSanXuat" name="nhaSanXuat">
								<c:forEach var="nsx" items="${listNhaSanXuat}">
									<option value="<c:out value="${nsx.getId()}"/>">
										<c:out value="${nsx.getTenNhaSanXuat()}" />
									</option>
								</c:forEach>
						</select></td>

						<td><select id="doiTuong" name="doiTuong">
								<c:forEach var="dt" items="${listDoiTuong}">
									<option value="<c:out value="${dt.getId()}"/>">
										<c:out value="${dt.getMoTa()}" />
									</option>
								</c:forEach>
						</select></td>

						<td><input type="text" name="giaBanDau" id="giaBanDau"
							value="0" /></td>
						<td><input type="text" name="giaHienTai" id="giaHienTai"
							value="0" /></td>

						<input type="hidden" id="task" name="task" value="add" />

					</tr>
					<tr>
						<th>Tình Trạng(*)</th>
						<th>Số Lượng Tồn(*)</th>
						<th>Số Lượt Xem(*)</th>
						<th>Số Lượng Đã Bán(*)</th>
						<th>Ngày Cập Nhật(*)</th>
						<th></th>
					</tr>
					<tr>
						<td><select id="tinhTrang" name="tinhTrang">
								<option>
									<c:out value="0" />
								</option>
								<option>
									<c:out value="1" />
								</option>
						</select></td>
						<td><input type="text" id="soLuongTon" name="soLuongTon"
							value="0" /></td>
						<td><input type="text" id="soLuotXem" name="soLuotXem"
							value="0" /></td>
						<td><input type="text" id="soLuongBan" name="soLuongBan"
							value="0" /></td>
						<td><input path="ngayCapNhat" type="text" id="ngayCapNhat"
							name="ngayCapNhat"
							value="<c:out value="${currentDate}"></c:out> " /></td>
						<td></td>
					</tr>
				</tbody>
			</table>

			<h3>Mô Tả Sản Phẩm</h3>
			<hr class="hr" />
			<textarea id="moTa" style="width: 70%; height: 200px" name="moTa"
				value=""></textarea>
			<p class="submit">
				<input class="btt-youtube" type="button" value="Xác Nhận"
					onclick="f_insert();" />
			</p>
		</sf:form>

	</div>

	</div>
</article>
<input id="serverName" type="hidden"
	value="<c:out value="${serverName}"/>" />
<input id="serverPort" type="hidden"
	value="<c:out value="${serverPort.toString()}"/>" />
<input id="serverProtocol" type="hidden"
	value="<c:out value="${serverProtocol}"/>" />
<input id="servletName" type="hidden"
	value="<c:out value="${servletName}"/>" />
<input id="serverContext" type="hidden"
	value="<c:out value="${serverContext}"/>" />