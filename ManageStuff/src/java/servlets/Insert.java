/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import db_connect.DbConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EAMirzazade
 */
public class Insert extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
        PrintWriter out = response.getWriter();
        DbConnect db = new DbConnect();
        boolean test1 = false, test2 = false;
        String name = request.getParameter("full_name");
        String user = request.getParameter("username");
        String dep_id = request.getParameter("dep_name");
        String stuff_id = request.getParameter("stuff");
        String pass = request.getParameter("password");
        try {
            String query_to_user = "";
            String query_to_worker = "insert into worker(worker_fullname, password, username ) values('" + name + "', '" + pass + "', '" + user + "');";
            test1 = db.insertData(query_to_worker);
            int worker_id = db.getDataSingle("select id from worker where worker_fullname='" + name + "' and password='" + pass + "' and username='" + user + "';","id");
            
            if (!test1) {
                query_to_user = "insert into user(dep_id, worker_id, stuff_id) values('" + dep_id + "', '" + worker_id + "','" + stuff_id + "')";
                test2 = db.insertData(query_to_user);
                if (!test2) {
                    response.sendRedirect("home");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
