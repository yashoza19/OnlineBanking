<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport"
              content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Online Bank</title>


    </head>


    <body>


        <a  href="${pageContext.request.contextPath}/">Online
            Bank</a>



        <div>

            <div>

                <div>

                    <div>
                        <div>
                            <h3>Sign In</h3>
                        </div>
                    </div>

                    <div>


                        <form:form
                            action="${pageContext.request.contextPath}/authenticateTheUser"
                            method="POST" class="form-horizontal">

                            <!-- Place for messages: error, alert etc ... -->
                            <div>
                                <div>
                                    <div>

                                        <!-- Check for login error -->

                                        <c:if test="${param.error != null}">

                                            <div class="alert alert-danger col-xs-offset-1 col-xs-10">
                                                Invalid username and password.</div>

                                        </c:if>

                                        <!-- Check for logout -->

                                        <c:if test="${param.logout != null}">

                                            <div class="alert alert-success col-xs-offset-1 col-xs-10">
                                                You have been logged out.</div>

                                        </c:if>

                                    </div>
                                </div>
                            </div>

                            <div>
                                <span></i></span> <input type="text" name="username" placeholder="username">
                            </div>

                            <div>
                                <span><i></i></span> <input type="password" name="password" placeholder="password">
                            </div>

                            <!-- Login/Submit Button -->
                            <div>
                                <div>
                                    <button type="submit">Login</button>
                                </div>
                            </div>


                        </form:form>

                    </div>

                </div>

                <div>
                    <a
                        href="${pageContext.request.contextPath}/register/showRegistrationForm">Register New User</a>
                </div>
                <br>
            </div>

        </div>
    </body>
</html>