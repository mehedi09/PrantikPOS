/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posprantik.DAL;
import java.awt.Frame;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilderFactory;
import posprantik.HomeScreenJF;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
/**
 *
 * @author User
 */
public class UserDAL  {
     // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
   //  public static SqlConnection conn = new SqlConnection(DBConnection.GetConnectionString());
  //public  String url="jdbc:sqlserver://192.168.5.17:1555;databaseName=POSXCSEMI;user=sa;password=sa9";
//Connection con = DriverManager.getConnection(url);
  private Connection connect() {
      try{
          File inputFile = new File("/Personal/JAVA/PosPrantik/Config.xml");
       //File inputFile = new File("input.txt");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("credentials");
         System.out.println("----------------------------");
          for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Version : "
                       + eElement.getElementsByTagName("Version")
                               .item(0).getTextContent());
                 System.out.println("----------------------------");
               System.out.println("OutLetRegNo: " 
                       + eElement.getElementsByTagName("OutLetRegNo")
                               .item(0).getTextContent());
               System.out.println("Device_ID : "       
                       + eElement                     .getElementsByTagName("Device_ID")
                  .item(0).getTextContent());
               System.out.println("SQLIP : " 
                  + eElement                  .getElementsByTagName("SQLIP")                  
                          .item(0)                  .getTextContent());
               System.out.println("DatabaseName : " 
                  + eElement                  .getElementsByTagName("DatabaseName")
                  .item(0)                  .getTextContent());
            }}
      }
      
      catch (Exception e) {
         e.printStackTrace();
      }
      
      
//DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
//        .newInstance();
//DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
//      org.w3c.dom.Document document = null;
//      try {
//          document = documentBuilder.parse(file);
//      } catch (IOException ex) {
//          Logger.getLogger(UserDAL.class.getName()).log(Level.SEVERE, null, ex);
//      }
//String usr = document.getElementsByTagName("user").item(0).getTextContent();
//String pwd = document.getElementsByTagName("password").item(0).getTextContent();
      
        // SQLite connection string
        //String  url="jdbc:sqlserver://192.168.5.17:1555;databaseName=POSXCSEMI;user=sa;password=sa9";
        String  url="jdbc:sqlserver://192.168.5.21:1433;databaseName=POSX;user=sa;password=sa9";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
  /**
     * select all rows in the warehouses table
     */
  //  public void selectAll(){
//        String sql = "SELECT id, name, capacity FROM warehouses";
//        
//        try (Connection conn = this.connect();
//             Statement stmt  = conn.createStatement();
//             ResultSet rs    = stmt.executeQuery(sql)){
//            
//            // loop through the result set
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") +  "\t" + 
//                                   rs.getString("name") + "\t" +
//                                   rs.getDouble("capacity"));
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
 
  
   public void CheckUser(String UserName,String Password){
             //  String sql = "Select * from t_User where User_LoginID=? "
                 //      + "and User_LoginID = ?";
               
               String sql = "EXEC SP_ULogin ?,?,?,?";
//                    cmd.Parameters.Add("@pLid", SqlDbType.NVarChar).Value = _UName;
//            cmd.Parameters.Add("@pPass", SqlDbType.NVarChar).Value = _Pass;
//            cmd.Parameters.Add("@pKey", SqlDbType.NVarChar).Value = _pKey;
//            cmd.Parameters.Add("@oRg", SqlDbType.NVarChar).Value = _oRg;               

          
//String SPsql = "EXEC <sp_name> ?,?";   // for stored proc taking 2 parameters
//Connection con = SmartPoolFactory.getConnection();   // java.sql.Connection
//PreparedStatement ps = con.prepareStatement(SPsql);
//ps.setEscapeProcessing(true);
//ps.setQueryTimeout(<timeout value>);
//ps.setString(1, <param1>);
//ps.setString(2, <param2>);
//ResultSet rs = ps.executeQuery();
                  
               
               
               
               
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            // set the value
            pstmt.setString(1,UserName);
            pstmt.setString(2,Password);
            pstmt.setString(3,"700999DB-2F2F");
            pstmt.setString(4,"R009");
            //
            ResultSet rs  = pstmt.executeQuery();
            
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("emp_id") +  "\t" + 
                                   rs.getString("emp_id") + "\t");
                  HomeScreenJF HSJF= new HomeScreenJF();
         HSJF.setExtendedState(Frame.MAXIMIZED_BOTH);
         HSJF.setVisible(true); 
               
            }
             conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
//  public Datatable UserLogin_Check( _UName,  _Pass,  _pKey,  _oRg){
//    //  DataTable dt = new DataTable();
//     // public  String url="jdbc:sqlserver://192.168.5.17:1555;databaseName=POSXCSEMI;user=sa;password=sa9";
//      Connection con = DriverManager.getConnection(url);
//      
//      String sql = "Select * from EMPLOYEE_MASTER where EMP_ID=? and Salesforce_ID = ?";
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setString(1, _UName.getText());
//            pst.setString(2,_Pass.getText());
//            
//             //SqlDataAdapter adpt = new SqlDataAdapter(cmd);
//           // adpt.Fill(dt);
//            ResultSet rs = pst.executeQuery();
////            if (conn.State == 0)
////            {
////                conn.Open();
////            }
//            SqlCommand cmd = new SqlCommand("SP_ULogin", conn);
//            cmd.CommandType = CommandType.StoredProcedure;
//            cmd.Parameters.Add("@pLid", SqlDbType.NVarChar).Value = _UName;
//            cmd.Parameters.Add("@pPass", SqlDbType.NVarChar).Value = _Pass;
//            cmd.Parameters.Add("@pKey", SqlDbType.NVarChar).Value = _pKey;
//            cmd.Parameters.Add("@oRg", SqlDbType.NVarChar).Value = _oRg;
//            SqlDataAdapter adpt = new SqlDataAdapter(cmd);
//            adpt.Fill(dt);
//           
//            return dt;
//  }
}
