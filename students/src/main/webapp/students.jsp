<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Students database</title>
	</head>
	<body>
	<h1>Students.jsp</h1>
	<h4>An Institut Marianao application</h4>
	
	<c:if test="${!empty requestScope.studentDNI}">
		<p>L'estudiant amb DNI <c:out value="${requestScope.studentDNI}" /> s'ha inserit correctament</p>
	</c:if>
	
	<c:forEach var="cycle" items="${requestScope.cycles}" >
	
		<c:url value="RegisterServlet" var="registerUrl">
			<c:param name="cycle" value="${cycle}"/>
		</c:url>
		
		<a href="${registerUrl}">New <c:out value="${cycle}"/> register</a>
	</c:forEach>
	
	<h3>Students</h3>
	<table>
		<tr>
			<th>DNI</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Email</th>
			<th>Cycle</th>
			<th>Modules</th>
			<th>Actions</th>
		</tr>
		<c:forEach var="student" items="${requestScope.students}" >
			<tr>
				<td><c:out value="${student.dni}" /></td>
				<td><c:out value="${student.name}" /></td>
				<td><c:out value="${student.surname}" /></td>
				<td><c:out value="${student.email}" /></td>
				<td><c:out value="${student.cycle}" /></td>
				<td>
					<c:forEach var="module" items="${student.modules}" >
						<p><c:out value="${module}" /></p>
					</c:forEach>
				</td>
				<td><a href="#">Remove</a>
			</tr>
		</c:forEach>
	</table>
	</body>
</html>