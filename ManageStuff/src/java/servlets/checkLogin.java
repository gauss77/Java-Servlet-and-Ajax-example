package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author EAMirzazade
 */
public class checkLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            // load the driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            throw new UnavailableException(
                    "Login init() ClassNotFoundException: " + e.getMessage());
        } catch (IllegalAccessException e) {
            throw new UnavailableException(
                    "Login init() IllegalAccessException: " + e.getMessage());
        } catch (InstantiationException e) {
            throw new UnavailableException(
                    "Login init() InstantiationException: " + e.getMessage());
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection connection;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/manager", "root", "admin");
        } catch (SQLException e) {
            out.println("Login doGet() " + e.getMessage());
        }
        if (connection != null) {
          // store the connection

            String user = request.getParameter("username");
            String pass = request.getParameter("password");

            try {
                Statement statement = connection.createStatement();
                ResultSet resultset = statement.executeQuery("select count(*) from manager.worker where username='"
                        + user + "' and password='" + pass + "';");
                while (resultset.next()) {
                    if (resultset.getInt(1) <= 0) {
                        response.sendRedirect("index.html");
                    } else {
                       // session.setAttribute("username",  user);
                        response.sendRedirect("home");
                        return;
                    }
                }
                connection.close();
            } catch (Exception ex) {

            }
            
        }
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
