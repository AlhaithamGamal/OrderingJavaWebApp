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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.BranchBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otlobli.common.bean.ProviderUserBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "EditProviderUserServletOnly", urlPatterns = {"/EditProviderUserServletOnly"})
public class EditProviderUserServletOnly extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        boolean updated;
        try {
            String providerIdText = request.getParameter("providers");
            Integer providerId = Integer.parseInt(providerIdText);

            Integer providerUserId = Integer.parseInt(request.getParameter("providerUserId"));
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
            providerUserBean.setId(providerUserId);
            providerUserBean.setPassword(password);
            providerUserBean.setEncrypted_password(encrypted_password);
            providerUserBean.setUsername(userName);

            providerUserBean.setEmail(email);

            providerUserBean.setActive(active);

            ProviderBean providerBean = gateway.findProviderByIdWithoutBranchesOfProviderUsers(providerId);
            providerUserBean.setProvider(providerBean);
            providerUserBean.setBranch(null);

            ProviderUserBean providerUserBean2 = gateway.updateProviderUser(providerUserBean);
            if (providerUserBean2 != null) {
                updated = true;
                //send branchId and providerId to show provider name and branch name y using findProviderById() and findBranchById() in jsp

                response.sendRedirect("view_providerusers_only.jsp?s=" + updated + "&providers=" + providerId);

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
