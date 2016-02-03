/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Bean.Employee;
import static controller.LoginServlet.JDBC_DRIVER;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed_telnet
 */
public class EmployeesServlet extends HttpServlet {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Employee> list=listDB();
        req.setAttribute("EmployeeList", list);
        RequestDispatcher rd=req.getRequestDispatcher("/mangerpage.jsp");
        rd.forward(req, resp);
    }
    

    public ArrayList<Employee> listDB() {
        ArrayList<Employee> emps;
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            PreparedStatement pStm = conn.prepareStatement("select *  from employee.employee");
            ResultSet result = pStm.executeQuery();
            emps=new ArrayList<>();
            while(result.next())
            {
                Employee emp=new Employee();
                emp.setAddress(result.getString(1));
                emp.setEmail(result.getString(2));
                emp.setPassword(result.getString(3));
                emp.setId(result.getInt(4));
                emp.setName(result.getString(5));
                emp.setSalary(result.getInt(6));
                emp.setManager(result.getInt(7)==1);
                emps.add(emp);
                System.out.println("test");
            }
            pStm.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return emps;
    }
}
