/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import Exceptions.InvalidPasswordException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.DeliveryAreaBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "SaveProviderUserServlet", urlPatterns = {"/SaveProviderUserServlet"})
public class SaveProviderUserServlet extends HttpServlet {

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
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();
        boolean added = false;

        try {
            /* TODO output your page here. You may use following sample code. */

            String providerIdText = request.getParameter("providerId");
            Integer providerId = Integer.parseInt(providerIdText);
            String branchesIdText = request.getParameter("branches");
            Integer branchId = Integer.parseInt(branchesIdText);
            String userName = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String encrypted_password = DigestUtils.md5Hex(password);
            String status = request.getParameter("status");
            Byte active = 0;
            if (status.toLowerCase().equals("yes")) {
                active = 1;
            } else {
                active = 0;
            }

            ProviderUserBean providerUserBean = new ProviderUserBean();
            providerUserBean.setPassword(password);
            providerUserBean.setEncrypted_password(encrypted_password);
            if(email != null){
            providerUserBean.setEmail(email);
            }
            providerUserBean.setUsername(userName);
            providerUserBean.setActive(active);

            ProviderBean providerBean = gateway.findProviderById(providerId);
            providerUserBean.setProvider(providerBean);

            BranchBean branchBean = gateway.findBranchById(branchId);
            providerUserBean.setBranch(branchBean);

            ProviderUserBean providerUserBean2 = gateway.addProviderUser(providerUserBean);
            if (providerUserBean2 != null) {
                added = true;
                //sending branch id and provider user id to show provider users with providername and branch name

                response.sendRedirect("view_provider_users.jsp?s=" + added + "&providerId=" + providerId);

            } else {
                out.println("<h1> Sorry! unable to add record </h1>");
                throw new InvalidPasswordException("Null record check may be validation not valid password length must be from 4 to 10 characters !!! ");

            }

        } catch (Exception e) {

            added = false;
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
        // processRequest(request, response);
        response.sendError(405);
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
