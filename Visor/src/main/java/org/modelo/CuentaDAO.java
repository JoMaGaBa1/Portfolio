package org.modelo;

import java.sql.Connection;
import java.util.List;

public interface CuentaDAO {
    public Connection getConexion();
    public int sacaUltimoId();
    public Cuenta consultarCuenta(int id);
    public List<Cuenta> listarCuentas();
    public int insertarCuenta(Cuenta cuenta);
}
