<%@ page language="java" contentType="text/html; charset=UTF-8" 
       pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>** JPA Hibernate Home **</title>
</head>
<body>
<h2>
	** Hello JPA Hibernate !!! ** 
</h2>
<P> * Start Time: ${serverTime}. </P>
<hr>
<c:if test="${not empty requestScope.message}">
	<script>alert("${requestScope.message}")</script>
</c:if>
<hr>
&nbsp;&nbsp;<a href="blist">BoardList</a>
</body>
</html>
