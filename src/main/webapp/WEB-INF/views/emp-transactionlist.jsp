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
	<h3>Detailed Transaction List</h3>
	<br>
	<hr>
	
	<table>
		<thead>
		<tr>
			<th>UserName</th>
			<th>Account Type</th>
			<th>Date</th>
			<th>Description</th>
			<th>Type</th>
			<th>Status</th>
			<th>Amount</th>
			<th>Available Balance</th>
			<th>Transfer Type</th>
			<th>Approve/Deny</th>
		</tr>
		</thead>
		<c:if test="${not empty transactionList}">
			<c:forEach items="${transactionList}" var="list">
		<tr>
			<td>${userName}</td>
			<td>${accountType}</td>
			<td>${list.date}</td>
			<td>${list.description}</td>
			<td>${list.type}</td>
			<td>${list.status}</td>
			<td>$ ${list.amount}</td>
			<td>$ ${list.availableBalance}</td>
			<td>${list.transactionType}</td>
			<c:if test="${list.status == 'Pending'}">
			<td>
				<form:form 
				action="${pageContext.request.contextPath}/employee/approve-transaction?description=${list.description}&amount=${list.amount}&userName=${userName}&accountType=${accountType}&id=${list.id}" method="POST">
				<input type="hidden" name="description" value="${list.description}"/>
				<input type="hidden" name="amount" value="${list.amount}"/>
				<input type="hidden" name="userName" value="${userName}"/>
				<input type="hidden" name="accountType" value="${accountType}"/>
				<input type="hidden" name="id" value="${list.id}"/>
				<input type="hidden" name="transactionType" value="${list.transactionType}"/>
				<input type="hidden" name="receiversAccountNumber" value="${list.receiversAccountNumber}"/>
				<button type="submit">Approve</button>
				
				</form:form>
				
			</td>
			</c:if>
			
			
		</tr>
		</c:forEach>
		
		</c:if> 
		
		
		
			
	</table>
	<a  href="${pageContext.request.contextPath}/employee/emp-useraccounts">Back</a>
	</div>
	
</body>

</html>