package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    protected Connection conexion = null;
    private final String driver = "org.mariadb.jdbc.Driver";
    private final String url = "jdbc:mariadb://80.59.11.241:3306/jgarciabarrionuevo";
    private final String usuario = "jgarciabarrionuevo";
    private final String password = "Hl4qOceHZTssxmzd";

    public Connection conectarBdd() {
        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(url,usuario,password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            return conexion;
        }
    }

    public boolean desconectarBdd() {
        try {
            if (!conexion.isClosed()) {
                conexion.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return false;
        }
    }
}
