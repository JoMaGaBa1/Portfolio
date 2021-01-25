package org.modelo;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MariaDBCuentaDAO extends Conexion implements CuentaDAO {
    private Statement sentencia;
    private PreparedStatement preparada;
    private ResultSet resultados;

    @Override
    public Connection getConexion() {
        return getConnection();
    }

    @Override
    public int sacaUltimoId() {
        List<Cuenta> lista;
        lista = listarCuentas();
        return lista.get(lista.size() - 1).getNumero();
    }

    @Override
    public List<Cuenta> listarCuentas() {
        String sql = "SELECT * FROM cuentas";
        List<Cuenta> lista = new ArrayList<>();
        try {
            conectarBDD();
            sentencia = conexion.createStatement();
            resultados = sentencia.executeQuery(sql);
            while(resultados.next()) {
                lista.add(new CuentaImpl(resultados.getInt(1),
                        resultados.getString(2),
                        resultados.getString(3),
                        new SimpleDateFormat("dd/MM/yyyy").format(resultados.getDate(4)),
                        resultados.getFloat(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo fallo con la base de datos...");
        } finally {
            desconectarBDD();
        }
        return lista;
    }

    @Override
    public Cuenta consultarCuenta(int id) {
        String sql = "SELECT * FROM cuentas WHERE numero = ?";
        Cuenta cuenta = null;
        try {
            conectarBDD();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1,id);
            resultados = preparada.executeQuery();
            if(resultados.next()) {
                cuenta = new CuentaImpl(resultados.getInt(1),
                        resultados.getString(2),
                        resultados.getString(3),
                        new SimpleDateFormat("dd/MM/yyyy").format(resultados.getDate(4)),
                        resultados.getFloat(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo fallo con la base de datos...");
        } finally {
            desconectarBDD();
        }
        return cuenta;
    }

    @Override
    public int insertarCuenta(Cuenta cuenta) {
        int insertadas = 0;
        String sql = "INSERT INTO cuentas VALUES (?,?,?,?,?)";
        Cuenta cuentaInsertada;
        try {
            conectarBDD();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1,cuenta.getNumero());
            preparada.setString(2,cuenta.getTitular());
            preparada.setString(3,cuenta.getNacionalidad());
            preparada.setString(4,cuenta.getFecha(0));
            preparada.setFloat(5,cuenta.getSaldo());
            if(preparada.executeUpdate() < 1) {
                throw new SQLException();
            } else {
                cuentaInsertada = consultarCuenta(cuenta.getNumero());
                if (!cuentaInsertada.equals(cuenta)) {
                    throw new SQLException();
                } else {
                    insertadas++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo fallo con la base de datos...");
        } finally {
            desconectarBDD();
        }
        return insertadas;
    }
}
