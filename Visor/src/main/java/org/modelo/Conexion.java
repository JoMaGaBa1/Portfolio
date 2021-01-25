package org.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    protected Connection conexion = null;
    private final String driver = "org.mariadb.jdbc.Driver";
    private final String url = "jdbc:mariadb://80.59.11.241:3306/jgarciabarrionuevo";
    private final String usuario = "jgarciabarrionuevo";
    private final String password = "Hl4qOceHZTssxmzd";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,usuario,password);
        } catch (SQLException e) {
            e.printStackTrace();
            return conexion;
        }
    }

    public void conectarBDD() {
        try {
            Class.forName(driver);
            conexion = getConnection();
        } catch (ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void desconectarBDD() {
        try {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
