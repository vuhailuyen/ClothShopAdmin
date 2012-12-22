<%-- 
    Document   : listSanPham
    Created on : Oct 23, 2012, 2:58:24 PM
    Author     : ElC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
    <header><h3>Các Báo Cáo</h3></header>
    <div class="module_content">
        <div id="result"></div>
        <h4>Download Báo Cáo</h4>
        <table class="tableA sortable" cellspacing="0">

            <thead>
                <tr>
                    <th class="id_col" title="Id"> Id </th>
                    <th class="" title="Tên Báo Cáo">Tên Báo Cáo</th>
                    <th class="" title="Adobe Reader - Foxit Reader">PDF</th>
                    <th class="" title="Microsoft Excel">EXCEL</th>
                    <th class="" title="Microsoft Word">DOCX</th>
                    <th class="" title="Định Dạng CSV">CSV</th>
                    <th class="" title="Định Dạng RTF">RTF</th>                  
                    <th class="" ></th>
                    <th class="" ></th>
                </tr>
            </thead>
            <tbody>
            	<c:set var="i" value="${1}"/>
            	<c:forEach var="bc" items="${listBaoCao}">
                    <c:if test="${i % 2 == 0}">
                        <c:set var="_current" value="even"/>
                    </c:if>
                    <c:if test="${i % 2 != 0}">
                        <c:set var="_current" value="odd"/>
                    </c:if>
                <tr class="<c:out value="${_current}"/>">
                    <td class="id_col left_col"><c:out value="${i}"/></td>
                    <td class="sp_col center_col"><c:out value="${bc.displayName}"/></td>
                    <td class="id_col center_col"><a href="item/${bc.name}?type=pdf"> <img class="officeImg pdf" alt="LinkDown"/> </a></td>
                    <td class="id_col center_col"><a href="item/${bc.name}?type=xlsx"> <img class="officeImg xlsx" alt="LinkDown"/> </a></td>
                    <td class="id_col center_col"><a href="item/${bc.name}?type=docx"> <img class="officeImg docx" alt="LinkDown"/> </a></td>
                    <td class="id_col center_col"><a href="item/${bc.name}?type=csv"> <img class="officeImg csv" alt="LinkDown"/> </a></td>
                    <td class="id_col center_col"><a href="item/${bc.name}?type=rtf"> <img class="officeImg rtf" alt="LinkDown"/> </a></td> 
                    <td class="id_col center_col"></td> 
                    <td class="id_col center_col"></td> 
                </tr>
                <c:set var="i" value="${i+1}"/>
                </c:forEach>
            </tbody>
        </table>
        <div class="clear"></div>
    </div>   
</article><!-- end of stats article -->
<script type="text/javascript">
    f_officeImage();
</script>
