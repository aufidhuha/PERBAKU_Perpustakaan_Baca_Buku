/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latihanKu;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class koneksi {
    
    static Connection cnVar;
    
    public static Connection getKoneksi(){
        
        try {
            String url = "jdbc:mysql://localhost:3306/perbaku";
            String user = "root";
            String pass = "";

            cnVar = DriverManager.getConnection(url, user, pass);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        } catch (SQLException sQLException) {
            JOptionPane.showMessageDialog(null, "Gagal Terkoneksi : " + sQLException.getMessage());
        }
        return cnVar;
    }
    
}
