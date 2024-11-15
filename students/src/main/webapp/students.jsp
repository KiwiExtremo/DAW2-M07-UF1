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
		
		<script>
			window.onload = initialize;

			function initialize() {
			}

			function showDeleteMessage(dni, url) {
				document.getElementById("dni-span").innerHTML = dni;
				document.getElementById("dni-url").href = url;
				document.getElementById("delete-container").style.display = "flex";
			}

			function cancelDeletion() {
				document.getElementById("delete-container").style.display = "none";
			}
		</script>
	</head>
	<body>
		<div class="container">
			<div class="header">
				<h1>Students.jsp</h1>
				<h4>An Institut Marianao application</h4>
			</div>
			<div class="new-student-container">
				<c:forEach var="cycle" items="${requestScope.cycles}" >
				
					<c:url value="RegisterServlet" var="registerUrl">
						<c:param name="cycle" value="${cycle}"/>
					</c:url>
					
					<a href="${registerUrl}" class="btn btn-register"><i class="fa fa-plus"></i> New <c:out value="${cycle}"/> register</a>
				</c:forEach>
			</div>
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
						<td class="modules">
							<c:forEach var="module" items="${student.modules}" >
								<p><c:out value="${module}" /></p>
							</c:forEach>
						</td>
						<td>
							<c:url value="remove" var="removeUrl">
								<c:param name="dni" value="${student.dni}"/>
							</c:url>
							
							<button type="button" class="btn btn-delete-message" onclick="showDeleteMessage('${student.dni}', '${removeUrl}')" ><i class="fa fa-trash"></i> Delete</button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<div id="delete-container">
				<div class="message">
					<h3>Attention</h3>
					<p>Are you sure you want to delete the student with DNI = <span id="dni-span">00000000X</span>?</p>
					<button type="button" class="btn" id="btn-cancel" onclick="cancelDeletion()"><i class="fa fa-close"></i> Cancel</button>
					<div class="spacer"></div>
					<a id="dni-url" href="#"><button type="button" class="btn" id="btn-delete"><i class="fa fa-trash"></i> Delete</button></a>
				</div>
			</div>
		</div>
	</body>
</html>