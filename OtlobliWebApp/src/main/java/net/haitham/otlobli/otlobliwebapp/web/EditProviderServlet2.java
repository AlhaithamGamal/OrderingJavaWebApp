/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otloblibal.OtlobliBLGateway;

/**
 *
 * @author HaithamGamal
 */
 @MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class EditProviderServlet2 extends HttpServlet {

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
        try  {
            boolean updated = false;
            /* TODO output your page here. You may use following sample code. */
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);
            String nameAR = request.getParameter("name_ar_provider");
            String nameEN = request.getParameter("name_en_provider");
            String hotline = request.getParameter("hotline");
            String logo = request.getParameter("logo");
            
            ProviderBean providerBean = new ProviderBean();
            providerBean.setId(Integer.parseInt(sid));
            providerBean.setNameAr(nameAR);
            providerBean.setNameEn(nameEN);
           // Part part = request.getPart("logo");
//            if (part  != null) {
//
//                String fileName = extractFileName(part);
//                String savePath = "C:\\haitham\\JavaEE\\Java Web SeniorSteps\\PracticeByMe\\Otlobli Practice\\OtlobliWebApp\\src\\main\\webapp\\images" + File.separator + fileName;
//                File fileSaveDir = new File(savePath);
//                part.write(savePath + File.separator);
//                providerBean.setLogoPathEn(fileName);
//            }
//            else{
//            
//            providerBean.setLogoPathEn(null);
//            }

            if (!"".equals(hotline)) {
                try {

                    providerBean.setHotline(hotline);

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {

                providerBean.setHotline("no hotline !");

            }

            ProviderBean p = gateway.updateProvider(providerBean);
            if (p != null) {
                updated = true;
                response.sendRedirect("providers.jsp?s2=" + updated);
            } else {
                out.println("Sorry! unable to update record");
            }
        }
           catch (Exception e) {
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

    private String extractFileName(Part part) {

        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }

        }
        return "";

    }
}
