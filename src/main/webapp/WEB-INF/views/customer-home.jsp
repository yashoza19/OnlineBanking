<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Online Bank</title>
</head>
<body>

	<nav> <a href="${pageContext.request.contextPath}/">Online
		Bank</a>

	<div>
		<ul>

			<li href="${pageContext.request.contextPath}/customer-home">Home</a></li>
			
			<li><a> Accounts </a>
				<div>
					<a href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking Account</a> 
                                        <a href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings Account</a>
				</div></li>

			<li><a> Transfer </a>
				
					<a href="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin">To
						Someone else from Online Bank</a>
					
					<a href="${pageContext.request.contextPath}/transfer/betweenAccounts">Between
						Accounts</a>
				</div></li>

			<li><a> Add Recipients </a>
				<div>
					<a href="${pageContext.request.contextPath}/transfer/internalrecipient/save">Add
						Recipient from Online Bank</a>
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
		<p>
			<h3>Welcome: <b><security:authentication
					property="principal.username" /></h3></b> <br> <br>
		<div>
			<div>
				<div>
					<div>
						<h5>Checking Account</h5>
						<p>$ ${checkingAccount.accountBalance}</p>
						<a href="${pageContext.request.contextPath}/account/checkingAccountDetails">View Details</a>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div>
						<h5>Savings Account</h5>
						<p>$ ${savingsAccount.accountBalance}</p>
						<a href="${pageContext.request.contextPath}/account/savingsAccountDetails"
							class="btn btn-primary">View Details</a>
					</div>
				</div>
			</div>
		</div>
		<br> <br>
		<div>
			<div>
				<div>
					<div>
						<h5>Deposit</h5>
						<p>Deposit amount to your accounts</p>
						<a href="${pageContext.request.contextPath}/account/depositAmount"
							class="btn btn-primary">Go to deposits</a>
					</div>
				</div>
			</div>
			<div>
				<div>
					<div>
						<h5>Withdraw</h5>
						<p>Withdraw amount from your accounts</p>
						<a href="${pageContext.request.contextPath}/account/withdrawAmount"
							class="btn btn-primary">Go to withdrawal</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
</html>