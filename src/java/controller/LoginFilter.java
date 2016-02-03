/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author Ahmed_telnet
 */
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        if(name!=null||password!=null)
        {
        if((name.equals("")||password.equals("")))
        {
        }
        else{
            fc.doFilter(request, response);
        }
    }
    }

    @Override
    public void destroy() {
        
    }
    
    
    
}
