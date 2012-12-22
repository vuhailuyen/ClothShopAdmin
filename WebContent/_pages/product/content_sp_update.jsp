<%-- 
    Document   : listSanPham
    Created on : Oct 23, 2012, 2:58:24 PM
    Author     : ElC
--%>


<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<article class="module width_full">
	<header>
		<h3>Chỉnh Sửa Sản Phẩm</h3>
	</header>
	<div class="module_content">

		<h3>Thông Tin Sản Phẩm</h3>
		<hr class="hr" />
		<div id="result"></div>
		<form class="form" method="POST" action="">
			<table cellspacing="0" class="tableA">
				<thead>
					<tr>
						<th><label for="name">Tên Sản Phẩm(*)</label></th>
						<th><label for="lsp">Loại Sản Phẩm(*)</label></th>
						<th><label for="nsx">Nhà Sản Xuất(*)</label></th>
						<th><label for="dt">Đối Tượng(*)</label></th>
						<th><label for="gbd">Giá Ban Đầu(*)</label></th>
						<th><label for="ght">Giá Hiện Tại(*)</label></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="text" name="tenSanPham" id="tenSanPham"
							value="${oldSanPham.getTenSanPham()}" /></td>
						<td><select id="loaiSanPham" name="loaiSanPham">
								<option
									value="<c:out value="${oldSanPham.getLoaiSanPham().getId()}"/>">
									<c:out value="${oldSanPham.getLoaiSanPham().getTenLoai()}" />
								</option>

								<c:forEach var="lsp" items="${listLoaiSanPham}">
									<option value="<c:out value="${lsp.getId()}"/>">
										<c:out value="${lsp.getTenLoai()}" />
									</option>
								</c:forEach>
						</select></td>
						<td><select id="nhaSanXuat" name="nhaSanXuat">
								<option
									value="<c:out value="${oldSanPham.getNhaSanXuat().getId()}"/>">
									<c:out value="${oldSanPham.getNhaSanXuat().getTenNhaSanXuat()}" />
								</option>

								<c:forEach var="nsx" items="${listNhaSanXuat}">
									<option value="<c:out value="${nsx.getId()}"/>">
										<c:out value="${nsx.getTenNhaSanXuat()}" />
									</option>
								</c:forEach>
						</select></td>

						<td><select id="doiTuong" name="doiTuong">
								<option
									value="<c:out value="${oldSanPham.getDoiTuong().getId()}"/>">
									<c:out value="${oldSanPham.getDoiTuong().getMoTa()}" />
								</option>

								<c:forEach var="dt" items="${listDoiTuong}">
									<option value="<c:out value="${dt.getId()}"/>">
										<c:out value="${dt.getMoTa()}" />
									</option>
								</c:forEach>
						</select></td>

						<td><input type="text" name="giaBanDau" id="giaBanDau"
							value="<c:out value="${oldSanPham.getGiaBanDau().toString()}"/>" /></td>
						<td><input type="text" name="giaHienTai" id="giaHienTai"
							value="<c:out value="${oldSanPham.getGiaHienTai().toString()}"/>" /></td>
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
									<c:out value="${oldSanPham.getTinhTrang().toString()}" />
								</option>
								<option>
									<c:out value="0" />
								</option>
								<option>
									<c:out value="1" />
								</option>
						</select></td>
						<td><input type="text" id="soLuongTon" name="soLuongTon"
							value="<c:out value="${oldSanPham.getSoLuongTon().toString()}"/>" /></td>
						<td><input type="text" id="soLuotXem" name="soLuotXem"
							value="<c:out value="${oldSanPham.getSoLuotXem().toString()}"/>" /></td>
						<td><input type="text" id="soLuongBan" name="soLuongBan"
							value="<c:out value="${oldSanPham.getSoLuongDaBan().toString()}"/>" /></td>
						<td><input type="text" id="ngayCapNhat" name="ngayCapNhat"
							value="<c:out value="${currentDate}"/>" /></td>
						<td></td>
					</tr>
				</tbody>
			</table>

			<h3>Mô Tả Sản Phẩm</h3>
			<hr class="hr" />
			<textarea id="moTa" style="width: 70%; height: 200px" name="moTa">
				<c:out value="${oldSanPham.getMoTa()}" />
			</textarea>
			<p class="submit">
				<input class="btt-youtube" type="button" value="Xác Nhận"
					onclick="f_updateProduct();" />
			<h3>Hình Ảnh Sản Phẩm - Đường Dẫn</h3>
			<hr class="hr" />
			<p class="submit">
			<table class="tableA" cellspacing="0">
				<tr>
					<th></th>
					<th></th>
					<th></th>
					<th></th>
				</tr>
				<tr>
					<td><input id="img_lnk_1" type='text' size="30"
						class='image-link' oninput="f_ImageChange('1');" /></td>
					<td><input id="img_lnk_2" type='text' size="30"
						class='image-link' oninput="f_ImageChange('2');" /></td>
					<td><input id="img_lnk_3" type='text' size="30"
						class='image-link' oninput="f_ImageChange('3');" /></td>
					<td><input id="img_lnk_4" type='text' size="30"
						class='image-link' oninput="f_ImageChange('4');" /></td>
					<td><input id="img_lnk_5" type='text' size="30"
						class='image-link' oninput="f_ImageChange('5');" /></td>
				</tr>
				<tr>
					<td><img id="img_1" /></td>
					<td><img id="img_2" /></td>
					<td><img id="img_3" /></td>
					<td><img id="img_4" /></td>
					<td><img id="img_5" /></td>
				</tr>
				<tr>
					<th colspan='4'><input class="btt-youtube" type="button"
						value="Xác Nhận" onclick="f_updateImage();" /></th>
				</tr>
			</table>
			<p />
		</form>
	</div>
</article>
<article class="module width_full">
	<header>
		<h3>Hình Ảnh Sản Phẩm</h3>
	</header>
	<div class="module_content">
		<table class="tableA" cellspacing="0">
			<c:set var="iterator" scope="page" value="${1}" />

			<c:forEach var="dt" items="${listHinhAnhSanPham}">
				<c:if test="${iterator % 4 == 1}">
					<tr>
				</c:if>
				<th><img class="product-image"src="<c:out value="${dt.getDuongDan()}"/>" /></th>
				<th>
					<label>Tình Trạng</label>
					<select class="image-status" name="image-${dt.getId().toString()}-status" data-id="${dt.getId().toString()}">
						<option>
							<c:out value="${dt.getTinhTrang().toString()}"/>
						</option>
						<option>
							<c:out value="0" />
						</option>
						<option>
							<c:out value="1" />
						</option>
					</select>
				</th>
				<c:if test="${iterator % 4 == 0}">
					</tr>
				</c:if>
				<c:set var="iterator" scope="page" value="${iterator+1}" />
			</c:forEach>
			<tr>
				<td colspan='8'>
					<input type="button" value="Xác Nhận" class="btt-youtube" onclick="f_setImagesStatus();" />
				</td>
			</tr>
		</table>

	</div>
</article>
<input type="hidden" id="productId"
	value="<c:out value="${oldSanPham.getId()}"/>" />