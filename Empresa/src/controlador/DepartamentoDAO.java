package controlador;

import modelo.Departamento;

import java.util.List;

public interface DepartamentoDAO {
    public int sacaUltimoId();
    public List<Departamento> listarDepartamentos();
    public Departamento consultarDepartamento(int id);
    public int eliminarDepartamento(int id);
    public int insertarDepartamento(Departamento departamento);
    public int modificarDepartamento(Departamento departamento);
}
