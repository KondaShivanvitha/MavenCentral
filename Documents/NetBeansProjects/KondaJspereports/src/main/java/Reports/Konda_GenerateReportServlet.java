/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author s525731
 */
@WebServlet(name = "Konda_GenerateReportServlet", urlPatterns = {"/Konda_GenerateReportServlet"})
public class Konda_GenerateReportServlet extends HttpServlet {
     

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws net.sf.jasperreports.engine.JRException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, JRException {
        String reportPath = "C:\\Users\\s525731\\Documents\\NetBeansProjects\\KondaJspereports\\src\\main\\java\\Reports\\MyFirstReport.jrxml";
    Connection con = DriverManager.getConnection ("jdbc:derby://localhost:1527/sample", "app", "app");
JasperReport report = JasperCompileManager. compileReport (reportPath);
byte [] byteStream = JasperRunManager.runReportToPdf (report, null, con);

OutputStream outStream = response.getOutputStream();
response.setHeader ("Content-Disposition","inline; filename=\"MyFirstReport.pdf\"");
response.setContentType ("application/pdf");
response.setContentLength (byteStream.length);
outStream.write (byteStream, 0, byteStream.length);
        
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Konda_GenerateReportServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Konda_GenerateReportServlet.class.getName()).log(Level.SEVERE, null, ex);
}
    }
}
