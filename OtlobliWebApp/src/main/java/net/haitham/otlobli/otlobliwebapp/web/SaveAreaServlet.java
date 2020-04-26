/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.AreaBean;
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "SaveAreaServlet", urlPatterns = {"/SaveAreaServlet"})
public class SaveAreaServlet extends HttpServlet {
//    private OtlobliBLGateway gateway = new OtlobliBLGateway();
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

            String nameAR = request.getParameter("name_ar");
            String nameEN = request.getParameter("name_en");
            Integer cityId = Integer.parseInt(request.getParameter("cities"));
//            String correct = new String(request.getParameter("nameAR").getBytes("ISO-8859-1"), "UTF-8");


            AreaBean areaBean = new AreaBean();
            areaBean.setNameAr(nameAR);
            areaBean.setNameEn(nameEN);
            CityBean cityBean = gateway.findCityById(cityId);
            areaBean.setCityBean(cityBean);

             areaBean = gateway.addArea(areaBean);
            if (areaBean != null) {
                added = true;
                out.print("<p>Record saved successfully!</p>");
                response.sendRedirect("view_city.jsp?s=" + added+"&cityId="+cityId);

            } else {
                out.println("Sorry! unable to save record");
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
      //  processRequest(request, response);
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
