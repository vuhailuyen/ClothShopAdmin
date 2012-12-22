<%@ page pageEncoding="UTF-8" contentType="text/html" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<section id="secondary_bar">
		<div class="user">
			<p>Vũ Hải Luyện (<a href="#">0912277</a>)</p>
			<!-- <a class="logout_user" href="#" title="Logout">Logout</a> -->
		</div>
		<div class="breadcrumbs_container">
			<article class="breadcrumbs"><a href="#"> Admin</a> 
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('product')}"/>" > Sản Phẩm</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="current" >Loại Sản Phẩm</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('manufacturer')}"/>">Nhà Sàn Xuất</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('user')}"/>">Tài Khoản Người Dùng</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('cart')}"/>">Giỏ Hàng</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('comment')}"/>">Bình Luận</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('ad')}"/>">Quảng Cáo</a>
                            <div class="breadcrumb_divider"></div> 
                            <a class="" href="<c:out value="${pathMappings.getProperty('report')}"/>">Báo Cáo</a>
                        </article>
		</div>
</section><!-- end of secondary bar -->
	