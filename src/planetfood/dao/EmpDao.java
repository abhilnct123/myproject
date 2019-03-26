package planetfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Emp;

/**
 *
 * @author #ar$#
 */
public class EmpDao {
     public static boolean addEmployee(Emp e)throws SQLException
    {
Connection conn=DBConnection.getConnection();
//PreparedStatement p=conn.prepareStatement("select max(empid) from employees");
//int x=p.getString(1);
PreparedStatement ps=conn.prepareStatement("insert into employees(empid,ename,job,sal) values(?,?,?,?)");
ps.setString(1, e.getEmpId());
ps.setString(2,e.getEmpName());
ps.setString(3,e.getJob());
ps.setDouble(4,e.getSalary());
    
    int count=ps.executeUpdate();
    if(count==0)
        return false;
    else
        return true;
    
}
     
      public static String getNewEmpId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       int id=101;
       ResultSet rs=st.executeQuery("Select count(*) from employees");
       if(rs.next())
       {
           id=id+rs.getInt(1);
       }
   return "E"+id;
    }
      public static ArrayList<Emp> getAllData()throws SQLException{
         Connection conn=DBConnection.getConnection();
         ArrayList<Emp> allData=new ArrayList<>();
         Statement st=conn.createStatement();
         ResultSet rs=st.executeQuery("select * from Employees" );
         Iterator it=allData.iterator();
         
            
         while(rs.next()){
            Emp e=new Emp();
            e.setEmpId(rs.getString(1));
            e.setEmpName(rs.getString(2));
            e.setJob(rs.getString(3));
            e.setSalary(rs.getInt(4));
            
             
             allData.add(e);
         }
         //allData.add(p);
         return allData;
     }
      
      public static HashMap<String,String> getAllEmp()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select * from employees" );
        
        HashMap <String,String> employees=new HashMap<>();
        while(rs.next()){
            String empName=rs.getString(2);
            String empId=rs.getString(1);
            String empJob=rs.getString(3);
            String empSal=rs.getString(4);
            employees.put(empId,empName);
            
            
        }
        
        return employees;
        
    }
      
     
      public static HashMap<String,Emp> getEmployeeById(String empId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        String qry="Select * from employees where empid=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap< String,Emp> empList=new HashMap<>();
        ps.setString(1,empId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
            Emp e=new Emp();
            e.setEmpId(empId);
            //e.setProdId(rs.getString("prod_id"));
            e.setEmpName(rs.getString("ename"));
            e.setSalary(rs.getInt("sal"));
            e.setJob(rs.getString("job"));
            empList.put(e.getEmpId(),e);
        }
        return empList;
    }
      
      public static HashMap<Emp,String> getEmpById(String empId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        String qry="Select * from employees where empid=?";
        PreparedStatement ps=conn.prepareStatement(qry);
        HashMap< Emp,String> empList=new HashMap<>();
        ps.setString(1,empId);
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
            Emp e=new Emp();
            e.setEmpId(empId);
            //e.setProdId(rs.getString("prod_id"));
            e.setEmpName(rs.getString("ename"));
            e.setSalary(rs.getInt("sal"));
            e.setJob(rs.getString("job"));
            empList.put(e,e.getEmpId());
        }
        return empList;
    }
      
      public static boolean updateEmp(Emp p)throws SQLException{
         Connection conn=DBConnection.getConnection();
         PreparedStatement ps=conn.prepareStatement("update employees set ename=?,sal=?,job=? where empid=?");
         ps.setString(1,p.getEmpName());
         ps.setDouble(2,p.getSalary());
         ps.setString(3,p.getJob());
         ps.setString(4,p.getEmpId());
         int x=ps.executeUpdate();
         
         if(x>0)
             return true;
         else
             return false;
     }
   
      public static boolean removeEmp(String empId)throws SQLException{
          Connection conn=DBConnection.getConnection();
          PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
          ps.setString(1, empId);
          int x=ps.executeUpdate();
          if(x>0)
             return true;
         else
             return false;
          
          
      }
    
}