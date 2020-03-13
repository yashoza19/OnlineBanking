<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
				
				<li><a  href="${pageContext.request.contextPath}/customer-home">Home</a></li>
				<li><a> Accounts </a>
					<div>
						<a href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking Account</a> 
                                                <a href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings Account</a>
					</div></li>
					
				<li><a> Transfer </a>
					<div>
						<a href="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin">To Someone else from Online Bank</a> 
			
          				<a href="${pageContext.request.contextPath}/transfer/betweenAccounts">Between Accounts</a>
					</div></li>	
					
				<li><a> Add Recipients </a>
					<div>
						<a href="${pageContext.request.contextPath}/transfer/internalrecipient/save">Add Recipient from Online Bank</a>
					</div></li>
				
			</ul>
			
		


		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">

			<button type="submit">Logout</button>

		</form:form>
		</div>
	</nav>
	
	<div class="container">
	<br>
	<h3>Checking Account Balance: <br> $ ${checkingAccount.accountBalance}</h3>
	
	<hr>
	<table>
	<thead>
		<tr>
			<th>Date</th>
			<th>Description</th>
			<th>Type</th>
			<th>Status</th>
			<th>Amount</th>
			<th>Available Balance</th>
		</tr>
		</thead>
		<c:if test="${not empty checkingTransactionList}">
			<c:forEach items="${checkingTransactionList}" var="list">
		<tr>
			<td>${list.date}</td>
			<td>${list.description}</td>
			<td>${list.type}</td>
			<td>${list.status}</td>
			<td>$ ${list.amount}</td>
			<td>$ ${list.availableBalance}</td>
			
			
		</tr>
		</c:forEach>
		
		</c:if> 
		
		
		
			
	</table>
	 </div>
	 
	
	</body>
</html>