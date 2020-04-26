/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "EditAdminAccountServlet", urlPatterns = {"/EditAdminAccountServlet"})
public class EditAdminAccountServlet extends HttpServlet {

    ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");

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
        PrintWriter out = response.getWriter();
        try {
            boolean updated = false;
            AdminUserBean adminUserBean = new AdminUserBean();

            int userId = Integer.parseInt(request.getParameter("userId"));
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String originalPassword = request.getParameter("password");
            String newpassword = DigestUtils.md5Hex(originalPassword);
            String encryptedPassword = request.getParameter("password_enc");
            String hash = request.getParameter("hash");
            Byte status = Byte.parseByte(request.getParameter("status"));
            adminUserBean.setId(userId);
            adminUserBean.setUsername(username);
            adminUserBean.setEmail(email);
            adminUserBean.setOriginalPassword(originalPassword);
            adminUserBean.setPassword(newpassword);
            adminUserBean.setHash(hash);
            adminUserBean.setActive(status);
            

            AdminUserBean adminUserBeanUpdated = gateway.updateAdminAccount(adminUserBean);
            if (adminUserBeanUpdated != null) {
                updated = true;
                response.sendRedirect("edit_admin_account.jsp?s2=" + updated+"&userId="+userId);
            } else {
                out.println("Sorry! unable to update record");
            }
        } catch (Exception e) {
            System.out.println(e);
            out.println("<h1> ERROR MESSAGE::" + e.getMessage() + e + "</h1>");

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
