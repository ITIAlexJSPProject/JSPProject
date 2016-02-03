/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Bean.Employee;
import static controller.EmployeesServlet.JDBC_DRIVER;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed_telnet
 */
public class EmployeeAction extends HttpServlet {

     static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
     static final String DB_URL = "jdbc:mysql://localhost/employee";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("editid"));
        String action=request.getParameter("actiontype");
        
        switch(action)
        {
            case "edit":
                Employee emp=getEmployee(id);
                request.setAttribute("employee", emp);
                RequestDispatcher rd=request.getRequestDispatcher("/EmployeeForm.jsp");
                rd.forward(request, response);
                break;
            case "delete":
                deleteEmployee(id);
                response.sendRedirect("/Employee_Manger/Mangerpage");
                break;
            case "add":
                break;
        }
        
    }
    
    public Employee getEmployee(int id) {
        Employee emp=new Employee();
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            PreparedStatement pStm = conn.prepareStatement("select *  from employee.employee where id= ?");
            pStm.setInt(1, id);
            ResultSet result = pStm.executeQuery();
            
            while(result.next())
            {
                emp.setAddress(result.getString(1));
                emp.setEmail(result.getString(2));
                emp.setPassword(result.getString(3));
                emp.setId(result.getInt(4));
                emp.setName(result.getString(5));
                emp.setSalary(result.getInt(6));
                emp.setManager(result.getInt(7)==1);
            }
            pStm.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return emp;
    }
    
     public boolean deleteEmployee(int id) {
         try {
             Employee emp=new Employee();
             
             Class.forName(JDBC_DRIVER);
             Connection conn = DriverManager.getConnection(DB_URL, "root", "");
             PreparedStatement pStm = conn.prepareStatement("DELETE FROM employee.employee WHERE id= ?");
             pStm.setInt(1, id);
             int result = pStm.executeUpdate();
             if(result==0)
             {
                 return false;
             }
             else{
                 return true;
             }
         } catch (ClassNotFoundException ex) {
             Logger.getLogger(EmployeeAction.class.getName()).log(Level.SEVERE, null, ex);
         } catch (SQLException ex) {
             Logger.getLogger(EmployeeAction.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
        }
     }
