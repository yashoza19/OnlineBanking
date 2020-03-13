<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>

<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Online Bank</title>

    </head>

    <body>



        <a href="${pageContext.request.contextPath}/">Online
            Bank</a>



        <div>

            <security:authorize access="hasRole('CUSTOMER')">
                <div>
                    <a 
                        href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking
                        Account</a> <a
                                   href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings
                        Account</a>
                </div>

                <a> Transfer </a>
                        <a
                           href="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin">To
                            Someone else from Online Bank</a> 
                        <a
                           href="${pageContext.request.contextPath}/transfer/betweenAccounts">Between
                            Accounts</a>
                    </div>

                <a> Add Recipients </a>
                    <div>
                        <a
                           href="${pageContext.request.contextPath}/transfer/internalrecipient/save">Add
                            User from Online Bank</a>
                    </div>
                </security:authorize>

        <form:form action="${pageContext.request.contextPath}/logout"
                   method="POST">

            <button type="submit">Logout</button>

        </form:form>
   
<br>

<div>

    <h2>Hi ${user.firstName}, Welcome To Online Bank</h2>


    <p>
    </p>

    <security:authorize access="hasRole('CUSTOMER')">
        <p>
            <a href="${pageContext.request.contextPath}/customer-home">Start
                Banking</a>

        </p>

    </security:authorize>

    <security:authorize access="hasRole('EMPLOYEE')">


        <p>
            <a href="${pageContext.request.contextPath}/employee">Employee
                Work Area</a>

        </p>

    </security:authorize>

    <hr>



</div>



</body>

</html>









