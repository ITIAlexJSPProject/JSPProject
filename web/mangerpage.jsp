<%-- 
    Document   : mangerpage
    Created on : Feb 1, 2016, 9:10:20 AM
    Author     : Ahmed_telnet
--%>

<%@page import="Bean.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            ArrayList<Employee> list = (ArrayList<Employee>) request.getAttribute("EmployeeList");
            out.println(list.size());
        %>
        <table border="1px" style="border-style: solid ; border-color:#e0f0e8; padding:10px ;background-color:#f5faf9">
            <th>id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Password</th>
            <th>salary</th>
            <th>address</th>
            
            <c:forEach items="${requestScope.EmployeeList}" var="emp">
                <tr>
                    <td>${emp.id}</td>   
                <td>${emp.name}</td>
                <td>${emp.email}</td>
                <td>${emp.password}</td>
                <td>${emp.salary}</td>
                <td>${emp.address}</td>
                <td><form method="post" action="/Employee_Manger/EmployeeAction"><input type="hidden" name="actiontype" value="edit"><input type="hidden" name="editid" value=${emp.id}><input type="submit" name="commit" value="edit"></form></td>
                <td><form method="post" action="/Employee_Manger/EmployeeAction"><input type="hidden" name="actiontype" value="delete"><input type="hidden" name="editid" value=${emp.id}><input type="submit" name="commit" value="delete"></form></td>
                </tr>
            </c:forEach>
        </table>
        <td><form method="post" action="/Employee_Manger/form"><input type="hidden" value=${emp.id}><input type="submit" name="commit" value="Add new EMP"></form></td>       
    </body>
</html>
