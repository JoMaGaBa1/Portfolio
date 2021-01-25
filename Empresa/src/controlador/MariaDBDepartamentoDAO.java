package controlador;

import modelo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MariaDBDepartamentoDAO extends Conexion implements DepartamentoDAO {
    private Statement sentencia;
    private PreparedStatement preparada;
    private ResultSet resultados;

    @Override
    public int sacaUltimoId() {
        List<Departamento> lista = listarDepartamentos();
        return lista.get(lista.size() - 1).getId();
    }

    @Override
    public List<Departamento> listarDepartamentos() {
        String sql = "SELECT * FROM departamento";
        List<Departamento> lista = new ArrayList<>();
        try {
            conectarBdd();
            sentencia = conexion.createStatement();
            resultados = sentencia.executeQuery(sql);
            while (resultados.next()) {
                lista.add(new DepartamentoImpl(resultados.getInt(1),
                        resultados.getString(2),
                        resultados.getInt(3),
                        resultados.getString(4),
                        resultados.getString(5)));
            }
        } catch (SQLException e) {
            lista = null;
        } finally {
            desconectarBdd();
            return lista;
        }
    }

    @Override
    public Departamento consultarDepartamento(int id) {
        String sql = "SELECT * FROM departamento WHERE id_dpto = ?";
        Departamento departamento = null;
        try {
            conectarBdd();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1,id);
            resultados = preparada.executeQuery();
            resultados.next();
            departamento = new DepartamentoImpl(resultados.getInt(1),
                    resultados.getString(2),
                    resultados.getInt(3),
                    resultados.getString(4),
                    resultados.getString(5));
        } catch (NullPointerException | SQLException e) {
            departamento = null;
        } finally {
            desconectarBdd();
            return departamento;
        }
    }

    @Override
    public int eliminarDepartamento(int id) {
        int eliminadas = 0;
        String sql = "DELETE FROM departamento WHERE id_dpto = ?";
        Departamento departamentoEliminado = consultarDepartamento(id);
        try {
            conectarBdd();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1,id);
            eliminadas = preparada.executeUpdate();
        } catch (SQLException e) {
            eliminadas = -1;
        } finally {
            desconectarBdd();
            if (eliminadas == 1 && id == departamentoEliminado.getId()) {
                return eliminadas;
            } else {
                return -1;
            }
        }
    }

    @Override
    public int insertarDepartamento(Departamento departamento) {
        int insertadas = 0;
        String sql = "INSERT INTO departamento VALUES (?,?,?,?,?)";
        Departamento departamentoInsertado = null;
        try {
            conectarBdd();
            preparada = conexion.prepareStatement(sql);
            preparada.setInt(1,departamento.getId());
            preparada.setString(2,departamento.getNombre());
            preparada.setInt(3,departamento.getNumEmpleados());
            preparada.setString(4,departamento.getJefe());
            preparada.setString(5,departamento.getServicios());
            insertadas = preparada.executeUpdate();
            departamentoInsertado = consultarDepartamento(departamento.getId());
        } catch (SQLException e) {
            System.out.println("Algo fallo con la base de datos...");
        } finally {
            desconectarBdd();
            if (insertadas == 1 && departamento.equals(departamentoInsertado)) {
                return insertadas;
            } else {
                return -1;
            }
        }
    }

    @Override
    public int modificarDepartamento(Departamento departamento) {
        int modificadas = 0;
        String sql = "UPDATE departamento SET nombre_dpto = ?,num_emp_dpto = ?,jefe_dpto = ?,servicios = ? WHERE id_dpto = ?";
        Departamento departamentoModificado = null;
        try {
            conectarBdd();
            preparada = conexion.prepareStatement(sql);
            preparada.setString(1,departamento.getNombre());
            preparada.setInt(2,departamento.getNumEmpleados());
            preparada.setString(3,departamento.getJefe());
            preparada.setString(4,departamento.getServicios());
            preparada.setInt(5,departamento.getId());
            modificadas = preparada.executeUpdate();
            departamentoModificado = consultarDepartamento(departamento.getId());
        } catch (SQLException e) {
            departamento = null;
        } finally {
            desconectarBdd();
            if (departamento != null && modificadas > 0 && departamento.equals(departamentoModificado)) {
                return modificadas;
            } else {
                return -1;
            }
        }
    }
}
