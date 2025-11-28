/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Koneksi;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;


public class Database {
    static Connection conn;
    public static Connection KoneksiDB(){
        if(conn == null){
            try{
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/db_apotik";
                String user = "root";
                String pass = "";
                
                Class.forName(driver).newInstance();
                System.out.println("driver kebaca");
                conn = DriverManager.getConnection(url,user,pass);
                JOptionPane.showMessageDialog(null, "Koneksi Berhasil...", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            } 
            catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Koneksi Tidak Berhasil...", "Pesan", JOptionPane.INFORMATION_MESSAGE);                
            }
        }
        return conn;
    }
}
