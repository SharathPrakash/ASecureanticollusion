/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import DbPack.DatabaseConnection;
import DbPack.TrippleDes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class getSign extends HttpServlet {

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
            throws ServletException, IOException, SQLException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);
            String getu=session.getAttribute("uid").toString();
            System.out.println("uid is "+getu);
            String getS=request.getParameter("sig");
            Connection con=DatabaseConnection.getCon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select * from register where username = '" + getu + "'");
            if(rs.next()){
            String getSignature=null;
            
            try{
            getSignature = new TrippleDes().encrypt(rs.getString("group_"));            
             System.out.println("group sig is = " + getSignature);
              out.print("<!DOCTYPE html>");
                    out.print("<html lang='en'>");
                    out.print("<head>");
                    out.print("<meta charset='utf-8'>");
                    out.print("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
                    out.print("<meta name='viewport' content='width=device-width, initial-scale=1'>");
                    out.print("<title>Example of Bootstrap 3 Panels</title>");
                    out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>");
                    out.print("<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css' integrity='sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp' crossorigin='anonymous'>");
                    out.print("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js'></script>");
                    out.print("<script src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js' integrity='sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa' crossorigin='anonymous'></script>");
                    out.print("<style type='text/css'>");
                    out.print(".bs-example{");
                    out.print("margin: 20px;");
                    out.print("width:250px;");
                    out.print(" }");
                    out.print("</style>");
                    out.print("</head>");
                    out.print("<body>");
                    out.print("<div class='bs-example'>");
                    out.print("<div class='panel panel-primary'>");
                    out.print("<div class='panel-heading'><h4>Your Signature Key</h4>");
                    out.print("</div><h2>");
                   // out.print("<div class='panel-body'><h3>Your Signature Key<h3></div>");
                    out.print("   <div class='panel-body'>   "+ getSignature);                                     
                    out.print("</daiv>");
                    out.print("</div>");
                    out.print("</body>");
                    out.print("</html>");                   
           //  out.print(""+getSignature);
                    
            }
            catch(Exception e){e.printStackTrace();}
            }
        } finally {
            out.close();
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(getSign.class.getName()).log(Level.SEVERE, null, ex);
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(getSign.class.getName()).log(Level.SEVERE, null, ex);
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
