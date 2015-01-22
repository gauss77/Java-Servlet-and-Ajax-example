package servlets;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import db_connect.DbConnect;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author EAMirzazade
 */
public class GetJson extends HttpServlet {

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

    }

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

        try {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            List<String> list;

            db = new DbConnect();
            //String[] req_data = request.getParameter("ids").toString().split(";");
            String query = "Select dep_name from department;";
            list = db.getDepListData(query);
            jsonObject.put("dep", list);
            
            query = "Select stuff_name from stuff";
            //list = new ArrayList<String>();
            list = db.getStuffListData(query);
            jsonObject.put("stuff", list);
         
            
            db.close();

            //jsonObject.put("test", req_data[1]);
            

            out.println(jsonObject);
            
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    DbConnect db;
    ResultSet rs;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            List<String> list;

            db = new DbConnect();
            String[] req_data = request.getParameter("ids").toString().split(";");
            String query = "select worker_fullname,"
                    + "username,"
                    + "dep_name"
                    + " from user inner join worker on worker.id=user.worker_id inner join department on department.id = user.dep_id where"
                    + " user.id=" + Integer.parseInt(req_data[0]) + " and worker.id = " + Integer.parseInt(req_data[1])
                    + "; ";
            list = db.getListData(query);

            db.close();

            jsonObject.put("test", req_data[1]);
            String[] a = new String[4];
            a[0] = "wes";
            a[1] = "qd";
            a[2] = "cc";
            a[3] = "ee";
            jsonObject.put("week", list);
            jsonObject.put("arr", a);

            out.println(jsonObject);
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
