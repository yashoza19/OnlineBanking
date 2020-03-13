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
        <a href="${pageContext.request.contextPath}/">Online
            Bank</a>

        <div>
            <ul>

                <li><a href="${pageContext.request.contextPath}/customer-home">Home</a></li>
                <li><a> Accounts </a>
                    <div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/account/checkingAccountDetails">Checking Account</a> <a
                            class="dropdown-item" href="${pageContext.request.contextPath}/account/savingsAccountDetails">Savings Account</a>
                    </div></li>

                <li><a> Transfer </a>
                    <div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/transfer/toSomeoneElseWithin">To Someone else from Online Bank</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/transfer/betweenAccounts">Between Accounts</a>
                    </div></li>	

                <li><a> Add Recipients </a>
                    <div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/transfer/internalrecipient/save">Add Recipient from Online Bank</a>
                    </div></li>
            </ul>




            <form:form action="${pageContext.request.contextPath}/logout"
                       method="POST">

                <button class="btn btn-danger" type="submit">Logout</button>

            </form:form>
        </div>
    <br>
    <div>
        <h3>Transfer Amount Between Accounts</h3>
        <br>

        <form:form action="${pageContext.request.contextPath}/transfer/betweenAccounts" method="POST">
            <p>
                Please select the account you would like to transfer from:
                <select value="${transferFrom}" required="required" name="transferFrom" id="transferFrom" required="required">
                    <option disabled="disabled" selected="selected">--Select your Account--</option>
                    <option>Checking</option>
                    <option>Savings</option>
                </select>
                <br><br>
                Please select the account you would like to transfer to:
                <select value="${transferTo}" required="required" name="transferTo" id="transferTo" required="required">
                    <option disabled="disabled" selected="selected">--Select your Account--</option>
                    <option>Checking</option>
                    <option>Savings</option>
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
<script>
    $("#transferFrom").change(function () {
        if ($("#transferFrom").val() == 'Checking') {
            $('#transferTo').val('Savings');
        } else if ($("#transferFrom").val() == 'Savings') {
            $('#transferTo').val('Checking');
        }
    });

    $("#transferTo").change(function () {
        if ($("#transferTo").val() == 'Checking') {
            $('#transferFrom').val('Savings');
        } else if ($("#transferTo").val() == 'Savings') {
            $('#transferFrom').val('Checking');
        }
    });
</script>

</html>