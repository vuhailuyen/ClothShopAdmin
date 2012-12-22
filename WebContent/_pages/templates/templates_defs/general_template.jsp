
<%@ page pageEncoding="UTF-8" contentType="text/html" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"   %>

<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <title>Admin Panel </title>
		<!-- include css -->
        <tiles:insertAttribute name="css"/>
        <!-- include js -->
        <tiles:insertAttribute name="js"/>
    </head>
    <body>
        <!-- include header -->
        <tiles:insertAttribute name="header"/>
        <!-- include admin navigation bar -->
        <tiles:insertAttribute name="navbar"/>
        <!-- include side bar -->
        <tiles:insertAttribute name="sidebar"/>

        <section id="main" class="column">
            <h4 class="alert_info">Xin chào admin, chúc bạn có một ngày làm việc hiệu quả.</h4>
            <tiles:insertAttribute name="content"/>
            <div class="clear"></div>            
            <div class="spacer"></div>
        </section>
        <tiles:insertAttribute name="serverInfo"/>
    </body>
    
</html>