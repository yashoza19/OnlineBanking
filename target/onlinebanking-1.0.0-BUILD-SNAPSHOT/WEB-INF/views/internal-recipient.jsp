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
	
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Online
			Bank</a>

		<div>
			<ul>
				
				<li><a href="${pageContext.request.contextPath}/customer-home">Home</a></li>
				<li><a> Accounts </a>
					<div>
						<a href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking Account</a> <a href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings Account</a>
					</div></li>
					
				<li><a> Transfer </a>
					<div>
						<a href="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin">To Someone else from Online Bank</a> 
						<div></div>
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
	
	<br>
	<div>
		<h3>Recipient Details:</h3><br>
		
		<form:form action="${pageContext.request.contextPath}/transfer/internalrecipient/save" method="POST">
		<label>Name</label>
		<input type="text" value="${recipient.name}" name="name" id="name" required/><br>
		
		<label>Email</label>
		<input type="email" value="${recipient.email}" name="email" id="email" required/><br>
		
		<label>Phone</label>
		<input type="text" value="${recipient.phone}" name="phone" id="phone" required/><br>
		
		<label>Account Number</label>
		<input type="text" value="${recipient.accountNumber}" name="accountNumber" id="accountNumber" required/><br>
		
		<label>Description</label>
		<input type="text" value="${recipient.description}" name="description" id="description" required/><br>
		
		<button type="submit">Add Recipient</button>
		
		</form:form>
		<br>
		<h3>List of Recipients</h3><br>
		
		<table>
			<thead>
			<tr>
				<th>Recipient Name</th>
                                <th>Recipient Email</th>
                                <th>Recipient Phone</th>
                                <th>Recipient Account Number</th>
                                <th>Description</th>
                                <th>
                                    
                                </th>
			</tr>
			</thead>
			
			<c:if test="${not empty recipientList}">
				<c:forEach items="${recipientList}" var="list">
				
					<tr>
						<td>${list.name}</td>
						<td>${list.email}</td>
						<td>${list.phone}</td>
						<td>${list.accountNumber}</td>
						<td>${list.description}</td>
						<td><a href="${pageContext.request.contextPath}/transfer/deleteinternal?recipientName=${list.name}">Delete</a></td>
						
					</tr>
				
				</c:forEach>
			
			</c:if>
			
		</table>
		
		</div>
	</body>
</html>