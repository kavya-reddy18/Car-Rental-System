/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author karthik
 */
public class deletingcardetails extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
        String em=request.getParameter("fname");
        String pwd=request.getParameter("lname");
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","reddy");
            PreparedStatement ps=conn.prepareStatement("select * from availablecars where carname=? and carnum=?");
            ps.setString(1,em);
            ps.setString(2,pwd);
            ResultSet rs=ps.executeQuery();
            if (rs.next())
                   {
                      
                        PreparedStatement ps1=conn.prepareStatement("delete from availablecars where carname=? and carnum=?");
                        ps1.setString(1,em);
                        ps1.setString(2,pwd);
                        ps1.executeUpdate();
                        RequestDispatcher rd=request.getRequestDispatcher("onsuccess.html");  
                        rd.forward(request,response);
                    }
           
                    
            else{
               
               out.print("Incorrect carname/carnumber please check");  
                
               RequestDispatcher rd=request.getRequestDispatcher("delcars.html");  
               rd.include(request,response);  
            }
            
            
            
            
    }catch(ClassNotFoundException | SQLException cs){
                
            }
    } 
    
    catch(Exception e){
    
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
