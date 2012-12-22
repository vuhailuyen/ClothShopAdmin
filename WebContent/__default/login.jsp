<%@ page pageEncoding="UTF-8" contentType="text/html" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log In</title>
        <meta charset="UTF-8" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reset.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.msg.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/structure.css">
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.js"  ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.center.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.msg.min.js"></script>
    </head>

    <body onload="document.f.j_username.focus();">
        <form name ="f" action="<c:url value='j_spring_security_check'/>" class="box login" method="POST">
            <fieldset class="boxBody">
                <label>Username</label>
                <input id="username_input" name='j_username' type="text" tabindex="1" placeholder="Username" value="" required="true">
                <label>Password</label>
                <input id="password_input" name='j_password' type="password" tabindex="2" required="true">
            </fieldset>
            <footer>
                <input  type="submit" class="btnLogin" value="Login" tabindex="4"/>
            </footer>
            <c:if test="${success.equals('0')}">
            	<script>
            	  $.msg({ content : 'Wrong Username or Password' });
            	</script>
            </c:if>
        </form>
        <div id="result"></div>
        <footer id="main">
            
        </footer>
    </body>
</html>
