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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import planetfood.dbutil.DBConnection;
import planetfood.pojo.Order;
import planetfood.pojo.OrderDetail;



/**
 *
 * @author Abhinash
 */
public class OrderDao {
    public static ArrayList<Order>getOrdersByDate(Date startDate,Date endDate)throws SQLException
    {
      
        java.sql.Date d1=new java.sql.Date(startDate.getTime());
        java.sql.Date d2=new java.sql.Date(endDate.getTime());
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from Orders where ord_date between ? and ?");
         
         ps.setDate(1, d1);
         ps.setDate(2, d2);
         
         ResultSet rs=ps.executeQuery();
         ArrayList<Order>orderList=new ArrayList<>();
         
       
         while(rs.next())
         {
             
             Order obj=new Order();
             obj.setOrdId(rs.getString("ord_id"));
             java.sql.Date d=rs.getDate("ord_date");
            
             SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
             
             String dateStr=sdf.format(d);
             obj.setOrdDate(dateStr);
           //  obj.setOrdAmount(rs.getDouble("ord_amount"));
             obj.setGst(rs.getDouble("gst"));
             obj.setGstAmount(rs.getDouble("gst_amount"));
             obj.setGrandTotal(rs.getDouble("grand_total"));
             obj.setDiscount(rs.getDouble("discount"));
             obj.setUserId(rs.getString("userid"));
             orderList.add(obj);
             System.out.println("hii"+orderList);
             
         }
         return orderList;
    }
    
    public static String getNewId()throws SQLException
    {
        Connection conn=DBConnection.getConnection();
       Statement st=conn.createStatement();
       int id=101;
       ResultSet rs=st.executeQuery("Select count(*) from orders");
       if(rs.next())
       {
           id=id+rs.getInt(1);
       }
   return "OD"+id;
    }
    
    
    public static boolean addOrder(Order order,ArrayList<OrderDetail>orderList)throws SQLException,ParseException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?,?,?,?)");
        ps.setString(1,order.getOrdId());
        
        String dateStr=order.getOrdDate();
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=sdf.parse(dateStr);
        java.sql.Date d2=new java.sql.Date(d1.getTime());
        ps.setDate(2,d2);
        
        ps.setDouble(3,order.getGst());
        ps.setDouble(4,order.getGstAmount());
        ps.setDouble(5,order.getDiscount());
        ps.setDouble(6,order.getGrandTotal());
        ps.setString(7,order.getUserId());
       // ps.setDouble(8,order.getOrdAmount());
        int x=ps.executeUpdate();
        
        
        PreparedStatement ps2=conn.prepareStatement("Insert into order_details values(?,?,?,?)");
        int count=0,y;
        for(OrderDetail detail:orderList)
        {
            ps2.setString(1,detail.getOrdId());
            ps2.setString(2, detail.getProdId());
            ps2.setDouble(3,detail.getQuantity());
            ps2.setDouble(4,detail.getCost());
            y=ps2.executeUpdate();
            if(y>0)
            count=count+y;
        }
        
        if(x>0&&count==orderList.size())
            return true;
        else
            return false;
                    
              
                  
       
        
    }
     public static ArrayList<Order> getAllOrders()throws SQLException{
        Connection conn=DBConnection.getConnection();
        //System.out.println(startDate);
        PreparedStatement ps=conn.prepareStatement("select * from orders ");
        
        
        
        ArrayList<Order> orderList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Order obj=new Order();
            obj.setOrdId(rs.getString("ord_id"));
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
            java.sql.Date d=rs.getDate("Ord_date");
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setOrdAmount(rs.getDouble("ord_amount"));
            obj.setGst(rs.getDouble("gst"));
            obj.setGstAmount(rs.getDouble("gst_amount"));
            obj.setGrandTotal(rs.getDouble("grand_total"));
            obj.setDiscount(rs.getDouble("discount"));
            obj.setUserId(rs.getString("userid"));
            orderList.add(obj);
            //System.out.println("hello" +orderList);
        }
        return orderList;
    }
     public static ArrayList<Order> getOrdersByDateByUser(String ms1,String ms2 )throws SQLException, ParseException{
        Connection conn=DBConnection.getConnection();
        System.out.println(ms1);
        PreparedStatement ps=conn.prepareStatement("select * from orders where ord_date between ? and ?");
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
        java.util.Date d1=(java.util.Date) sdf.parse(ms1);
        java.util.Date d2=(java.util.Date) sdf.parse(ms2);
        System.out.println(d1);
        
        ps.setDate(1, (java.sql.Date) d1);
        ps.setDate(2, (java.sql.Date) d2);
        ArrayList<Order> orderList=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            Order obj=new Order();
            obj.setOrdId(rs.getString("ord_id"));
            java.sql.Date d=rs.getDate("Ord_date");
            String dateStr=sdf.format(d);
            obj.setOrdDate(dateStr);
            obj.setOrdAmount(rs.getDouble("ord_amount"));
            obj.setGst(rs.getDouble("gst"));
            obj.setGstAmount(rs.getDouble("gst_amount"));
            obj.setGrandTotal(rs.getDouble("grand_total"));
            obj.setDiscount(rs.getDouble("discount"));
            obj.setUserId(rs.getString("userid"));
            orderList.add(obj);
            //System.out.println("hello" +orderList);
        }
        return orderList;
    }
}