/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ahmed_telnet
 */
public class LoginServlet extends HttpServlet {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/employee";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        if (loginDB(name, password)) {
         if(isManger(name, password))
         {
             response.sendRedirect("/Employee_Manger/Mangerpage");
         }
         else{
             //response.sendRedirect("");
        }
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
    }

    public boolean loginDB(String Email, String password) {
        boolean flag = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            PreparedStatement pStm = conn.prepareStatement("select email  from employee.employee where email= ? AND password= ?");
            pStm.setString(1, Email);
            pStm.setString(2, password);

            ResultSet result = pStm.executeQuery();
            if (result.next()) {
                flag = true;
            }
            pStm.close();
            conn.close();
            return flag;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isManger(String Email,String password) {
        boolean flag = false;
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, "root", "");
            PreparedStatement pStm = conn.prepareStatement("select manger  from employee.employee where email= ? AND password= ?");
            pStm.setString(1, Email);
            pStm.setString(2, password);

            ResultSet result = pStm.executeQuery();
            if (result.next()) {
                int i=result.getInt(1);
                flag = i==1;
            }
            pStm.close();
            conn.close();
            return flag;

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
