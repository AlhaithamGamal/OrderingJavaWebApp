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
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
public class SaveCountryServlet extends HttpServlet implements Filter {

   // private OtlobliBLGateway gateway = new OtlobliBLGateway();
      ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");
//        System.out.println("count:"+studentManagerTwo.getStudentRepo().getCount());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();
        boolean added = false;
        try  {
            /* TODO output your page here. You may use following sample code. */

            String nameAR = request.getParameter("nameAR");
            String nameEN = request.getParameter("nameEN");
//            String correct = new String(request.getParameter("nameAR").getBytes("ISO-8859-1"), "UTF-8");

            CountryBean countryBean = new CountryBean();
            countryBean.setNameAr(nameAR);
            countryBean.setNameEn(nameEN);

            CountryBean cBean = gateway.addCountry(countryBean);
            if (cBean != null) {
                added = true;
                out.print("<p>Record saved successfully!</p>");
                response.sendRedirect("countries.jsp?s=" + added);

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
        //processRequest(request, response);
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
        //in case of entering page url

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//         request.setCharacterEncoding("UTF-8");
//        chain.doFilter(request, response);
    }

}
