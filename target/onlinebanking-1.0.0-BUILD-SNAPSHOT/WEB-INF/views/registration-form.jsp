<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!doctype html>
<html lang="en">

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
			
		


		
		</div>
	</nav>

	

	<div>
		
		<div>
			
			<div>

				<div>
					<div>Register New User</div>
				</div>

				<div>

					<!-- Registration Form -->
					<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm" 
						  	   modelAttribute="customer">

					    <!-- Place for messages: error, alert etc ... -->
					    <div>
					        <div>
					            <div>
								
									<!-- Check for registration error -->
									<c:if test="${registrationError != null}">
								
										<div>
											${registrationError}
										</div>
		
									</c:if>
																			
					            </div>
					        </div>
					    </div>

						<!-- User name -->
						<div>
							<span><i></i></span> 
							<form:errors path="userName" cssClass="error" />
							<form:input path="userName" placeholder="username (*)"/>
						</div>

						<!-- Password -->
						<div>
							<span class="input-group-addon"><i></i></span> 
							<form:errors path="password" cssClass="error" />
							<form:password path="password" placeholder="password (*)" />
						</div>
						
						<!-- Confirm Password -->
						<div>
							<span><i></i></span> 
							<form:errors path="matchingPassword" cssClass="error" />
							<form:password path="matchingPassword" placeholder="confirm password (*)"/>
						</div>
					
						
						<!-- First name -->
						<div>
							<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span> 
							<form:errors path="firstName" cssClass="error" />
							<form:input path="firstName" placeholder="first name (*)" />
						</div>
						
						<!-- Last name -->
						<div>
							<span><i></i></span> 
							<form:errors path="lastName" cssClass="error" />
							<form:input path="lastName" placeholder="last name (*)" />
						</div>
						
						<!-- Email -->
						<div>
							<span><i></i></span> 
							<form:errors path="email" cssClass="error" />
							<form:input path="email" placeholder="email (*)"/>
						</div>
						
						

						<!-- Register Button -->
						<div>						
							<div>
								<button type="submit">Register</button>
							</div>
						</div>
						
					</form:form>

				</div>

			</div>

		</div>

	</div>
</body>
</html>