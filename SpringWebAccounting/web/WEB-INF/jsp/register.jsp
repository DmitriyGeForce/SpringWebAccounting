<%-- 
    Document   : register
    Created on : Oct 20, 2016, 4:13:44 PM
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
                                    Welcome</font> 
                                    <h3 align="left"> 
                                        <font color="cc215a"><font size=+2>
                                        Register
                                        </font></font> 
                                    </h3> 
                                </h1> 
                            </th> 
                        </tr> 
                    </table> 
                    <a href = "entrance"><<"Back"</a>
                    <table border="1" bordercolor="cc215a" width="790" cellpadding="10"> 
                        <tr> 
                            <td rowspan="4" width="600" bgcolor="e6e6fa"> 
                                <form:form commandName="user" method="post">
                                    <div class="input-group">
                                        Create new login: <form:input path="username" required="required" />
                                    </div>
                                    <div class="input-group">
                                        Create new password: <form:password path="password" maxlength="20" />
                                    </div>
                                    <div class="btn" role="group">
                                        <input type="submit" value="Register"></button>
                                        <input type="button" value="Clear" onclick="form.reset()"></button>
                                    </div>
                                </form:form>
                            </td> 
                            <!--Сайдбар--> 
                            <td bgcolor="e6e6fa" align="left"> 
                                <h3>Menu</h3> 
                        <li><a href = "entrance"> Entrance </a></li>
                </td> 
            </tr> 
            <tr> 
                <td bgcolor="e6e6fa" align="center"> 
                    <h3>Additional information</h3> 
                    We are glad, that you've decided to register to our service <br /> ${message}
                </td> 
            </tr>  
        </table>
        <h2>
            © Copyright
        </h2>
    </td>
</tr>
</table> 
</body>  
</html>
