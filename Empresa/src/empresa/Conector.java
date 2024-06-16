/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conector {
    Connection conectar = null;

    public Connection conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Empresa", "jcaceresap", "12345");
            JOptionPane.showMessageDialog(null, "Conexión Exitosa");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error: Driver no encontrado " + e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: No se pudo conectar a la base de datos " + e.getMessage());
        }
        return conectar;
    }
}