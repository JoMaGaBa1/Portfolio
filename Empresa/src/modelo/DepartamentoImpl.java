package modelo;

import java.util.Objects;

public class DepartamentoImpl implements Departamento {
    private int id;
    private String nombre;
    private int numEmpleados;
    private String jefe;
    private String servicios;

    public DepartamentoImpl() {
        this.id = 0;
        this.nombre = "";
        this.numEmpleados = 0;
        this.jefe = "";
        this.servicios = "";
    }

    public DepartamentoImpl(int id,String nombre,int numEmpleados,String jefe,String servicios) {
        this.id = id;
        this.nombre = nombre;
        this.numEmpleados = numEmpleados;
        this.jefe = jefe;
        this.servicios = servicios;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public int getNumEmpleados() {
        return numEmpleados;
    }

    @Override
    public String getJefe() {
        return jefe;
    }

    @Override
    public String getServicios() {
        return servicios;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void setNumEmpleados(int numEmpleados) {
        this.numEmpleados = numEmpleados;
    }

    @Override
    public void setJefe(String jefe) {
        this.jefe = jefe;
    }

    @Override
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartamentoImpl that = (DepartamentoImpl) o;
        return getId() == that.getId() &&
                getNumEmpleados() == that.getNumEmpleados() &&
                getNombre().equals(that.getNombre()) &&
                getJefe().equals(that.getJefe()) &&
                getServicios().equals(that.getServicios());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getNumEmpleados(), getJefe(), getServicios());
    }

    @Override
    public String toString() {
        return "DepartamentoImpl{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", numEmpleados=" + getNumEmpleados() +
                ", jefe='" + getJefe() + '\'' +
                ", servicios='" + getServicios() + '\'' +
                '}';
    }
}
