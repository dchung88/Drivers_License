<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Person</title>
</head>
<body>

	<h1>New Person</h1>
	<form:form action="/addperson" method="post" modelAttribute="personObject">		  
		<h2>
			<form:label path="firstName">First Name:</form:label>
				<form:input path="firstName"/>
		</h2>
		<h2>
			<form:label path="lastName">Last Name:</form:label>
			    <form:input path="lastName"/>
		</h2>
		<input type="submit" value="Create"/>	 
	</form:form>
</body>
</html>