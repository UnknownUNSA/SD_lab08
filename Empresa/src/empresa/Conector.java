/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conector {
    Connection conectar = null;
    
    public Connection conexion()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            JOptionPane.showMessageDialog(null, "Conexion Exitosa");
            
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }
        return conectar;
   
    }
}
