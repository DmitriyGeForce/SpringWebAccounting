<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : admin
    Created on : Oct 18, 2016, 5:14:56 PM
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
                                        All money <br /> <br /> Admin
                                        </font></font> 
                                    </h3> 
                                </h1> 
                            </th> 
                        </tr> 
                    </table> 
                    <table border="1" bordercolor="cc215a" width="790" cellpadding="10"> 
                        <tr> 
                            <td rowspan="4" width="600" bgcolor="e6e6fa"> 
                                <c:choose>
                                    <c:when test="${requestScope.username != null || error != null}" >
                                        <h4>${requestScope.username} ${error}</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <h4>All Users</h4>
                                    </c:otherwise>
                                </c:choose>
                                <%@include file="/WEB-INF/jspf/admin-table.jspf" %>
                                <form:form method="post">
                                    Search by username: <input type="text" name="searchInput" value="<c:out value="${requestScope.username}" />" >
                                    <input type="submit" value="Enter" ></button>
                                    </td> 
                                    <!--Сайдбар--> 
                                    <td bgcolor="e6e6fa" align="left"> 
                                        <h3>Menu</h3> 
                                    <li><a href = "admin"> "All Users" </a></li>
                                    <li><a href = "logout"> "Logout" </a></li>
                            </td> 
                        </tr> 
                        <tr> 
                            <td bgcolor="e6e6fa" align="center"> 
                                <h3>Additional filters</h3> 
                                <%@include file="/WEB-INF/jspf/filter.jspf" %>
                                <input type="submit" value="Show" id="filter"></button>
                            </form:form>
                            </td> 
                        </tr> 
                    </table> 
                    <h2>
                        © Copyright
                    </h2>
        </table> 
    </body>  
</html>
