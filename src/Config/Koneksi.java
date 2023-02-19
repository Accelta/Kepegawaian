/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Axel
 */
public class Koneksi {
    Connection  conek;
     public Koneksi(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conek = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kepegawaian", "root","");
//            JOptionPane.showMessageDialog(null, "terkoneksi");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"gagal terkoneksi"+ e);
        }

    }
    public Connection login() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
            conek = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_kepegawaian", "root","");
//            JOptionPane.showMessageDialog(null, "terkoneksi");
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null,"gagal terkoneksi"+ e);
        }
        return conek;
    }
}
