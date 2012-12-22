<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/_pages/category/loaisanpham_charts.jsp"></jsp:include>

<article class="module width_full">
	<header>
		<h3>Danh Sách Loại Sản Phẩm</h3>
	</header>
	<div class="module_content">
		<div id="result"></div>
		<h4>Tìm Kiếm Loại Sản Phẩm</h4>
		<form method="GET" id="filterForm" action="list">
			<table class="tableA tableB" cellspacing="0">
				<thead>
					<tr>
						<th class="id_col" title="Id">Id</th>
						<th class="" title="Tên Loại Sản Phẩm">Tên Loại Sản Phẩm</th>
						<th class="tt_col" title="Tình Trạng">Tình Trạng</th>
						<th></th>
					</tr>
					<tr>
						<td class="id_col" title="Id"><input type="text" size="7" 
							name="categoryId" id="categoryId" /></td>
						<td><input type="text" name="tenLoai" id="tenLoai"
							value="<c:out value="${categoryQuery.tenLoai}"/>" /></td>

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
					<th class="" title="Tên Loại Sản Phẩm">Tên Loại Sản Phẩm</th>
					<th class="" title="Ngày Cập Nhật">Ngày Cập Nhật</th>
					<th class="" title="Tình Trạng">Tình Trạng</th>
					<th class=""></th>
					<th class=""></th>
				</tr>
			</thead>
			<thead>
				<tr>
					<th class="id_col" title="Rank"></th>
					<th class="id_col" title=""></th>
					<th class="" title="Tên Sản Phẩm"><input id="tenLoai"
						type="text" class="" value="" /></th>
					<th class="" title="Ngày Cập Nhật"><input id="ngayCapNhat"
						type="text" class="" value="${currentDate}" /></th>
					<th class="" title="Tình Trạng"><select id="tinhTrangThemMoi">
							<option value="0">0</option>
							<option value="1">1</option>
					</select></th>
					<th class=""><input type="button" class="btt-refresh"
						value="Thêm" onclick="f_insert();" /></th>
					<th class=""><input type="button" class="btt-youtube"
						value="Hủy" /></th>
				</tr>
			</thead>

			<tbody>

				<c:set var="i" value="${1}" />
				<c:forEach var="lsp" items="${listLoaiSanPham}">
					<c:if test="${i % 2 == 0}">
						<c:set var="_current" value="even" />
					</c:if>
					<c:if test="${i % 2 != 0}">
						<c:set var="_current" value="odd" />
					</c:if>
					<tr id="col<c:out value="${lsp.id}"/>"
						class="<c:out value="${_current}"/>">
						<td class="id_col center_col"><c:out value="${i}" /></td>
						<td class="id_col center_col"><c:out value="${lsp.id}" /></td>
						<td class="sp_col left_col"><c:out value="${lsp.tenLoai}" /></td>
						<td class="ncn_col right_col"><c:out
								value="${lsp.ngayCapNhat}" /></td>
						<td class="tt_col"><c:out value="${lsp.tinhTrang}" /></td>
						<td class="id_col center_col slt_col"><a
							href="javascript:f_editUI('<c:out value="${lsp.id}"/>');"
							class="button">Edit</a></td>
						<td class="id_col center_col slb_col"><a
							href="javascript:f_deleteUI('<c:out value="${lsp.id}"/>');"
							class="button">Delete</a></td>
						<c:set var="i" value="${i+1}" />
					</tr>
					<tr id="${lsp.id}deleteconfirm"></tr>
					<tr id="${lsp.id}editconfirm"></tr>
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
										<c:param name="categorytId" value="${categorytQuery.id}" />
										<c:param name="tenLoai" value="${categoryQuery.tenLoai}" />
										<c:param name="tinhTrang" value="${categoryQuery.tinhTrang}" />
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
<!-- end of stats article -->
