/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.util.stream.Collectors;
import org.json.JSONObject;
import java.sql.*;
import java.sql.Connection;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author yohan
 */
@WebServlet(urlPatterns = {"/read_form_data"})
public class read_form_data extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        //read json data sent by the front end including the form data
        String incomingJson = req.getReader().lines().collect(Collectors.joining());        
        JSONObject obj = new JSONObject(incomingJson);

        String name = obj.getString("name");
        String email = obj.getString("email");
        int age = Integer.parseInt(obj.getString("age"));
        String dob = obj.getString("dob");
        String year = obj.getString("year");
        String stream = obj.getString("stream");
        
        
        
        //database integration
        try (Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_register","root","");){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement stmt =con.prepareStatement("insert into student (name, email, age, date_of_birth, stream, enrolled_at) values(?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, age);
            stmt.setString(4, dob);
            stmt.setString(5, stream);
            stmt.setString(6, year);
            int row = stmt.executeUpdate();
            
            //sending a response as a json
            JSONObject responseJson = new JSONObject();
            
            if(row>0){
               responseJson.put("message", "data inserted successfully");
               out.print(responseJson.toString());
               out.flush();
            }
            else{
               responseJson.put("message" ,"there is no data to insert ");
               out.print(responseJson.toString());
               out.flush();
            }
            
            
//            int row = stmt.executeQuery();
        } catch (Exception ex) {
//            System.getLogger(read_form_data.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             out.print("{\"error\": oops}");
        }
        
        
    }
    @Override
     protected void doPut(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        //read json data sent by the front end including the form data
        String incomingJson = req.getReader().lines().collect(Collectors.joining());        
        JSONObject obj = new JSONObject(incomingJson);

        String name = obj.getString("name");
        String email = obj.getString("email");
        int age = Integer.parseInt(obj.getString("age"));
        String dob = obj.getString("dob");
        String year = obj.getString("year");
        String stream = obj.getString("stream");
        
        
        
        //database integration
        try (Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_register","root","");){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement stmt =con.prepareStatement("update student set name=?, email=?, age=?, date_of_birth=?, stream=?, enrolled_at=? where email=?");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setInt(3, age);
            stmt.setString(4, dob);
            stmt.setString(5, stream);
            stmt.setString(6, year);
            stmt.setString(7, email);
            int row = stmt.executeUpdate();
            
            //sending a response as a json
            JSONObject responseJson = new JSONObject();
            
            if(row>0){
               responseJson.put("message", "data update successfully");
               out.print(responseJson.toString());
               out.flush();
            }
            else{
               responseJson.put("message" ,"there is no data to update ");
               out.print(responseJson.toString());
               out.flush();
            }
            
            
//            int row = stmt.executeQuery();
        } catch (Exception ex) {
//            System.getLogger(read_form_data.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             out.print("{\"error\": oops}");
        }
        
        
    }
     @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        


        String email = req.getParameter("email");
        
        
        
        //database integration
        try (Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_register","root","");){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement stmt =con.prepareStatement("select *from student where email=?");
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            
            //sending a response as a json
            JSONObject responseJson = new JSONObject();
            
            if(rs.next()){
               responseJson.put("message", "data retrived successfully");
               out.print(responseJson.toString());
               out.flush();
            }
            else{
               responseJson.put("message" ,"there is no data to retrived ");
               out.print(responseJson.toString());
               out.flush();
            }
            
            
//            int row = stmt.executeQuery();
        } catch (Exception ex) {
//            System.getLogger(read_form_data.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
             out.print("{\"error\": oops}");
        }
        
        
    }
      @Override
       protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        PrintWriter out = res.getWriter();
        
        //read json data sent by the front end including the form data
        String incomingJson = req.getReader().lines().collect(Collectors.joining());        
        JSONObject obj = new JSONObject(incomingJson);

         String email = obj.getString("email");
        
        
        
        //database integration
        try (Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/uni_register","root","");){
            Class.forName("com.mysql.cj.jdbc.Driver");
            PreparedStatement stmt =con.prepareStatement("delete from student where email= ?");
            stmt.setString(1, email);
            int row = stmt.executeUpdate();
            
            //sending a response as a json
            JSONObject responseJson = new JSONObject();
            
            if(row>0){
               responseJson.put("message", "data deleted successfully");
               out.print(responseJson.toString());
               out.flush();
            }
            else{
               responseJson.put("message" ,"there is no data to deleted ");
               out.print(responseJson.toString());
               out.flush();
            }
            
            
//            int row = stmt.executeQuery();
        } catch (Exception ex) {
//            System.getLogger(read_form_data.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            out.print("{\"error\": oops}");
        }
        
        
    }
}
