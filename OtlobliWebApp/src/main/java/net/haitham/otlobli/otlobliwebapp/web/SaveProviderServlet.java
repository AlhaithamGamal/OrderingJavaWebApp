/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import com.oreilly.servlet.MultipartRequest;
import java.io.File;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.core.MediaType;
import net.haitham.otlobli.common.bean.CountryBean;
import net.haitham.otlobli.common.bean.ProviderBean;
import net.haitham.otloblibal.OtlobliBLGateway;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author HaithamGamal
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)

public class SaveProviderServlet extends HttpServlet implements Filter {

   ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
OtlobliBLGateway gateway =(OtlobliBLGateway)appCon.getBean("otlobliBLGateway");
    ProviderBean providerBean = new ProviderBean();

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
        response.setContentType(MediaType.TEXT_HTML);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = response.getWriter();

        boolean added = false;
        try {
            /* TODO output your page here. You may use following sample code. */

            /* TODO output your page here. You may use following sample code. */
            String nameAR = request.getParameter("name_ar_provider");
            String nameEN = request.getParameter("name_en_provider");
            String hotline = String.valueOf(Integer.parseInt(request.getParameter("hotline")));
          //  Part part = request.getPart("logo");
//            if (part != null) {
//
//                String fileName = extractFileName(part);
//                String savePath = "C:\\haitham\\JavaEE\\Java Web SeniorSteps\\PracticeByMe\\Otlobli Practice\\OtlobliWebApp\\src\\main\\webapp\\images" + File.separator + fileName;
//                File fileSaveDir = new File(savePath);
//                part.write(savePath + File.separator);
//                providerBean.setLogoPathEn(fileName);
//            }
            if (hotline != "") {
                try {

                    providerBean.setHotline(hotline);

                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {

                providerBean.setHotline("no hotline !");

            }

            providerBean.setNameAr(nameAR);
            providerBean.setNameEn(nameEN);

            ProviderBean pBean = gateway.addProvider(providerBean);
            if (pBean != null) {
                added = true;
                out.print("<p>Record saved successfully!</p>");
                response.sendRedirect("providers.jsp?s=" + added);

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
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    //file name of the upload file is included in content disposition header like this
    //form-data='';name='';filename='';
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
