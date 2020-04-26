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
import static java.lang.System.out;
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
import net.haitham.otlobli.common.bean.CityBean;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otloblibal.OtlobliBLGateway;

/**
 *
 * @author HaithamGamal
 */
@WebServlet(name = "SaveBranchServlet", urlPatterns = {"/SaveBranchServlet"})
public class SaveBranchServlet extends HttpServlet {
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
            
            Integer flag = Integer.parseInt(request.getParameter("flag"));
            if(flag != 0){
            
            String nameAR = request.getParameter("name_ar");
            String nameEN = request.getParameter("name_en");
            String phone = String.valueOf(request.getParameter("phone"));
            String opened = String.valueOf(request.getParameter("opened"));
            String closed = String.valueOf(request.getParameter("closed"));

            BigDecimal lat = BigDecimal.valueOf(Double.parseDouble(request.getParameter("lat")));
            BigDecimal lng = BigDecimal.valueOf(Double.parseDouble(request.getParameter("lng")));
            Integer providerId = Integer.parseInt(request.getParameter("providers"));
            Integer areaId = Integer.parseInt(request.getParameter("areas"));

//            String correct = new String(request.getParameter("nameAR").getBytes("ISO-8859-1"), "UTF-8");
            BranchBean branchBean = new BranchBean();
            branchBean.setNameAr(nameAR);
            branchBean.setNameEn(nameEN);
            branchBean.setOpenAt(opened);
            branchBean.setCloseAt(closed);
            if (phone.equals(null) || phone.equals("")) {

                branchBean.setPhone("no phone");
            } else {
                branchBean.setPhone(phone);

            }
            if (lat.equals(null)) {

                branchBean.setLat(new BigDecimal(0));
            } else {
                branchBean.setLat(lat);
            }
            if (lng.equals(null)) {

                branchBean.setLng(new BigDecimal(0));

            } else {
                branchBean.setLng(lng);
            }

            ProviderBean providerBean = gateway.findProviderById(providerId);
            AreaBean areaBean = gateway.findAreaById(areaId);
            branchBean.setProviderBean(providerBean);
            branchBean.setAreaBean(areaBean);
            branchBean = gateway.addBranch(branchBean);
            if (branchBean != null) {
                added = true;
                out.print("<p>Record saved successfully!</p>");
                response.sendRedirect("view_provider_branches.jsp?s=" + added + "&providerId=" + providerId);
                //send area id to show areas and branches in one page 

            } else {
                out.println("Sorry! unable to save record");
            }
            }
            else{
            
                 
            String nameAR = request.getParameter("name_ar");
            String nameEN = request.getParameter("name_en");
            String phone = String.valueOf(request.getParameter("phone"));
            String opened = String.valueOf(request.getParameter("opened"));
            String closed = String.valueOf(request.getParameter("closed"));

            BigDecimal lat = BigDecimal.valueOf(Double.parseDouble(request.getParameter("lat")));
            BigDecimal lng = BigDecimal.valueOf(Double.parseDouble(request.getParameter("lng")));
            Integer providerId = Integer.parseInt(request.getParameter("providers"));
            Integer areaId = Integer.parseInt(request.getParameter("areas"));

//            String correct = new String(request.getParameter("nameAR").getBytes("ISO-8859-1"), "UTF-8");
            BranchBean branchBean = new BranchBean();
            branchBean.setNameAr(nameAR);
            branchBean.setNameEn(nameEN);
            branchBean.setOpenAt(opened);
            branchBean.setCloseAt(closed);
            if (phone.equals(null) || phone.equals("")) {

                branchBean.setPhone("no phone");
            } else {
                branchBean.setPhone(phone);

            }
            if (lat.equals(null)) {

                branchBean.setLat(new BigDecimal(0));
            } else {
                branchBean.setLat(lat);
            }
            if (lng.equals(null)) {

                branchBean.setLng(new BigDecimal(0));

            } else {
                branchBean.setLng(lng);
            }

            ProviderBean providerBean = gateway.findProviderById(providerId);
            AreaBean areaBean = gateway.findAreaById(areaId);
            branchBean.setProviderBean(providerBean);
            branchBean.setAreaBean(areaBean);
            branchBean = gateway.addBranch(branchBean);
            if (branchBean != null) {
                added = true;
                out.print("<p>Record saved successfully!</p>");
                response.sendRedirect("view_area_branches.jsp?s=" + added + "&areaId=" + areaId);
                //send area id to show areas and branches in one page 

            } else {
                out.println("Sorry! unable to save record");
            }
            
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
