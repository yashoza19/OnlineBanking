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
				
				<li><a href="${pageContext.request.contextPath}/customer-home">Home</a></li>
				<li><a> Accounts </a>
					<div>
						<a href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking Account</a>
                                                <a href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings Account</a>
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
	
	<div>
	<br>
	<h3>Transfer Amount to Accounts which belong to Online Bank</h3>
	<br>
	<form:form action="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin" method="POST">
	<p>
		Please select the account you would like to transfer from:
		<select value="${accountType}" required="required" name="accountType" id="accountType" required="required">
			<option disabled="disabled" selected="selected">--Select your Account--</option>
			<option>Checking</option>
			<option>Savings</option>
		</select>
		<br><br>
		Please select the recipient:
		<select value="${recipientName}" required="required" name="recipientName" id="recipientName" required="required">
			<option disabled="disabled" selected="selected">--Select your Account--</option>
			<c:forEach items="${recipientList}" var="list">
				 <c:if test="${list != selected}">
				<option value="${list.name}">${list.name}</option>
				</c:if>
				
			
			</c:forEach>
		</select>
		
		<br><br>
		Please enter the amount you would like to withdraw:
		<input type="number" value="${amount}" name="amount" required>
		<br>
		<br>
		<button type="submit">Transfer</button>
	</p>
	
	
	 </form:form>
	
	</div>
	</body>
	

</html>