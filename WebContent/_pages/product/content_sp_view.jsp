<%-- 
    Document   : listSanPham
    Created on : Oct 23, 2012, 2:58:24 PM
    Author     : ElC
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
    <header><h3>Danh Sách Sản Phẩm</h3></header>
    <div class="module_content">
        <div id="result"></div>
        <h4>Tìm Kiếm Sản Phẩm</h4>
        <form method="GET" id="filterForm" action="list">
            <table class="tableA tableB" cellspacing="0">
                <thead>
                    <tr>
                        <th class="id_col" title="Id"> Id </th>
                        <th class="" title="Tên Sản Phẩm">Tên Sản Phẩm</th>
                        <th class="nsx_col" title="Nhà Sản Xuất">Nhà Sản Xuất</th>
                        <th class="lsp_col" title="Chủng Loại">Chủng Loại</th>
                        <th class="dt_col" title="Đối Tượng">Đối Tượng</th>
                        <th class="tt_col" title="Tình Trạng">Tình Trạng</th>
                        <th></th>
                    </tr>
                    <tr>
                        <td class="id_col" title="Id"><input type="text" name="productId" id="productId" size="3"/></td>
                        <td><input type="text" name="tenSanPham" id="tenSanPham" value="<c:out value="${productParameters.tenSanPham}"/>"/></td>
                        <td><select id="nhaSanXuat" name="nhaSanXuat">
                                <option value="-1">---------</option>
                                <c:forEach var="nsx" items="${listNhaSanXuat}">
                                    <option value="<c:out value="${nsx.getId()}"/>">
                                        <c:out value="${nsx.getTenNhaSanXuat()}"/>
                                    </option>
                                </c:forEach>
                            </select></td>
                        <td> <select id="loaiSanPham" name="loaiSanPham">
                                <option value="-1">---------</option>
                                <c:forEach var="lsp" items="${listLoaiSanPham}">
                                    <option value="<c:out value="${lsp.getId()}"/>">
                                        <c:out value="${lsp.getTenLoai()}"/>
                                    </option>
                                </c:forEach>
                            </select></td>
                        <td><select id="doiTuong" name="doiTuong">
                                <option value="-1">---------</option>
                                <c:forEach var="dt" items="${listDoiTuong}">
                                    <option value="<c:out value="${dt.getId()}"/>">
                                        <c:out value="${dt.getMoTa()}"/>
                                    </option>
                                </c:forEach>
                            </select></td>
                        <td><select id="tinhTrang" name="tinhTrang">
                                <option value="-1">---</option>
                                <option value="0">0</option>
                                <option value="1">1</option>
                            </select></td>
                        <td><input type="submit" id="filter-confirm" class="btt-refresh" value="Tìm Kiếm" size="7" onclick=""/></td>
                    </tr>               
                <input type="hidden" name="page" id="page" value="1"/>
            </table>
        </form>
        <br/>
        <hr class="hr"/>
        <br/>
        <table class="tableA sortable" cellspacing="0">
            <thead>
                <tr>
                    <th class="id_col" title="Rank"> # </th>
                    <th class="id_col" title="Id"> Id </th>
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
                    <th class="" ></th>
                    <th class="" ></th>
                </tr>
            </thead>
            <tbody>

                <c:set var="i" value="${1}"/>
                <c:forEach var="sp" items="${listSanPham}">
                    <c:if test="${i % 2 == 0}">
                        <c:set var="_current" value="even"/>
                    </c:if>
                    <c:if test="${i % 2 != 0}">
                        <c:set var="_current" value="odd"/>
                    </c:if>
                    <tr id ="col<c:out value="${sp.id}"/>"class="<c:out value="${_current}"/>">
                        <td class="id_col center_col"><c:out value="${i}"/></td>
                        <td class="id_col center_col"><c:out value="${sp.id}"/></td>
                        <td class="sp_col left_col"><c:out value="${sp.tenSanPham}"/></td>
                        <td class="nsx_col left_col"><c:out value="${sp.nhaSanXuat.tenNhaSanXuat}"/></td>
                        <td class="lsp_col left_col"><c:out value="${sp.loaiSanPham.tenLoai}"/></td>
                        <td class="dt_col left_col"><c:out value="${sp.doiTuong.moTa}"/></td>
                        <td class="ncn_col right_col"><c:out value="${sp.ngayCapNhat}"/></td>
                        <td class="gbd_col center_col"><c:out value="${sp.giaBanDau}"/></td>
                        <td class="ght_col center_col"><c:out value="${sp.giaHienTai}"/></td>
                        <td class="tt_col"><c:out value="${sp.tinhTrang}"/></td>
                        <td class="id_col center_col slx_col"><c:out value="${sp.soLuotXem}"/></td>
                        <td class="id_col center_col slt_col"><c:out value="${sp.soLuongTon}"/></td>
                        <td class="id_col center_col slb_col"><c:out value="${sp.soLuongDaBan}"/></td>
                        <td class="id_col center_col slt_col"><a href="edit?id=<c:out value="${sp.id}"/>" class="button">Edit</a></td>
                        <td class="id_col center_col slb_col"><a href="javascript:f_deleteUI('<c:out value="${sp.id}"/>');" class="button">Delete</a></td> 
                        <c:set var="i" value="${i+1}"/>
                    </tr>
                    <tr id="${sp.id}deleteconfirm">
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="clear"></div>
        <div id="pagination">
            <table id="pagination-table">
                <tbody id="pagination-body">
                    <tr id="pagination-text">
                        <td id="pagination-numbers">
                            <c:forEach var="pageNumber" items="${pagingNumbers}">
                                <c:if test="${pageNumber.equals(current)}">
                                    <c:set var="_current" value="current"/>
                                </c:if>
                                <c:if test="${!pageNumber.equals(current)}">
                                    <c:set var="_current" value=""/>
                                </c:if>
                                <a href="page=<c:out value="${pageNumber.toString()}"/>" class="pagination-number <c:out value="${_current}"/>"><c:out value="${pageNumber.toString()}"/></a>
                            </c:forEach> 
                            <a class="pagination-number current">Tổng Số Trang: <b><c:out value="${totalPage}"/></b></a>
                        </td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>    
    </div>
    
    
</article><!-- end of stats article -->
