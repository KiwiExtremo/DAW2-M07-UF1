<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Students database</title>
		<link rel="stylesheet" type="text/css" href="styles.css" />
		
		<!-- Icon library -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	</head>
	<body>
		<div class="container">
			<div class="header">
				<h1>Register.jsp</h1>
				<h4>An Institut Marianao application</h4>
			</div>
			
			<h3>New <c:out value="${requestScope.cycle}"/> register</h3>
			
			<!-- Show errors that come from the servlet if the student data was incorrect -->
			<c:if var="hasError" test="${!empty requestScope.errors}">
				<div class="errors-container">
					<c:forEach var="error" items="${requestScope.errors}" >
						<p><i class="fa fa-exclamation-circle"></i> <c:out value="${error.key}" />: <c:out value="${error.value}" /></p>
					</c:forEach>
				</div>
			</c:if>
			
			<c:url value="RegisterServlet" var="registerUrl" />
			<form action="${registerUrl}" method="post">
				<label for="dni">DNI:</label>
				<input type="text" name="dni" id="dni" placeholder="dni" value="${hasError ? requestScope.studentData.dni : ''}">
				<br>
				<label for="name">Name:</label>
				<input type="text" name="name" id="name" placeholder="student name" value="${hasError ? requestScope.studentData.name : ''}">
				<br>
				<label for="surname">Surnames:</label>
				<input type="text" name="surname" id="surname" placeholder="student surnames" value="${hasError ? requestScope.studentData.surname : ''}">
				<br>
				<label for="email">Email:</label>
				<input type="text" name="email" id="email" placeholder="student email" value="${hasError ? requestScope.studentData.email : ''}">
				<br>
				<input type="hidden" name="cycle" id="cycle" value="${requestScope.cycle}">
				
				<label for="modules"><c:out value="${requestScope.cycle}"/>
				<c:forEach var="module" items="${requestScope.modules}" >
					<input type="checkbox" name="modules" id="${module}" value="${module}"/><label for="${module}"><c:out value="${module}"/></label>
					<br>
				</c:forEach>
				
				<c:url value="students" var="studentsUrl" />
				<a href="${studentsUrl}">Cancel</a>
				<input type="submit" value="Submit">
			</form>
		</div>
	</body>
</html>