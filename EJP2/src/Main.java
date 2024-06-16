import java.sql.*;

public class Main {
    // Configuración de la conexión JDBC
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Empresa";
    private static final String JDBC_USER = "jcaceresap";
    private static final String JDBC_PASSWORD = "12345";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Insertar un nuevo departamento
            // insertarDepartamento(conn, "Nuevo Departamento 2", "123456731", "77654321");

            // Actualizar un proyecto existente
            actualizarProyecto(conn, 2, "Nuevo Nombre de Proyecto");

            // Eliminar un ingeniero por ID
            // eliminarIngeniero(conn, 1);

            // Consultar todos los proyectos de un departamento específico
            // consultarProyectosPorDepartamento(conn, "Arequipa");

            // Consultar todos los ingenieros que participan en un proyecto específico
            // consultarIngenierosPorProyecto(conn, "Proyecto JAKA");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo departamento en la tabla Departamentos
    private static void insertarDepartamento(Connection conn, String nombre, String telefono, String fax)
            throws SQLException {
        String sql = "INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, fax);
            stmt.executeUpdate();
            System.out.println("Departamento insertado correctamente.");
        }
    }

    // Método para actualizar el nombre de un proyecto en la tabla Proyectos
    private static void actualizarProyecto(Connection conn, int idProyecto, String nuevoNombre) throws SQLException {
        String sql = "UPDATE Proyectos SET Nombre = ? WHERE IDProy = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoNombre);
            stmt.setInt(2, idProyecto);
            stmt.executeUpdate();
            System.out.println("Proyecto actualizado correctamente.");
        }
    }

    // Método para eliminar un ingeniero por ID de la tabla Ingenieros
    private static void eliminarIngeniero(Connection conn, int idIngeniero) throws SQLException {
        String sql = "DELETE FROM Ingenieros WHERE IDIng = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idIngeniero);
            stmt.executeUpdate();
            System.out.println("Ingeniero eliminado correctamente.");
        }
    }

    // Método para consultar todos los proyectos de un departamento específico
    private static void consultarProyectosPorDepartamento(Connection conn, String nombreDepartamento)
            throws SQLException {
        String sql = "SELECT * FROM Proyectos WHERE IDProy IN (SELECT IDProy FROM Departamentos_Proyectos WHERE IDDpto = (SELECT IDDpto FROM Departamentos WHERE Nombre = ?))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreDepartamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("IDProy: " + rs.getInt("IDProy") + ", Nombre: " + rs.getString("Nombre")
                        + ", Fec_Inicio: " + rs.getDate("Fec_Inicio") + ", Fec_Termino: " + rs.getDate("Fec_Termino"));
            }
        }
    }

    // Método para consultar todos los ingenieros que participan en un proyecto
    // específico
    private static void consultarIngenierosPorProyecto(Connection conn, String nombreProyecto) throws SQLException {
        String sql = "SELECT * FROM Ingenieros WHERE IDIng IN (SELECT IDIng FROM Proyectos_Ingenieros WHERE IDProy = (SELECT IDProy FROM Proyectos WHERE Nombre = ?))";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombreProyecto);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("IDIng: " + rs.getInt("IDIng") + ", Especialidad: " + rs.getString("Especialidad")
                        + ", Cargo: " + rs.getString("Cargo"));
            }
        }
    }
}
