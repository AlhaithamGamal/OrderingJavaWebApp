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
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet(name = "EditBranchServlet", urlPatterns = {"/EditBranchServlet"})
public class EditBranchServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

        try {
            String flag = request.getParameter("flag");

            if (!flag.equals("true")) {
                Integer areaId = Integer.parseInt(String.valueOf(request.getParameter("areas")));
                Integer providerId = Integer.parseInt(String.valueOf(request.getParameter("providers")));
                boolean updated = false;
                /* TODO output your page here. You may use following sample code. */

                String nameAR = request.getParameter("name_ar");
                String nameEN = request.getParameter("name_en");
                String phone = String.valueOf(request.getParameter("phone"));
                String opened = String.valueOf(request.getParameter("opened"));
                String closed = String.valueOf(request.getParameter("closed"));
                BigDecimal lat = new BigDecimal(request.getParameter("lat"));
                BigDecimal lng = new BigDecimal(request.getParameter("lng"));
                Integer branchId = Integer.parseInt(request.getParameter("id"));

                BranchBean branchBean = new BranchBean();
                branchBean.setNameAr(nameAR);
                branchBean.setNameEn(nameEN);
                branchBean.setOpenAt(opened);
                branchBean.setCloseAt(closed);
                branchBean.setId(branchId);
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
                BranchBean branchBean2 = gateway.updateBranch(branchBean);
                if (branchBean2 != null) {
                    updated = true;
                    response.sendRedirect("view_area_branches.jsp?s2=" + updated + "&areaId=" + areaId);
                } else {
                    out.println("Sorry! unable to update record");
                }

            } else {
                boolean updated = false;
                /* TODO output your page here. You may use following sample code. */

                String nameAR = request.getParameter("name_ar");
                String nameEN = request.getParameter("name_en");
                String phone = String.valueOf(request.getParameter("phone"));
                String opened = String.valueOf(request.getParameter("opened"));
                String closed = String.valueOf(request.getParameter("closed"));
                BigDecimal lat = new BigDecimal(request.getParameter("lat"));
                BigDecimal lng = new BigDecimal(request.getParameter("lng"));
                Integer branchId = Integer.parseInt(request.getParameter("id"));
                Integer providerId = Integer.parseInt(request.getParameter("providers"));
                Integer areaId = Integer.parseInt(request.getParameter("areas"));

                BranchBean branchBean = new BranchBean();
                branchBean.setNameAr(nameAR);
                branchBean.setNameEn(nameEN);
                branchBean.setOpenAt(opened);
                branchBean.setCloseAt(closed);
                branchBean.setId(branchId);
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
                BranchBean branchBean2 = gateway.updateBranch(branchBean);
                if (branchBean2 != null) {
                    updated = true;
                    response.sendRedirect("view_provider_branches.jsp?s2=" + updated + "&providerId=" + providerId);
                } else {
                    out.println("Sorry! unable to update record");
                }
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
      //  processRequest(request, response);
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
