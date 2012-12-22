<%-- 
    Document   : listSanPham
    Created on : Oct 23, 2012, 2:58:24 PM
    Author     : ElC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="module width_full">
    <header><h3>Quảng Cáo</h3></header>
    <div class="module_content">
        <div id="result"></div>
        <table class="tableA sortable" cellspacing="0">
            <thead>
                <tr>
                    <th class="id_col" title="Id"> Id </th>
                    <th class="" title="Vị Trí">Vị Trí</th>
                    <th class="" title="Hình Ảnh">Hình Ảnh</th>
                    <th class="" title="Ngày Hết Hạn">Ngày Hết Hạn</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
            	<c:set var="i" value="${1}"/>
            	<c:forEach var="qc" items="${listQuangCao}">
                    <c:if test="${i % 2 == 0}">
                        <c:set var="_current" value="even"/>
                    </c:if>
                    <c:if test="${i % 2 != 0}">
                        <c:set var="_current" value="odd"/>
                    </c:if>
                <tr class="<c:out value="${_current}"/>">
                    <td class="id_col left_col"><c:out value="${i}"/></td>
                    <td class="slt_col left_col"><h3><c:out value="${qc.position}"/></h3></td>
                    <td id="<c:out value="${qc.position}"/>-old-link" class="slx_col center_col"><c:out value="${qc.link}"/></td>
                    <td id="<c:out value="${qc.position}"/>-old-date" class="sp_col center_col"><c:out value="${qc.date}"/></td>
                    <td class="id_col center_col"><input type="button" class="btt-chrome"  value="Edit" onclick="f_editUI('<c:out value="${qc.position}"/>');"/></td> 
                    <td class="id_col center_col"><input type="button" class="btt-youtube" value="Cancel" onclick="f_exitEditUI('<c:out value="${qc.position}"/>');"/></td> 
                     
                </tr>
                <tr id="<c:out value="${qc.position}"/>"></tr>
                <tr class="<c:out value="${_current}"/>">
                    <td class="id_col left_col"></td>
                    <td class="slt_col left_col"><h3></h3></td>
                    <td class="slx_col center_col"><img class="banner-img" src="<c:out value="${qc.link}"/>"/></td>
                    <td class="sp_col center_col"></td>
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
</script>
