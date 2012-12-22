<%-- 
    Document   : server_info
    Created on : Oct 31, 2012, 1:29:00 PM
    Author     : ElC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<input id="serverName" type="hidden" value="<c:out value="${serverName}"/>" />
<input id="serverPort" type="hidden" value="<c:out value="${serverPort.toString()}"/>" />
<input id="serverProtocol" type="hidden" value="<c:out value="${serverProtocol}"/>" />
<input id="servletName" type="hidden" value="<c:out value="${servletName}"/>" />
<input id="serverContext" type="hidden" value="<c:out value="${serverContext}"/>" />
