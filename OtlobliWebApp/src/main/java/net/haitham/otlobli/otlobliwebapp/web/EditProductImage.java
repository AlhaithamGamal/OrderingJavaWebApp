/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.haitham.otlobli.otlobliwebapp.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import net.haitham.otlobli.common.bean.CategoryBean;
import net.haitham.otlobli.common.bean.ProductBean;
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
@WebServlet(name = "EditProductImage", urlPatterns = {"/EditProductImage"})
public class EditProductImage extends HttpServlet {

    ApplicationContext appCon = new ClassPathXmlApplicationContext("AContext.xml");
    OtlobliBLGateway gateway = (OtlobliBLGateway) appCon.getBean("otlobliBLGateway");

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
        ProductBean productBean = new ProductBean();

        PrintWriter out = response.getWriter();
        try {
            boolean updated = false;
            /* TODO output your page here. You may use following sample code. */
            String sid = request.getParameter("id");
            int id = Integer.parseInt(sid);
            String nameAR = request.getParameter("name_ar_product");
            String nameEN = request.getParameter("name_en_product");
            String descAR = request.getParameter("name_ar_desc_product");
            String descEN = request.getParameter("name_en_desc_product");
            String status = request.getParameter("status");
            Float price = Float.parseFloat(request.getParameter("price"));
            Integer categories = Integer.parseInt(request.getParameter("categories"));
            Integer providerId = Integer.parseInt(request.getParameter("providerId"));
            CategoryBean categoryBean = gateway.findCategoryById(categories);
            String logo = request.getParameter("logo");
            Part part = request.getPart("logo");
            if (status.toLowerCase().equals("yes")) {
                Byte active = 1;

                productBean.setActive(active);
            } else {
                Byte active = 0;
                productBean.setActive(active);
            }

            if (part != null) {

                String fileName = extractFileName(part);
                String savePath = "C:\\haitham\\JavaEE\\Java Web SeniorSteps\\PracticeByMe\\Otlobli Practice\\OtlobliWebApp\\src\\main\\webapp\\images" + File.separator + fileName;
                File fileSaveDir = new File(savePath);
                part.write(savePath + File.separator);
                productBean.setImagePath(fileName);
            } else {

                productBean.setImagePath(null);
            }
            productBean.setNameAr(nameAR);
            productBean.setNameEn(nameEN);
            productBean.setId(id);
            productBean.setDescriptionAr(descAR);
            productBean.setDescriptionEn(descEN);
            productBean.setPrice(price);
            productBean.setCategoryBean(categoryBean);

            ProductBean p = gateway.updateProductImage(productBean);
            if (p != null) {
                updated = true;
                response.sendRedirect("view_products.jsp?s2=" + updated + "&categoryId=" + categories + "&providerId=" + providerId);
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
