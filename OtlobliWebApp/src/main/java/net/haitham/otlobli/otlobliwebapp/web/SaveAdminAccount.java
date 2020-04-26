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
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.dsig.DigestMethod;
import net.haitham.otlobli.common.bean.AdminUserBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "SaveAdminAccount", urlPatterns = {"/SaveAdminAccount"})
public class SaveAdminAccount extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();
        boolean added;
        try {
            /* TODO output your page here. You may use following sample code. */
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String newpassword = DigestUtils.md5Hex(password);
            String myHash;
            Random random = new Random();
            random.nextInt(999999);
            myHash = DigestUtils.md5Hex("" + random);
            AdminUserBean adb = new AdminUserBean();
            adb.setUsername(userName);
            adb.setEmail(email);
            adb.setOriginalPassword(password);
            adb.setPassword(newpassword);
            adb.setHash(myHash);
            Byte active = 0;
            adb.setActive(active);

            AdminUserBean adBean = gateway.createAdminAccount(adb);
            if (adBean != null) {
                response.sendRedirect("verify.jsp");
            } else {

              
                 request.getRequestDispatcher("signup_admin.jsp").include(request, response);
                  out.print(" UserName or Password exist !");
                out.close();
            }
        } catch (Exception ex) {
            added = false;
            System.out.println(ex);
            out.println("<h1> ERROR MESSAGE::" + ex.getMessage() + ex + "</h1>");

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
