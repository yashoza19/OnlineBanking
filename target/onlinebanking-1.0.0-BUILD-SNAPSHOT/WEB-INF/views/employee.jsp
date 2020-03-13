<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<html>

<head>
	<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Online Bank</title>

</head>

<body>
<nav>

		<a href="${pageContext.request.contextPath}/">Online
			Bank</a>

		<div>
			<ul>
				
				
				
			</ul>
			
		


		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">

			<button type="submit">Logout</button>

		</form:form>
		</div>
	</nav>
	
	<br>
	<div>

<h2>Employee Work Area</h2>

<hr>

<p>
	<a href="${pageContext.request.contextPath}/employee/emp-useraccounts">Show User Accounts</a>
</p>

<hr>

</div>
</body>

</html>









