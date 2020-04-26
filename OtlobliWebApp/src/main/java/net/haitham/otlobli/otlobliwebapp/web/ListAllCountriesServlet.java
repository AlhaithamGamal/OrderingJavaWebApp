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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import net.haitham.otloblidal.entity.annotation.Country;

/**
 *
 * @author HaithamGamal
 */
public class ListAllCountriesServlet extends HttpServlet {
    ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");
private int count=0;
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
       
        String message="";
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
             List<CountryBean> countries = gateway.findCountries();
             String status = request.getParameter("s");
             if(status != null){ //check if the status not null in case of k=just refreshing the page without adding 
             if("true".equals(status)){ //true first to avoid null pointer exception
                 
                 message="Added Successfully";
             }
             else{
             message="Error in adding";
             }
             }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListAllCountriesServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(message);
//            out.println("<h1>Servlet ListAllCountriesServlet at " + request.getContextPath() + "</h1>");
            out.println("<a href='add_country.html'>Add New Country</a>");  
        out.println("<h1>Countries List</h1>");  
                    
        out.print("<table border='1' width='100%'");  
        out.print("<tr><th>Id</th><th>Country Arabic Name</th><th>Country English Name</th> ");
        out.print("<th>Edit</th><th>Delete</th></tr>");  
        for(CountryBean countryBean:countries){  
         out.print("<tr><td>"+countryBean.getId()+"</td><td>"+countryBean.getNameAr()+"</td><td>"+countryBean.getNameEn()+"</td><td><a href='EditCountryServlet?id="+countryBean.getId()+"'>edit</a></td>  \n" +
"                 <td><a href='DeleteCountryServlet?id="+countryBean.getId()+"'>delete</a></td></tr>");
        }  
        out.print("</table>");  
            out.println("</body>");
            out.println("</html>");
        }
        count++;
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
       // processRequest(request, response);
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
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
