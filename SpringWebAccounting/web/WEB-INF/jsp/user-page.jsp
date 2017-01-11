<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : table
    Created on : Oct 18, 2016, 5:14:18 PM
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
                                        All money <br /> <br /> ${username}
                                        </font></font> 
                                    </h3> 
                                </h1> 
                            </th> 
                        </tr> 
                    </table> 
                    <table border="1" bordercolor="cc215a" width="790" cellpadding="10"> 
                        <tr> 
                            <td rowspan="4" width="600" bgcolor="e6e6fa"> 
                                <%@include file="/WEB-INF/jspf/table.jspf" %>
                                <form:form commandName="row" method="post">
                                    <input type="hidden" value="${editSample.accountingTableId}" name="editSampleId"/>
                                    <table border="1">
                                        <tbody>
                                            <tr>
                                                <td>Type of action</td>
                                                <td><select name="typeOfAction">
                                                        <c:choose>
                                                            <c:when test="${editSample.typeOfAction.equals('Expense')}">
                                                                <option value="Income">Income</option>
                                                                <option selected value="Expense">Expense</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option selected value="Income">Income</option>
                                                                <option value="Expense">Expense</option>
                                                            </c:otherwise> 
                                                        </c:choose>
                                                    </select></td>
                                            </tr>
                                            <tr>
                                                <td>Amount</td>
                                                <td><form:input path="amount" value="${editSample.amount}" /></td> ${error}
                                            </tr>
                                            <tr>
                                                <c:choose>
                                                    <c:when test="${editSample != null}">
                                                        <td><input type="submit" name ="edit" value="Edit" /></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <td><input type="submit" name ="add" value="Add" /></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                <td><input type="reset" value="Clear" /></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form:form>
                            </td> 
                            <!--Сайдбар--> 
                            <td bgcolor="e6e6fa" align="left"> 
                                <h3>Menu</h3> 
                        <li><a href = "user-page"> "All money" </a></li>
                        <li><a href = "logout"> "Logout" </a></li>
                </td> 
            </tr> 
            <tr> 
                <td bgcolor="e6e6fa" align="center"> 
                    <h3>Additional filters</h3> 
                    <form method="post">
                        <%@include file="/WEB-INF/jspf/filter.jspf" %>
                        <input type="submit" value="Show" name="filter"></button>
                    </form>
                </td> 
            </tr> 
        </table> 
        <h2>
            © Copyright
        </h2>
    </table> 
</body>  
</html>