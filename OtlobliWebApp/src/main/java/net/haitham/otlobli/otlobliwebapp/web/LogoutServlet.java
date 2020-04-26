/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Cookie ckUserName = new Cookie("username", "");

            Cookie ckPassword = new Cookie("password", "");

            Cookie ckId = new Cookie("id", "");

            Cookie ckUserNameRemember = new Cookie("username_remember", "");

            Cookie ckPasswordRememeber = new Cookie("password_remember", "");

            Cookie ckIdRememeber = new Cookie("id_remember", "");

            ckUserNameRemember.setMaxAge(0);
            ckPasswordRememeber.setMaxAge(0);
            ckIdRememeber.setMaxAge(0);
            ckUserName.setMaxAge(0);
            ckPassword.setMaxAge(0);
            ckId.setMaxAge(0);
           
            response.addCookie(ckUserNameRemember);
            response.addCookie(ckPasswordRememeber);
            response.addCookie(ckIdRememeber);
            response.addCookie(ckUserName);
            response.addCookie(ckPassword);
            response.addCookie(ckId);
            request.getSession().invalidate();

            String msg = "You logged out successfully !";
            response.sendRedirect("logout.jsp?msgLogOut=" + msg);
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
