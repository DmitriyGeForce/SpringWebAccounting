<%-- 
    Document   : entrance
    Created on : Oct 18, 2016, 5:14:37 PM
    Author     : User
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body bgcolor="d5713f">
        <table border="1" bordercolor="cc215a" width="800" align="center"> 
            <tr> 
                <td bgcolor="e6e6fa"> 
                    <table border="1" bordercolor="cc215a" width="790" height="200"
                           cellpadding="10"> 
                        <tr> 
                            <th background=""> 
                                <h1><font color="cc215a" face="Monotype Corsiva">
                                    Income and expenses</font> 
                                    <h3 align="left"> 
                                        <font color="cc215a"><font size=+2>
                                        Entrance
                                        </font></font> 
                                    </h3> 
                                </h1> 
                            </th> 
                        </tr> 
                    </table> 
                    <table border="1" bordercolor="cc215a" width="790" cellpadding="10"> 
                        <tr> 
                            <td rowspan="4" width="600" bgcolor="e6e6fa"> 
                        <form:form commandName="user" method="post">
                                    <h3>Hello, type your login and password or if do not have an account you can <a href = "register">register</a></h3><br />
                                    <div class="input-group">
                                        Login: <form:input path="username" required="required" />
                                    </div>
                                    <div class="input-group">
                                        Password: <form:password path="password" maxlength="20" />
                                    </div>
                                    <div class="btn" role="group">
                                        <input type="submit" value="Enter"></button>
                                        <input type="button" value="Clear" onclick="form.reset()"></button>
                                    </div>
                                    ${message} ${error}
                                </form:form>
                            </td> 
                            <!--Сайдбар--> 
                            <td bgcolor="e6e6fa" align="left"> 
                                <h3>Menu</h3> 
                        <li><a href = "register"> "Register" </a></li>
                </td> 
            </tr> 
            <tr> 
                <td bgcolor="e6e6fa" align="center"> 
                    <h3>Additional information</h3> 
                    <h4>Admin</h4>
                    Enter for admin, <br /> login: "admin" <br /> password: "12345" <br />
                    <h4>Users</h4>
                    Enter for user, <br /> login: "user" <br /> password: "12345" <br />
                    Enter for usa, <br /> login: "usa" <br /> password: "12345" <br />
                </td> 
            </tr> 
        </table> 
        <h2>
            © Copyright
        </h2>
    </table> 
</body>  
</html>
