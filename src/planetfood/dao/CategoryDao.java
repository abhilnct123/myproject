/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Category;
/**
 *
 * @author ABHINASH
 */
public class CategoryDao {
public static HashMap<String,String>getAllCategoryId()throws SQLException
{
        
Connection conn=DBConnection.getConnection();
    Statement st=conn.createStatement();
     ResultSet rs=st.executeQuery("select cat_name,cat_id from categories");
HashMap<String,String> categories=new HashMap<>();
while(rs.next())
{
   String catName=rs.getString(1);
   String catId=rs.getString(2);
   categories.put(catName,catId);
}
return categories;
}
public static ArrayList<Category>getAllData()throws SQLException
    {
     Connection conn=DBConnection.getConnection();
     
        Statement st=conn.createStatement();
        ArrayList<Category>allCategories=new ArrayList<>();
     
        ResultSet rs=st.executeQuery("Select * from Categories");
        while(rs.next())
        {
            Category c=new Category();
            c.setCatId(rs.getString(1));
            c.setCatName(rs.getString(2));
         allCategories.add(c);
            
           
        }
        return allCategories;
    }
 
 public static boolean EditCategory(Category c)throws SQLException{
     
 Connection conn=DBConnection.getConnection();
 PreparedStatement ps=conn.prepareStatement("update categories set cat_name=? where cat_id=?");
 ps.setString(2,c.getCatId());
 ps.setString(1,c.getCatName());
 ResultSet rs=ps.executeQuery();
 int count=ps.executeUpdate();
 if(count==0)
     return false;
 else
     return true;
 } 
 
 public static String getNewId()throws SQLException {
     Connection conn=DBConnection.getConnection();
     Statement st=conn.createStatement();
     int id=101;
     ResultSet rs=st.executeQuery("select count(*) from categories");
     if(rs.next()){
         id=id+rs.getInt(1);
         
     }
     return "C"+id;
 }
 
 public static boolean addCategory(Category c)throws SQLException{
     Connection conn=DBConnection.getConnection();
     PreparedStatement ps=conn.prepareStatement("insert into categories values(?,?)");
     
     ps.setString(1,c.getCatId());
     ps.setString(2,c.getCatName());
     
     int count=ps.executeUpdate();
     if(count==0)
         return false;
     else
         return true;
 }

    
}

