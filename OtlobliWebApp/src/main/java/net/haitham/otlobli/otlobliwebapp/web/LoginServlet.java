/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
    OtlobliBLGateway gateway = (OtlobliBLGateway) appCon.getBean("otlobliBLGateway");

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String newpassword = DigestUtils.md5Hex(password);
        Integer userId;
        boolean flg = false;

        boolean remember = request.getParameter("remember") != null;
        try {
            /* TODO output your page here. You may use following sample code. */
            List<AdminUserBean> adminUserBeanList = gateway.findAdminAccounts();
            for (AdminUserBean adminUserBean : adminUserBeanList) {

                if (newpassword.equals(adminUserBean.getPassword()) && userName.equals(adminUserBean.getUsername())) {
                    String s = "Welcome" + userName;
                    userId = adminUserBean.getId();

                    Cookie ckUsername = new Cookie("username", userName);
                    Cookie ckPassword = new Cookie("password", password);
                    Cookie ckId = new Cookie("id", String.valueOf(userId));
                    ckUsername.setMaxAge(3600);
                    ckPassword.setMaxAge(3600);
                    ckId.setMaxAge(3600);

                    response.addCookie(ckUsername);
                    response.addCookie(ckPassword);
                    response.addCookie(ckId);
                    if (remember) {
                        Cookie ckUserNameRemember = new Cookie("username_remember", userName);
                        ckUserNameRemember.setMaxAge(3600);
                        response.addCookie(ckUserNameRemember);
                        Cookie ckPasswordRemember = new Cookie("password_remember", password);
                        ckPassword.setMaxAge(3600);
                        response.addCookie(ckPasswordRemember);
                        Cookie ckIdRemember = new Cookie("id_remember", String.valueOf(adminUserBean.getId()));
                        ckIdRemember.setMaxAge(3600);
                        response.addCookie(ckIdRemember);

                    }
                    flg = true;
                    response.sendRedirect("admin_profile.jsp?msg=" + s + "&userId=" + userId);
                }
            }
            if (!flg) {
                request.getRequestDispatcher("login.jsp").include(request, response);
                out.print("<h1 style='color:gold;'>sorry, check for username or password you have created during registeration or check your verification! </h1>");

            }
        } catch (Exception e) {
            out.println("EXCEPTION" + e + "sorry, check for username or password you have created during registeration or check your verification!");

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
