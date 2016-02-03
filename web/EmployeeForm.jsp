<%-- 
    Document   : EmployeeForm
    Created on : Feb 1, 2016, 4:11:46 PM
    Author     : Ahmed_telnet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
.left{
background-color:#e0f0e8;
color:#617e78;
text-align:right;
width:300px;
height:30px;
display:inline-block;
}
.mar_dev{
margin:5px
}
</style>

</head>
<body>
<div style="border-style: solid ; border-color:#e0f0e8; padding:10px ;background-color:#f5faf9">
<div class="mar_dev" style="font-size:28px ;color:#55917a">
Register-Create Account<br>
</div>
<div  class="mar_dev" style="border-style:dashed; border-color:#55917a; background-color:#e0f0e8; padding:10px">
Field marked with <span style="color:red">*</span> are compulsory fields
</div>
<form method= POST action="action.jsp">
<div class="mar_dev">
<span class="left"><span style="color:red">*</span>Your name </span>
<span><input type="text" name="name" value=${requestScope.employee.name}></span><br>
</div>
<div class="mar_dev">
<span class="left"><span style="color:red">*</span>E-Mail ID </span>
<span><input type="email" name="email" value=${requestScope.employee.email}></span><br>
</div>
<div class="mar_dev">
<span class="left">Salary </span>
<span><input type="number" name="salary" value=${requestScope.employee.salary}></span><br>
</div>
<div class="mar_dev">
<span class="left"><span style="color:red">*</span>Password </span>
<span><input type="password" name="password" value=${requestScope.employee.password}></span><br>
</div>
    <div class="mar_dev">
<span class="left"><span style="color:red">*</span>address </span>
<span><input type="text" name="address" value="${requestScope.employee.address}"></span><br>
</div>
    <div>
         <label>
             <input type="checkbox" name="manger" id="ismanger" <c:if test="${requestScope.employee.manager}">
                checked</c:if>>
            Manger
          </label>
    </div><br>
<div class="mar_dev" style="padding-left:300px">
<span><input type="submit" value="Continue>>"></span>
<span><input type="button" type="reset" value="Reset"></span>
<br>
</div>
</form>

</div>
</body>
</html>