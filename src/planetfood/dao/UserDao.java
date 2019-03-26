package planetfood.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import static oracle.net.aso.C11.s;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ABHINASH
 */
public class UserDao {
    public static String userr;
    static String s;
    public static String validateUser(User user)throws SQLException
    {
        Connection conn=DBConnection.getConnection();
       String qry="Select username from Users where userid=? and password=? and usertype=?";
       PreparedStatement ps=conn.prepareStatement(qry);
       ps.setString(1,user.getUserId());
       ps.setString(2,user.getPassword());
       ps.setString(3,user.getUserType());
        System.out.println(user.getPassword());
       ResultSet rs=ps.executeQuery();
       System.out.println(user.getUserId());
        System.out.println(user.getUserType());
        //System.out.println(rs.next());
       String username=null;
       if(rs.next())
       {
           username=rs.getString(1);
           userr=username;
       }
       return username;
       
       
       
    }

   
public static String showUsername()
    {
       Connection conn=DBConnection.getConnection();
       return userr; 
      // PreparedStatement ps=conn.prepareStatement("Select ename from employees where userid=?");
       //ps.setString(1,User.);
       
    }
     public static HashMap<User,String> getUserById(String userId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select * from users where userid=?");
         HashMap< User,String> userList=new HashMap<>();
        ps.setString(1, userId);
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            User u=new User();
            u.setUserId(userId);
            u.setUserName(rs.getString("username"));
            u.setEmpId(rs.getString("empid"));
            
            userList.put(u,u.getEmpId());
        }
        
        return userList;
    }
    
    public static boolean addUser(User u) throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?) ");
        ps.setString(1,u.getUserId());
        PreparedStatement ps1=conn.prepareStatement("select ename from employees where empid=?");
        ps1.setString(1,u.getEmpId());
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
        {
           s=rs.getString(1);
            System.out.println(s);
        }
        
        ps.setString(2,s);
        ps.setString(3,u.getPassword());
        ps.setString(4,u.getEmpId());
        ps.setString(5,u.getUserType());
        int count=ps.executeUpdate();
        if(count==0)
        return false;
        else
            return true;
        
    }
    
    public static boolean deleteUser(String userId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("delete from users where userid=?");
        ps.setString(1, userId);
          int x=ps.executeUpdate();
          if(x>0)
             return true;
         else
             return false;
    }
                
     
}

