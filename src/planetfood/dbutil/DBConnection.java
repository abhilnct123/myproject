/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetfood.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ABHINASH
 */
public class DBConnection {
     private static Connection conn;
        static{
       
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            
        
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-G7Q70BR:1521/XE","myproject","abhi");
                JOptionPane.showMessageDialog(null,"Connected Successfully to the DB","Success!",JOptionPane.INFORMATION_MESSAGE);
        }

    catch(ClassNotFoundException ex)
    {
        JOptionPane.showMessageDialog(null,"Error in loading DriverClass"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    }
    catch(SQLException ex)
    {
         JOptionPane.showMessageDialog(null,"SQL Exception"+ex,"Error!",JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        
    }
    }
    public static Connection getConnection(){
        return conn;
    }
        
    
    
    
}
