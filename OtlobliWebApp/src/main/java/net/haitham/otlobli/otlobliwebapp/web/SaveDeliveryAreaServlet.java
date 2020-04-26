/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import net.haitham.otloblibal.OtlobliBLGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "SaveDeliveryAreaServlet", urlPatterns = {"/SaveDeliveryAreaServlet"})
public class SaveDeliveryAreaServlet extends HttpServlet {

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
           Integer areaId = Integer.parseInt(String.valueOf(request.getParameter("areas")));
            Integer branchId = Integer.parseInt(String.valueOf(request.getParameter("branches")));

         
            Float deliveryFees = Float.parseFloat(request.getParameter("fees"));
            Integer deliveryMinutes = Integer.parseInt(request.getParameter("minutes"));

            DeliveryAreaBean deliveryAreaBean = new DeliveryAreaBean();
            deliveryAreaBean.setDeliverInMinutes(deliveryMinutes);
            deliveryAreaBean.setDeliveryFees(deliveryFees);
            BranchBean branchBean = gateway.findBranchById(branchId);
            AreaBean areaBean = gateway.findAreaById(areaId);
            deliveryAreaBean.setBranchBean(branchBean);
            deliveryAreaBean.setAreaBean(areaBean);
            DeliveryAreaBean deliveryAreaBean2 = gateway.addDeliveryArea(deliveryAreaBean);
            if (deliveryAreaBean2 != null) {
               added = true;
                response.sendRedirect("view_delivery_area.jsp?s2=" + added + "&branchId=" + branchId);
            } else {
                out.println("Sorry! unable to update record");
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
         response.sendError(405);
        //processRequest(request, response);
       
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
