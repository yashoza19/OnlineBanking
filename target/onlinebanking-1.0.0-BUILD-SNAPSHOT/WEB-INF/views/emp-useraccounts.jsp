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

		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">

			<button type="submit">Logout</button>

		</form:form>
		</div>
	</nav>
	<br>
	
	<div>
	<h3>User Accounts List</h3><br><br>
	
	<table>
		<thead>
		<tr>
			<th>User Name</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>Checking Account</th>
			<th>Savings Account</th>
		</tr>
		</thead>
		
		<c:forEach items="${userAccountList}" var="list">
			<tr>
				<td>${list.userName}</td>
				<td>${list.firstName}</td>
				<td>${list.lastName}</td>
				<td>${list.email}</td>
				<td><a href="${pageContext.request.contextPath}/employee/emp-checkingtransactionlist?checkingAccountUser=${list.userName}&accountType=Checking">$ ${list.checkingAccount.accountBalance}</a></td>
				<td><a href="${pageContext.request.contextPath}/employee/emp-savingstransactionlist?savingsAccountUser=${list.userName}&accountType=Savings">$ ${list.savingsAccount.accountBalance}</a></td>
			</tr>
		</c:forEach>
		
		
	
	</table>
	</div>
	
</body>

</html>