package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db_connect.DbConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.json.JsonObject;
import javax.json.JsonValue;

import org.json.simple.*;

/**
 *
 * @author EAMirzazade
 */
public class home extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Connection connection = DbConnect.connection;
    DbConnect db;
    ResultSet rs;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            db = new DbConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs = db.getData("select * from user inner join worker on worker.id=user.worker_id inner join department "
                + "on department.id = user.dep_id inner join "
                + "stuff on stuff.id=user.stuff_id;");
        /*HttpSession session = null;
         if (request.getSession(false) == null) {
         response.sendRedirect("index.html");
         } else {
         session = request.getSession();
         }*/
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet home</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/style.css\"/>");
            out.println("<script type=\"text/javascript\" src=\"js/jquery-1.11.2.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"js/ch_del.js\"></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1 id=\"h11\"></h1>");
            out.println("<table border=1>");
            out.println("<thead>");
            out.println("<th>Worker name</th>");
            out.println("<th>Username</th>");
            out.println("<th>Department</th>");
            out.println("<th>Stuff</th>");
            out.println("<thead>");

            while (rs.next()) {
                String code = rs.getInt("user.id") + ";" + rs.getInt("worker.id") + ";" + rs.getInt("department.id") + ";";
                out.println("<tr>");
                out.println("<td>" + rs.getString("worker_fullname") + "</td>");
                out.println("<td>" + rs.getString("username") + "</td>");
                out.println("<td>" + rs.getString("dep_name") + "</td>");
                out.println("<td>" + rs.getString("stuff_name") + "</td>");
                out.println("<td>"
                        + "<input type=\"button\" id=\"btnsedit\" data_val=" + code + "  value=\"Edit\"/>"
                        + "<input type=\"button\" id=\"btnsdelete\" data_val=" + code + "  value=\"Delete\"/>"
                        + "</td>");

                out.println("<tr>");
            }
            out.println("</table>");

            out.println("<div id='divv'></div>");
            out.println("<input type=\"button\" id=\"add\" value=\"insert data\" />");
            out.println("</body>");
            out.println("</html>");

            //session.invalidate();
        } catch (Exception ex) {
            ex.printStackTrace();
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
